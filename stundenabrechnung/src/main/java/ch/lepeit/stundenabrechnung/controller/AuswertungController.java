package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.jsflot.xydata.XYDataList;
import org.jsflot.xydata.XYDataSetCollection;

import ch.lepeit.stundenabrechnung.model.GroupedJournal;
import ch.lepeit.stundenabrechnung.model.Verbuchbar;
import ch.lepeit.stundenabrechnung.service.AuswertungService;
import ch.lepeit.stundenabrechnung.service.JournalService;

/**
 * ViewController für den Auswertungstab (auswertung.xhtml)
 * 
 * Stellt die Daten für das Kuchendiagramm sowie für die Liste der nicht verbuchbaren Journaleinträge bereit. Für diese
 * Daten wird auch eine "paging"-Funktion bereit gestellt, um den Monat, von welchem die Daten sind zu ändern.
 * 
 * @author Sven Tschui C910511
 * 
 */
@Named
@SessionScoped
public class AuswertungController implements Serializable {
    private static final long serialVersionUID = 20120523L;

    @EJB
    private AuswertungService auswertungService;

    @EJB
    private JournalService journalService;

    private Date monat;

    private List<GroupedJournal> nichtVerbuchbar;

    private XYDataSetCollection verbuchungsChart;

    private XYDataSetCollection createVerbuchungsChart(Date date) {
        List<Verbuchbar> list = auswertungService.getVerbuchbar(date);

        XYDataSetCollection dataSet = new XYDataSetCollection();
        XYDataList dataList;

        for (Verbuchbar v : list) {
            dataList = new XYDataList();
            dataList.setLabel((v.isVerbuchbar() ? "Verbuchbar" : "Nicht verbuchbar"));
            dataList.addDataPoint(1, v.getZeit());
            dataList.setColor((v.isVerbuchbar() ? "#00FF00" : "#FF0000")); // #FF0000
                                                                           // =
                                                                           // rot
                                                                           // /
                                                                           // #00FF00
                                                                           // =
                                                                           // grün
            dataSet.addDataList(dataList);
        }

        // TODO: Was soll hier hin?
        dataList = new XYDataList();
        dataList.setLabel("What ever 10 Stunden");
        dataList.addDataPoint(1, 10);
        dataSet.addDataList(dataList);

        return dataSet;
    }

    public Date getMonat() {
        return monat;
    }

    public List<GroupedJournal> getNichtVerbuchbar() {
        return this.nichtVerbuchbar;
    }

    public XYDataSetCollection getVerbuchungsChart() {
        return verbuchungsChart;
    }

    @PostConstruct
    public void init() {
        loadMonat(new Date());
        letzterMonat();
    }

    public String letzterMonat() {
        System.out.println("prev");
        Calendar c = new GregorianCalendar();

        c.setTime(this.getMonat());

        c.add(Calendar.MONTH, -1);

        this.loadMonat(c.getTime());

        return null;
    }

    private void loadMonat(Date monat) {
        this.monat = monat;
        nichtVerbuchbar = journalService.getNichtVerbuchbarGroupedJournals(monat);
        verbuchungsChart = createVerbuchungsChart(monat);
    }

    public String naechsterMonat() {
        System.out.println("next");
        Calendar c = new GregorianCalendar();

        c.setTime(this.getMonat());

        c.add(Calendar.MONTH, 1);

        this.loadMonat(c.getTime());

        return null;
    }
}

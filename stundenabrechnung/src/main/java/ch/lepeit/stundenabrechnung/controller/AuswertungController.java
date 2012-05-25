package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jsflot.xydata.XYDataList;
import org.jsflot.xydata.XYDataSetCollection;

import ch.lepeit.stundenabrechnung.model.BuchartStunden;
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
@RequestScoped
public class AuswertungController implements Serializable, Observer {
    private static final long serialVersionUID = 20120524L;

    @EJB
    private AuswertungService auswertungService;

    private XYDataSetCollection buchartChart;

    @EJB
    private JournalService journalService;

    private List<GroupedJournal> nichtVerbuchbar;

    @Inject
    private AuswertungPagingController pagingController;

    private XYDataSetCollection verbuchbarChart;

    private XYDataSetCollection createBuchartChart(Date date) {
        List<BuchartStunden> list = auswertungService.getBuchartProMonat(date);

        XYDataSetCollection dataSet = new XYDataSetCollection();
        XYDataList dataList;

        for (BuchartStunden v : list) {
            dataList = new XYDataList();
            dataList.setLabel(createLabel(v.getStunden(), v.getBuchart()));
            dataList.addDataPoint(1, v.getStunden());
            dataSet.addDataList(dataList);
        }

        return dataSet;
    }

    public String createLabel(Double stunden, boolean verbuchbar) {
        NumberFormat stundenFormat = NumberFormat.getNumberInstance();
        stundenFormat.setMaximumFractionDigits(0);

        NumberFormat tagFormat = NumberFormat.getNumberInstance();
        tagFormat.setMaximumFractionDigits(1);

        return stundenFormat.format(stunden) + " Stunden / " + tagFormat.format(stunden / 8.4) + " Tage "
                + (verbuchbar ? "Verbuchbar" : "Nicht verbuchbar");
    }

    public String createLabel(Double stunden, String tool) {
        NumberFormat stundenFormat = NumberFormat.getNumberInstance();
        stundenFormat.setMaximumFractionDigits(0);

        NumberFormat tagFormat = NumberFormat.getNumberInstance();
        tagFormat.setMaximumFractionDigits(1);

        return stundenFormat.format(stunden) + " Stunden / " + tagFormat.format(stunden / 8.4) + " Tage " + tool;
    }

    private XYDataSetCollection createVerbuchbarChart(Date date) {
        List<Verbuchbar> list = auswertungService.getVerbuchbar(date);

        XYDataSetCollection dataSet = new XYDataSetCollection();
        XYDataList dataList;

        for (Verbuchbar v : list) {
            dataList = new XYDataList();
            dataList.setLabel(createLabel(v.getZeit(), v.isVerbuchbar()));
            dataList.addDataPoint(1, v.getZeit());
            dataList.setColor((v.isVerbuchbar() ? "#22DD22" : "#FF0000")); // grün / rot
            dataSet.addDataList(dataList);
        }

        return dataSet;
    }

    @PreDestroy
    public void destruct() {
        this.pagingController.deleteObserver(this);
    }

    public XYDataSetCollection getBuchartChart() {
        return buchartChart;
    }

    public List<GroupedJournal> getNichtVerbuchbar() {
        return this.nichtVerbuchbar;
    }

    public XYDataSetCollection getVerbuchbarChart() {
        return verbuchbarChart;
    }

    @PostConstruct
    public void init() {
        this.pagingController.addObserver(this);
        this.loadMonat(this.pagingController.getMonat());
    }

    private void loadMonat(Date monat) {
        nichtVerbuchbar = journalService.getNichtVerbuchbarGroupedJournals(monat);
        verbuchbarChart = createVerbuchbarChart(monat);
        buchartChart = createBuchartChart(monat);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.loadMonat(this.pagingController.getMonat());
    }
}

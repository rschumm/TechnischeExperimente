package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.GroupedJournal;
import ch.lepeit.stundenabrechnung.service.JournalService;

/**
 * ViewController für den Verbuchen-Tab (verbuchen.xhtml)
 * 
 * Stellt alle Journaleinträge in Form eines JournalDataModel zur Verfügung. Dieses Model wird für das Serverseitige
 * Paging von Richfaces benötigt. Ausserdem steht eine Funktion zur Verfügung, mit welcher der "verbucht" Status eines
 * Journaleintrages geändert werden kann.
 * 
 * @author Sven Tschui C910511
 * 
 */
@Named
@SessionScoped
public class VerbuchenController implements Serializable {
    private static final long serialVersionUID = 20120523L;

    private List<GroupedJournal> buchungen;

    @EJB
    private JournalService journalService;

    public List<GroupedJournal> getBuchungen() {
        return buchungen;
    }

    @PostConstruct
    public void init() {
        this.reload();
    }

    public String reload() {
        this.buchungen = journalService.getNichtVerbuchteGroupedJournals();

        return null;
    }

    public String verbuchen(GroupedJournal j) {

        j.setPlantaverbucht(true);

        journalService.verbuchen(j.getDatum(), j.getTask().getName());

        return null;
    }
}

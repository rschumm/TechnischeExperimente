package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.datamodel.JournalDataModel;
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

    @EJB
    private JournalDataModel buchungen;

    @EJB
    private JournalService journalService;

    /* dataTable end */

    public JournalDataModel getBuchungen() {
        return buchungen;
    }

    public String verbuchen(int nr, boolean verbuchen) {
        journalService.verbuchen(nr, verbuchen);
        return null;
    }
}

package ch.lepeit.stundenabrechnung.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.JournalService;
import ch.lepeit.stundenabrechnung.service.TaskService;

/**
 * ViewController für das Hinzufügen eines Journaleintrages (journalAdd.xhtml)
 * 
 * Stellt eine Funktion zum speichern/erfassen eines Journaleintrages bereit. Es wird eine Liste aller Tasks für das
 * DropDown bereitgestellt.
 * 
 * @author Sven Tschui C910511
 * 
 */
@Named
@RequestScoped
public class JournalAddController {
    private Journal journal;

    @Inject
    private JournalController journalController;

    @EJB
    private JournalService journalService;

    private List<Task> tasks;

    @EJB
    private TaskService taskService;

    public Journal getJournal() {
        return journal;
    }

    public String getTask() {
        if (this.journal == null || this.journal.getTask() == null) {
            return null;
        }

        return this.journal.getTask().getName();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    @PostConstruct
    public void init() {
        // Leeres Journal für das Hinzufpgen-Formular
        this.journal = new Journal();

        this.tasks = this.taskService.getTasks();
    }

    public String save() {
        // Journal speichern
        journalService.save(this.journal);

        // Neues leeres Journal
        this.journal = new Journal();

        // Daten der Wochenübersicht neu laden
        this.journalController.reload();

        return null;
    }

    public void setTask(String task) {
        this.journal.setTask(this.taskService.getTask(task));
    }
}

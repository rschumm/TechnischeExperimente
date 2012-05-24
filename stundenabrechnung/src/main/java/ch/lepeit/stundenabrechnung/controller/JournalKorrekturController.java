package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;

import ch.lepeit.stundenabrechnung.datamodel.JournalDataModel;
import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.JournalService;
import ch.lepeit.stundenabrechnung.service.TaskService;

/**
 * ViewController für die Korrekturansicht des Journals (korrektur.xhtml)
 * 
 * Stellt alle Journaleinträge sowie alle Tasks zur verfügung. Es gibt noch die möglichkeit einen Journaleintrag zu
 * editieren. Die Journaleinträge werden in Form eines JournalDataModel zur verfügung gestellt, um das Serverseitige
 * Paging von Richfaces zu ermöglichen.
 * 
 * @author Sven Tschui C910511
 * 
 */
@Named
@SessionScoped
public class JournalKorrekturController implements Serializable {
    private static final long serialVersionUID = 20120516L;

    /* Richfaces dataTable */
    @EJB
    private JournalDataModel buchungen;

    /* dataTable end */

    @EJB
    private JournalService journalService;

    private Journal selectedItem;

    private Collection<Journal> selection;

    private List<Task> tasks;

    @EJB
    private TaskService taskService;

    public JournalDataModel getBuchungen() {
        return this.buchungen;
    }

    public Journal getSelectedItem() {
        return selectedItem;
    }

    public Collection<Journal> getSelection() {
        return selection;
    }

    public String getTask() {
        if (selectedItem == null || selectedItem.getTask() == null) {
            return null;
        }

        return selectedItem.getTask().getName();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    @PostConstruct
    public void init() {
        this.loadJournals();
        this.tasks = taskService.getTasks();
    }

    private void loadJournals() {
        this.selectedItem = null;
        this.selection = null;
    }

    public void remove() {
        journalService.delete(this.selectedItem);
        // TODO: implement
        System.out.println("remove");

        // reload from database
        this.loadJournals();
    }

    public void save() {
        System.out.println("save : " + this.selectedItem.getNr());
        journalService.update(this.selectedItem);

        // reload from database
        this.loadJournals();
    }

    public void selectionListener(AjaxBehaviorEvent event) {

        this.selectedItem = null;

        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();

        Object originalKey = dataTable.getRowKey();

        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                this.selectedItem = (Journal) dataTable.getRowData();

                System.out.println("selectionChanged : " + this.selectedItem.getNr());
            }
        }

        dataTable.setRowKey(originalKey);

    }

    public void setSelectedItem(Journal selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setSelection(Collection<Journal> selection) {
        this.selection = selection;
    }

    public void setTask(String task) {
        this.selectedItem.setTask(taskService.getTask(task));
    }
}

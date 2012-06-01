package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Observable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;

import ch.lepeit.stundenabrechnung.datamodel.JournalDataModel;
import ch.lepeit.stundenabrechnung.model.Journal;

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
public class KorrekturController extends Observable implements Serializable {
    private static final long serialVersionUID = 20120516L;

    @EJB
    private JournalDataModel buchungen;

    private Journal selectedItem;

    private Collection<Journal> selection;

    public JournalDataModel getBuchungen() {
        return this.buchungen;
    }

    public Journal getSelectedItem() {
        return selectedItem;
    }

    public Collection<Journal> getSelection() {
        return selection;
    }

    @PostConstruct
    public void init() {
        this.selectedItem = null;
        this.selection = null;
    }

    public void selectionListener(AjaxBehaviorEvent event) {

        this.selectedItem = null;

        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();

        Object originalKey = dataTable.getRowKey();

        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                this.selectedItem = (Journal) dataTable.getRowData();
            }
        }

        this.setChanged();
        this.notifyObservers();

        dataTable.setRowKey(originalKey);

    }

    public void setSelectedItem(Journal selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setSelection(Collection<Journal> selection) {
        this.selection = selection;
    }
}

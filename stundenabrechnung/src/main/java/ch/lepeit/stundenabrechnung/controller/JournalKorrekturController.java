package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.richfaces.component.UIExtendedDataTable;

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.JournalService;
import ch.lepeit.stundenabrechnung.service.TaskService;

@Named
@SessionScoped
public class JournalKorrekturController implements Serializable {
	private static final long serialVersionUID = 20120516L;

	@EJB
	private JournalService journalService;

	@EJB
	private TaskService taskService;

	private Collection<Journal> selection;

	public List<Journal> getBuchungen() {
		return journalService.getJournals();
	}
	
	public List<Task> getTasks() {
		return taskService.getTasks();
	}

	public void remove() {
		// TODO
		System.out.println("remove");
	}

	public void save() {
		System.out.println("save");
		journalService.update(this.selectedItem);
	}
	
	private Journal selectedItem = null;

	public void selectionListener(AjaxBehaviorEvent event) {
		
		this.selectedItem = null;

		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		
		Object originalKey = dataTable.getRowKey();
		
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
            	this.selectedItem = (Journal) dataTable.getRowData();
            }
        }
        
        dataTable.setRowKey(originalKey);

	}

	public Collection<Journal> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Journal> selection) {
		this.selection = selection;
	}
	
	public Journal getSelectedItem() {
		return selectedItem;
	}
	
	public void setSelectedItem(Journal selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	public String getTask() {
		if(selectedItem == null || selectedItem.getTask() == null)
			return null;
		
		return selectedItem.getTask().getName();
	}
	
	public void setTask(String task) {
		this.selectedItem.setTask(taskService.getTask(task));
	}
}

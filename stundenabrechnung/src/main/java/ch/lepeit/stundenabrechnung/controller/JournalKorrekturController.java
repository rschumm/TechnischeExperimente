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

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.JournalService;
import ch.lepeit.stundenabrechnung.service.TaskService;

@Named
@SessionScoped
public class JournalKorrekturController implements Serializable {
	private static final long serialVersionUID = 20120516L;
	
	private List<Journal> buchungen;

	private List<Task> tasks;

	private Collection<Journal> selection;
	
	private Journal selectedItem;

	@EJB
	private JournalService journalService;

	@EJB
	private TaskService taskService;
	
	@PostConstruct
	public void init() {
		this.loadJournals();
		this.tasks = taskService.getTasks();
	}
	
	private void loadJournals() {
		this.buchungen = journalService.getJournals();
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

		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		
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

	public List<Journal> getBuchungen() {
		return this.buchungen;
	}
	
	public List<Task> getTasks() {
		return this.tasks;
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

package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.richfaces.component.SortOrder;

import ch.lepeit.stundenabrechnung.datamodel.JournalDataModel;
import ch.lepeit.stundenabrechnung.service.JournalService;

import com.google.common.collect.Maps;

@Named
@SessionScoped
public class VerbuchenController implements Serializable {
	private static final long serialVersionUID = 20120523L;
	
	@EJB
	private JournalService journalService;
	
	/* Richfaces dataTable */
	private Map<String, SortOrder> sortOrders = Maps
			.newHashMapWithExpectedSize(1);
	private Map<String, String> filterValues = Maps.newHashMap();
	private String sortProperty;
	@EJB
	private JournalDataModel buchungen;
	/* dataTable end */
	
	public JournalDataModel getBuchungen() {
		return buchungen;
	}
	
	public String verbuchen(int nr, boolean verbuchen) {
		journalService.verbuchen(nr, verbuchen);
		return null;
	}

	/* Richfaces dataTable */
	public Map<String, SortOrder> getSortOrders() {
		return sortOrders;
	}

	public Map<String, String> getFilterValues() {
		return filterValues;
	}

	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortPropety) {
		this.sortProperty = sortPropety;
	}

	public void toggleSort() {
		for (Entry<String, SortOrder> entry : sortOrders.entrySet()) {
			SortOrder newOrder;
			if (entry.getKey().equals(sortProperty)) {
				if (entry.getValue() == SortOrder.ascending) {
					newOrder = SortOrder.descending;
				} else {
					newOrder = SortOrder.ascending;
				}
			} else {
				newOrder = SortOrder.unsorted;
			}
			entry.setValue(newOrder);
		}
	}
	/* dataTable end */
}

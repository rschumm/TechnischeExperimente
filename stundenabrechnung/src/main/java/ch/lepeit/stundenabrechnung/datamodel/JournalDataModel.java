package ch.lepeit.stundenabrechnung.datamodel;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateful;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import ch.lepeit.stundenabrechnung.model.Journal;

/**
 * GenericDataModel EJB für Journaleinträge
 * 
 * @author C910511
 * 
 */
@Stateful
public class JournalDataModel extends GenericDataModel<Journal> {
    public JournalDataModel() {
        super(Journal.class);
    }

    @Override
    protected List<Order> createOrders(CriteriaBuilder criteriaBuilder, Root<Journal> root) {
        List<Order> orders = new Vector<Order>(); // super.createOrders(criteriaBuilder, root);

        orders.add(criteriaBuilder.desc(root.get("datum")));

        return orders;
    }
}

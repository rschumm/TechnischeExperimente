package ch.lepeit.stundenabrechnung.datamodel;

import java.lang.reflect.Field;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.component.SortOrder;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * Wird von den Richfaces benötigt, um Serverseitiges Paging, Filtering und Sorting zu ermöglichen. Diese abstrakte
 * Klasse ist eine Vorlage, welche dann als DataModel für eine rich:dataTable verwendet werden kann. Das DataModel,
 * welches von dieser Klasse erbt, muss entweder ein EJB (Stateful) sein oder den EntityManager mit
 * setEntityManager(EntityManager entityManager) setzen.
 * 
 * @author C910511
 * 
 * @param <T>
 * Klasse der Einträge, welche zur Verfügung gestellt werden.
 */
public abstract class GenericDataModel<T> extends ExtendedDataModel<T> implements Arrangeable {
    private ArrangeableState arrangeableState;

    private final Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    private Object rowKey;

    public GenericDataModel(Class<T> entityClass) {
        super();

        this.entityClass = entityClass;
    }

    public GenericDataModel(EntityManager entityManager, Class<T> entityClass) {
        super();

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public void arrange(FacesContext context, ArrangeableState state) {
        arrangeableState = state;
    }

    private CriteriaQuery<Long> createCountCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);

        Expression<Boolean> filterCriteria = createFilterCriteria(criteriaBuilder, root);
        if (filterCriteria != null) {
            criteriaQuery.where(filterCriteria);
        }

        Expression<Long> count = criteriaBuilder.count(root);
        criteriaQuery.select(count);

        return criteriaQuery;
    }

    private Expression<Boolean> createFilterCriteria(CriteriaBuilder criteriaBuilder, Root<T> root) {
        Expression<Boolean> filterCriteria = null;
        List<FilterField> filterFields = (arrangeableState == null ? null : arrangeableState.getFilterFields());
        if (filterFields != null && !filterFields.isEmpty()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            for (FilterField filterField : filterFields) {
                String propertyName = (String) filterField.getFilterExpression().getValue(facesContext.getELContext());
                Object filterValue = filterField.getFilterValue();

                Expression<Boolean> predicate = createFilterCriteriaForField(propertyName, filterValue, root,
                        criteriaBuilder);

                if (predicate == null) {
                    continue;
                }

                if (filterCriteria == null) {
                    filterCriteria = predicate.as(Boolean.class);
                } else {
                    filterCriteria = criteriaBuilder.and(filterCriteria, predicate.as(Boolean.class));
                }
            }
        }
        return filterCriteria;
    }

    protected Expression<Boolean> createFilterCriteriaForField(String propertyName, Object filterValue, Root<T> root,
            CriteriaBuilder criteriaBuilder) {
        String stringFilterValue = (String) filterValue;
        if (Strings.isNullOrEmpty(stringFilterValue)) {
            return null;
        }

        stringFilterValue = stringFilterValue.toLowerCase(arrangeableState.getLocale());

        Path<String> expression = root.get(propertyName);
        Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), stringFilterValue, 1);
        return criteriaBuilder.gt(locator, 0);
    }

    protected List<Order> createOrders(CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Order> orders = Lists.newArrayList();
        List<SortField> sortFields = arrangeableState.getSortFields();
        if (sortFields != null && !sortFields.isEmpty()) {

            FacesContext facesContext = FacesContext.getCurrentInstance();

            for (SortField sortField : sortFields) {
                String propertyName = (String) sortField.getSortBy().getValue(facesContext.getELContext());

                Path<Object> expression = root.get(propertyName);

                Order jpaOrder;
                SortOrder sortOrder = sortField.getSortOrder();
                if (sortOrder == SortOrder.ascending) {
                    jpaOrder = criteriaBuilder.asc(expression);
                } else if (sortOrder == SortOrder.descending) {
                    jpaOrder = criteriaBuilder.desc(expression);
                } else {
                    throw new IllegalArgumentException(sortOrder.toString());
                }

                orders.add(jpaOrder);
            }
        }

        return orders;
    }

    protected CriteriaQuery<T> createSelectCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        // if (arrangeableState != null) {

        List<Order> orders = createOrders(criteriaBuilder, root);
        if (!orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }

        Expression<Boolean> filterCriteria = createFilterCriteria(criteriaBuilder, root);
        if (filterCriteria != null) {
            criteriaQuery.where(filterCriteria);
        }
        // }

        return criteriaQuery;
    }

    protected ArrangeableState getArrangeableState() {
        return arrangeableState;
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    protected Object getId(T o) {
        Field[] fields = o.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (f.getAnnotation(Id.class) != null) {
                f.setAccessible(true);
                try {
                    return f.get(o);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public int getRowCount() {
        CriteriaQuery<Long> criteriaQuery = createCountCriteriaQuery();
        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    @Override
    public T getRowData() {
        return entityManager.find(entityClass, rowKey);
    }

    @Override
    public int getRowIndex() {
        return -1;
    }

    @Override
    public Object getRowKey() {
        return rowKey;
    }

    @Override
    public Object getWrappedData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRowAvailable() {
        return rowKey != null;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRowKey(Object key) {
        rowKey = key;
    }

    @Override
    public void setWrappedData(Object data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
        CriteriaQuery<T> criteriaQuery = createSelectCriteriaQuery();
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        SequenceRange sequenceRange = (SequenceRange) range;
        if (sequenceRange.getFirstRow() >= 0 && sequenceRange.getRows() > 0) {
            query.setFirstResult(sequenceRange.getFirstRow());
            query.setMaxResults(sequenceRange.getRows());
        }

        List<T> data = query.getResultList();
        for (T t : data) {

            visitor.process(context, getId(t), argument);
        }
    }
}
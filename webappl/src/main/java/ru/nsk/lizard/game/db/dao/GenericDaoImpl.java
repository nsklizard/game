package ru.nsk.lizard.game.db.dao;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * http://www.codeproject.com/Articles/251166/The-Generic-DAO-pattern-in-Java-with-Spring-and
 */

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private static final Logger LOG = Logger.getLogger(GenericDaoImpl.class);

    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public GenericDaoImpl(Class<T> clazz) {
        type = clazz;
    }

    @Override
    public long countAll(final Map<String, Object> params) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(type.getSimpleName()).append(" o ");
        queryString.append(this.getQueryClauses(params, null));

        final Query query = this.em.createQuery(queryString.toString());

        return (Long) query.getSingleResult();

    }

    @Override
    public T create(final T t) {
//      return  this.em.persist(t); //TODO это была базовая реализация
        return this.em.merge(t);
    }

    @Override
    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Override
    public T find(final Object id) {
        return this.em.find(type, id);
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = em.createQuery("from " + type.getName(), type);
        List<T> list = query.getResultList();
        return list;
    }

    @Override
    public List<T> findRange(int start, int end, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> from = cq.from(type);
        cq.select(from);


        List<Predicate> predicates = new ArrayList<Predicate>();
        if (filters != null) {
            // filters
            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext(); ) {
                String filterProperty = it.next(); // table column name = field name
                System.out.println("filterProperty is: " + filterProperty);

                String filterValue = (String) filters.get(filterProperty);
                System.out.println("filterValue is: " + filterValue);

                Expression<String> literal = cb.literal((String) filterValue);
                try {
                    predicates.add(cb.like(from.<String>get(filterProperty), literal));
                } catch (IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        TypedQuery<T> typedQuery = createTypedQuery(start, end, cq);
        return typedQuery.getResultList();
    }

    @Override
    public long countRange(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> from = cq.from(type);
        cq.select(from);


        List<Predicate> predicates = new ArrayList<Predicate>();
        if (filters != null) {
            // filters
            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext(); ) {
                String filterProperty = it.next(); // table column name = field name
                System.out.println("filterProperty is: " + filterProperty);

                String filterValue = (String) filters.get(filterProperty);
                System.out.println("filterValue is: " + filterValue);

                Expression<String> literal = cb.literal((String) filterValue);
                try {
                    predicates.add(cb.like(from.<String>get(filterProperty), literal));
                } catch (IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
        }


        TypedQuery<T> q = em.createQuery(cq);
        return q.getResultList().size();
    }


    @Override
    public T update(final T t) {
        return this.em.merge(t);
    }



    private String getQueryClauses(final Map<String, Object> params, final Map<String, Object> orderParams) {
        final StringBuffer queryString = new StringBuffer();
        if ((params != null) && !params.isEmpty()) {
            queryString.append(" where ");
            for (final Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry<String, Object> entry = it.next();
                if (entry.getValue() instanceof Boolean) {
                    queryString.append(entry.getKey()).append(" is ").append(entry.getValue()).append(" ");
                } else {
                    if (entry.getValue() instanceof Number) {
                        queryString.append(entry.getKey()).append(" = ").append(entry.getValue());
                    } else {
                        // string equality
                        queryString.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
                    }
                }
                if (it.hasNext()) {
                    queryString.append(" and ");
                }
            }
        }
        if ((orderParams != null) && !orderParams.isEmpty()) {
            queryString.append(" order by ");
            for (final Iterator<Map.Entry<String, Object>> it = orderParams.entrySet().iterator(); it.hasNext(); ) {
                final Map.Entry<String, Object> entry = it.next();
                queryString.append(entry.getKey()).append(" ");
                if (entry.getValue() != null) {
                    queryString.append(entry.getValue());
                }
                if (it.hasNext()) {
                    queryString.append(", ");
                }
            }
        }
        return queryString.toString();
    }

    protected <T> TypedQuery<T> createTypedQuery(int start, int end, CriteriaQuery<T> criteriaQuery) {
        TypedQuery<T> query = em.createQuery(criteriaQuery);

        if (start > 0) {
            query.setFirstResult(start);
        }

        if (end >= 0) {
            query.setMaxResults(end - Math.max(0, start));
        }

        return query;
    }
}
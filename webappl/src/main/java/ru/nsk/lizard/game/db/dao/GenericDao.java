package ru.nsk.lizard.game.db.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T> {
    /**
     * Method that returns the number of entries from a table that meet some
     * criteria (where clause params)
     *
     * @param params sql parameters
     * @return the number of records meeting the criteria
     */
    long countAll(Map<String, Object> params);

    T create(T t);

    void delete(Object id);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int start, int end, Map<String, Object> filters);

    long countRange(Map<String, Object> filters);

    T update(T t);

//    default String any(String str) {
//        return "lower('%" + str + "%')";
//    }
}
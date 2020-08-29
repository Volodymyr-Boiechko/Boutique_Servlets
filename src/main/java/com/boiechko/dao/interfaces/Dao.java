package com.boiechko.dao.interfaces;

import java.util.List;

public interface Dao<T> {

    //add to data base
    boolean add(final T t);

    //get from data base
    T getById(final int id);

    //get all data
    List<T> getAll();

    //update
    boolean update(final T t);

    boolean delete(final int id);

}

package com.boiechko.dao.interfaces;

import java.util.List;

public interface Dao<T> {

    //add to data base
    boolean add(T t);

    //get from data base
    T getById(int id);

    //get all data
    List<T> getAll();

    //update
    boolean update(T t);

    boolean delete(int id);

}

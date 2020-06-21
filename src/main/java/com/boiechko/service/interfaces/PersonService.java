package com.boiechko.service.interfaces;

import com.boiechko.entity.Person;

import java.util.List;

public interface PersonService {

    Person getPersonByCredentials(String column, String credentials);

    boolean add(Person person);

    Person getById(int id);

    List<Person> getAll();

    boolean update(Person t);

    boolean delete(int id);

}

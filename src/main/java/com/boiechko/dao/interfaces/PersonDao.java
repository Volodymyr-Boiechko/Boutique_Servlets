package com.boiechko.dao.interfaces;

import com.boiechko.entity.Person;

public interface PersonDao extends Dao<Person> {

    Person getPersonByCredentials(String username);

    boolean addFull(Person person);

}

package com.boiechko.service.interfaces;

import com.boiechko.entity.Person;

import java.util.List;

public interface PersonService {

    Person getPersonByCredentials(final String column, final String credentials);

    boolean addPerson(final Person person);

    Person getPersonById(final int id);

    List<Person> getAllPersons();

    boolean updatePerson(final Person person);

    boolean deletePerson(final int id);

}

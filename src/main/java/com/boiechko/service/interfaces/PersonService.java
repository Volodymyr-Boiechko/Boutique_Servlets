package com.boiechko.service.interfaces;

import com.boiechko.entity.Person;

import java.util.List;

public interface PersonService {

    Person getPersonByCredentials(String column, String credentials);

    boolean addPerson(Person person);

    Person getPersonById(int id);

    List<Person> getAllPersons();

    boolean updatePerson(Person person);

    boolean deletePerson(int id);

}

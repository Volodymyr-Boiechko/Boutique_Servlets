package com.boiechko.service.interfaces;

import com.boiechko.entity.Person;

import java.util.List;

public interface PersonService {

    Person getPersonByColumn(final String column, final String credentials);

    boolean addPerson(final Person person);

    Person getPersonById(final int idPerson);

    List<Person> getAllPersons();

    boolean updatePerson(final Person person);

    boolean deletePerson(final int id);

    boolean isPersonAdmin(final Person person);

}

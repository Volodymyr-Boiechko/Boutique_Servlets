package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.PersonDaoImpl;
import com.boiechko.dao.interfaces.PersonDao;
import com.boiechko.entity.Person;
import com.boiechko.service.interfaces.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao = new PersonDaoImpl();

    @Override
    public Person getPersonByCredentials(String column, String credentials) { return personDao.getByCredentials(column, credentials); }

    @Override
    public boolean addPerson(Person person) { return personDao.add(person); }

    @Override
    public Person getPersonById(int id) { return personDao.getById(id); }

    @Override
    public List<Person> getAllPersons() { return personDao.getAll(); }

    @Override
    public boolean updatePerson(Person person) { return personDao.update(person); }

    @Override
    public boolean deletePerson(int id) { return personDao.delete(id); }
}

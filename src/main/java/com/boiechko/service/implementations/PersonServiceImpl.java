package com.boiechko.service.implementations;

import com.boiechko.dao.implementations.PersonDaoImpl;
import com.boiechko.dao.interfaces.PersonDao;
import com.boiechko.entity.Person;
import com.boiechko.service.interfaces.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao = new PersonDaoImpl();

    @Override
    public Person getPersonByCredentials(String username) {
        return personDao.getPersonByCredentials(username);
    }

    @Override
    public boolean add(Person person) {
        return personDao.add(person);
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public boolean update(Person t) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

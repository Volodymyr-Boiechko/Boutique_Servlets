package com.boiechko.dao.interfaces;

import com.boiechko.entity.Person;

public interface PersonDao extends Dao<Person> {

    Person getPersonByColumn(final String column, final String credentials);

}

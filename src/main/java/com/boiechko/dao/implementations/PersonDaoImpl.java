package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.PersonDao;
import com.boiechko.entity.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonDaoImpl implements PersonDao {

    // We receive the user on special data
    @Override
    public Person getPersonByCredentials(String column, String credentials) {

        String query = "SELECT * FROM person WHERE " + column + " = ?";

        PreparedStatement preparedStatement = null;
        Person person = new Person();

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, credentials);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                person.setIdPerson(rs.getInt("idPerson"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                person.setFirstName(rs.getString("firstName"));
                person.setLastName(rs.getString("lastName"));
                person.setBirthDate(rs.getDate("birthDate"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNumber(rs.getString("phoneNumber"));
                person.setIdAddress(rs.getInt("idAddress"));
                person.setPersonType(rs.getString("typeName"));
                person.setActivationCode(rs.getString("activationCode"));

            }

            return person;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            return null;

        } finally {

            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }
    }

    //Add to DataBase
    @Override
    public boolean add(Person person) {

        String query = "INSERT INTO person(username,password, birthDate, email, idAddress, typeName, activationCode)" +
                "VALUE (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setDate(3, person.getBirthDate());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, "USER");
            preparedStatement.setString(7, UUID.randomUUID().toString());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return false;
        } finally {
            try {

                assert preparedStatement != null;
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Get user from data base with special ID
    @Override
    public Person getById(int id) {

        String query = "SELECT * FROM person WHERE idPerson=?";

        PreparedStatement preparedStatement = null;
        Person person = new Person();

        try {

            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                person.setIdPerson(rs.getInt("idPerson"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                person.setFirstName(rs.getString("firstName"));
                person.setLastName(rs.getString("lastName"));
                person.setBirthDate(rs.getDate("birthDate"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNumber(rs.getString("phoneNumber"));
                person.setIdAddress(rs.getInt("idAddress"));
                person.setPersonType(rs.getString("typeName"));
                person.setActivationCode(rs.getString("activationCode"));

            }

            return person;

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

            return null;
        }  finally {

            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    // Get ALL users from data base
    @Override
    public List<Person> getAll() {

        String query = "SELECT * FROM person";
        List<Person> personList = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Person person = new Person();
                person.setIdPerson(rs.getInt("idPerson"));
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                person.setFirstName(rs.getString("firstName"));
                person.setLastName(rs.getString("lastName"));
                person.setBirthDate(rs.getDate("birthDate"));
                person.setEmail(rs.getString("email"));
                person.setPhoneNumber(rs.getString("phoneNumber"));
                person.setIdAddress(rs.getInt("idAddress"));
                person.setPersonType(rs.getString("typeName"));
                person.setActivationCode(rs.getString("activationCode"));

                personList.add(person);

            }
            return personList;
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return null;

        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    // Update user's info in data base
    @Override
    public boolean update(Person person) {

        String query = "UPDATE person SET username=?, password=?, firstName=?, lastName=?," +
                "birthDate=?,email=?,phoneNumber=?,idAddress=?,activationCode=? WHERE idPerson=?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(4, person.getLastName());
            preparedStatement.setDate(5, person.getBirthDate());
            preparedStatement.setString(6, person.getEmail());
            preparedStatement.setString(7, person.getPhoneNumber());
            preparedStatement.setInt(8, person.getIdAddress());
            preparedStatement.setString(9, person.getActivationCode());
            preparedStatement.setInt(10, person.getIdPerson());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        } finally {

            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }
    }

    // Delete user from data base
    @Override
    public boolean delete(int id) {

        String query = "DELETE FROM person WHERE idPerson=?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
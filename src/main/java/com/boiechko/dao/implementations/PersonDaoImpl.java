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
    public Person getByCredentials(String column, String credentials) {

        String query = "SELECT * FROM person WHERE " + column + " = ?";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, credentials);
            ResultSet rs = preparedStatement.executeQuery();

            Person person = new Person();

            if (rs.next()) person = getPerson(rs);

            return person;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    //Add to DataBase
    @Override
    public boolean add(Person person) {

        String query = "INSERT INTO person(username,password, birthDate, email, typeName, activationCode)" +
                "VALUE (?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setDate(3, person.getBirthDate());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, "USER");
            preparedStatement.setString(6, UUID.randomUUID().toString());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    // Get user from data base with special ID
    @Override
    public Person getById(int id) {

        String query = "SELECT * FROM person WHERE idPerson=?";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Person person = new Person();

            if (rs.next()) person = getPerson(rs);

            return person;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    // Get ALL users from data base
    @Override
    public List<Person> getAll() {

        String query = "SELECT * FROM person";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();

            List<Person> personList = new ArrayList<>();

            while (rs.next()) {

                Person person = getPerson(rs);

                personList.add(person);

            }
            return personList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    // Update user's info in data base
    @Override
    public boolean update(Person person) {

        String query = "UPDATE person SET username=?, password=?, firstName=?, surname=?, lastName=?," +
                "birthDate=?,email=?,phoneNumber=?,activationCode=? WHERE idPerson=?";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(4, person.getSurname());
            preparedStatement.setString(5, person.getLastName());
            preparedStatement.setDate(6, person.getBirthDate());
            preparedStatement.setString(7, person.getEmail());
            preparedStatement.setString(8, person.getPhoneNumber());
            preparedStatement.setString(9, person.getActivationCode());
            preparedStatement.setInt(10, person.getIdPerson());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    // Delete user from data base
    @Override
    public boolean delete(int id) {

        String query = "DELETE FROM person WHERE idPerson=?";

        try(PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    private Person getPerson(ResultSet resultSet) throws SQLException {

        Person person = new Person();

        person.setIdPerson(resultSet.getInt("idPerson"));
        person.setUsername(resultSet.getString("username"));
        person.setPassword(resultSet.getString("password"));
        person.setFirstName(resultSet.getString("firstName"));
        person.setSurname(resultSet.getString("surname"));
        person.setLastName(resultSet.getString("lastName"));
        person.setBirthDate(resultSet.getDate("birthDate"));
        person.setEmail(resultSet.getString("email"));
        person.setPhoneNumber(resultSet.getString("phoneNumber"));
        person.setPersonType(resultSet.getString("typeName"));
        person.setActivationCode(resultSet.getString("activationCode"));

        return person;

    }
}
package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.PersonDao;
import com.boiechko.entity.Person;
import com.boiechko.enums.PersonType;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    // We receive the user on special data
    @Override
    public Person getPersonByColumn(final String column, final String credentials) {

        final String query = "SELECT * FROM person WHERE " + column + " = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, credentials);
            ResultSet rs = preparedStatement.executeQuery();

            Person person = new Person();

            if (rs.next()) person = getPerson(rs);

            return person;

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return null;
        }
    }

    //Add to DataBase
    @Override
    public boolean add(final Person person) {

        final String query = "INSERT INTO person(username,password, birthDate, email, typeName, activationCode)" +
                "VALUE (?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setDate(3, person.getBirthDate());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, PersonType.USER.toString());
            preparedStatement.setString(6, person.getActivationCode());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return false;
        }
    }

    // Get user from data base with special ID
    @Override
    public Person getById(final int idPerson) {

        final String query = "SELECT * FROM person WHERE idPerson=?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idPerson);
            ResultSet rs = preparedStatement.executeQuery();

            Person person = new Person();

            if (rs.next()) person = getPerson(rs);

            return person;

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return null;
        }
    }

    // Get ALL users from data base
    @Override
    public List<Person> getAll() {

        final String query = "SELECT * FROM person";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();

            List<Person> personList = new ArrayList<>();

            while (rs.next()) {

                Person person = getPerson(rs);

                personList.add(person);

            }
            return personList;
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return null;
        }
    }

    // Update user's info in data base
    @Override
    public boolean update(final Person person) {

        final String query = "UPDATE person SET username=?, password=?, firstName=?, surname=?, lastName=?," +
                "birthDate=?,email=?,phoneNumber=?,activationCode=? WHERE idPerson=?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

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
            logger.error(sqlException.getMessage());
            return false;
        }
    }

    // Delete user from data base
    @Override
    public boolean delete(final int idPerson) {

        final String query = "DELETE FROM person WHERE idPerson=?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idPerson);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return false;
        }
    }

    private Person getPerson(final ResultSet resultSet) throws SQLException {

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
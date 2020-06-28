package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.PersonDao;
import com.boiechko.entity.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    // Check if there is already a user with such username
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

        String query = "INSERT INTO person(username,password, birthDate, email, idAddress, typeName)" +
                "VALUE (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setDate(3, person.getBirthDate());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, "USER");

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

    @Override
    public boolean addFull(Person person) {
        return false;
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
    public boolean update(Person person) {

        String query = "UPDATE person SET username=?, password=?, firstName=?, lastName=?," +
                "birthDate=?,email=?,phoneNumber=?,idAddress=? WHERE idPerson=?";

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
            preparedStatement.setInt(9, person.getIdPerson());

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

    @Override
    public boolean delete(int id) {
        return false;
    }
}

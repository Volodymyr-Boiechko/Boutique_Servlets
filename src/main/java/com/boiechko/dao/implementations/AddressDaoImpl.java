package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.AddressDao;
import com.boiechko.entity.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    @Override
    public List<Address> getAddressesOfUser(int userID) {

        String query = "SELECT * FROM address WHERE idPerson = ? ORDER BY idAddress DESC";
        PreparedStatement preparedStatement = null;

        List<Address> list = new ArrayList<>();

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Address address = new Address();

                address.setIdAddress(rs.getInt("idAddress"));
                address.setIdPerson(rs.getInt("idPerson"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setPostCode(rs.getString("postCode"));

                list.add(address);
                
            }

            return list;

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

    @Override
    public boolean add(Address address) {

        String query = "INSERT INTO address(idPerson, country, city, street, postCode)" +
                "VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, address.getIdPerson());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getPostCode());

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
    public Address getById(int id) {

        String query = "SELECT * FROM address WHERE idAddress = ?";
        PreparedStatement preparedStatement = null;

        Address address = new Address();

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                address.setIdAddress(rs.getInt("idAddress"));
                address.setIdPerson(rs.getInt("idPerson"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setPostCode(rs.getString("postCode"));

            }

            return address;

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

    @Override
    public List<Address> getAll() {

        String query = "SELECT * FROM address";
        Statement statement = null;

        List<Address> list = new ArrayList<>();

        try {
            statement = DBConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                Address address = new Address();

                address.setIdAddress(rs.getInt("idAddress"));
                address.setIdPerson(rs.getInt("idPerson"));
                address.setCountry(rs.getString("country"));
                address.setCity(rs.getString("city"));
                address.setStreet(rs.getString("street"));
                address.setPostCode(rs.getString("postCode"));

                list.add(address);
            }

            return list;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(Address address) {

        String query = "UPDATE address SET idPerson = ?, country = ?, city = ?, street = ?, postCode = ? " +
                "WHERE idAddress = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, address.getIdPerson());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5 , address.getPostCode());
            preparedStatement.setInt(6, address.getIdAddress());

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

        String query = "DELETE FROM address WHERE idAddress = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

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
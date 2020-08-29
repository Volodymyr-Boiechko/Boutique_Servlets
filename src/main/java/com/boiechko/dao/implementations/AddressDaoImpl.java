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
    public List<Address> getAddressesOfUser(final int userID) {

        final String query = "SELECT * FROM address WHERE idPerson = ? ORDER BY idAddress DESC";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();

            List<Address> list = new ArrayList<>();

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
        }
    }

    @Override
    public boolean add(final Address address) {

        final String query = "INSERT INTO address(idPerson, country, city, street, postCode)" +
                "VALUES (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, address.getIdPerson());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getPostCode());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public Address getById(final int id) {

        final String query = "SELECT * FROM address WHERE idAddress = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            Address address = new Address();

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
        }
    }

    @Override
    public List<Address> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(final Address address) {

        final String query = "UPDATE address SET idPerson = ?, country = ?, city = ?, street = ?, postCode = ? " +
                "WHERE idAddress = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, address.getIdPerson());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getPostCode());
            preparedStatement.setInt(6, address.getIdAddress());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(final int id) {

        final String query = "DELETE FROM address WHERE idAddress = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }
}
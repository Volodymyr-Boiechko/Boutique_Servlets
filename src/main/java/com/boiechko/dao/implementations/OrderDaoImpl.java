package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.OrderDao;
import com.boiechko.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean add(Order order) {

        String query = "INSERT INTO `order` (idPerson, totalPrice, timeOrder) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1 , order.getIdOrder());
            preparedStatement.setInt(2, order.getTotalPrice());
            preparedStatement.setDate(3, order.getTimeOrder());

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
    public List<Order> getAllById(int id) {

        String query = "SELECT * FROM `order` WHERE idPerson = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();

            while (rs.next()) {

                Order order = new Order();

                order.setIdOrder(rs.getInt("idOrder"));
                order.setIdPerson(rs.getInt("idPerson"));
                order.setIdPerson(rs.getInt("totalPrice"));
                order.setTimeOrder(rs.getDate("timeOrder"));

                orders.add(order);

            }

            return orders;

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


}

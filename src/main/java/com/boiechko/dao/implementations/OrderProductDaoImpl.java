package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.OrderProductDao;
import com.boiechko.entity.OrderProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDaoImpl implements OrderProductDao {

    @Override
    public boolean add(OrderProduct orderProduct) {

        String query = "INSERT INTO order_product (idOrder, idProduct, quantity) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, orderProduct.getIdOrder());
            preparedStatement.setInt(2, orderProduct.getIdProduct());
            preparedStatement.setInt(3, orderProduct.getQuantity());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

}
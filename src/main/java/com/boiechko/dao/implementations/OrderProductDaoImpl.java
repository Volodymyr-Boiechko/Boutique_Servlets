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

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, orderProduct.getIdOrder());
            preparedStatement.setInt(2, orderProduct.getIdProduct());
            preparedStatement.setInt(3, orderProduct.getQuantity());

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
    public List<OrderProduct> getAllByIdOrder(int id) {

        String query = "SELECT * FROM order_product WHERE idOrder = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            List<OrderProduct> list = new ArrayList<>();

            while (rs.next()) {

                OrderProduct orderProduct = new OrderProduct();

                orderProduct.setIdOrderProduct(rs.getInt("idOrderProduct"));
                orderProduct.setIdOrder(rs.getInt("idOrder"));
                orderProduct.setIdProduct(rs.getInt("idProduct"));
                orderProduct.setQuantity(rs.getInt("quantity"));

                list.add(orderProduct);

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
}

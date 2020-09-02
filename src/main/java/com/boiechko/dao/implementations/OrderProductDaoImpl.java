package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.OrderProductDao;
import com.boiechko.entity.OrderProduct;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderProductDaoImpl implements OrderProductDao {

    private final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public boolean add(final OrderProduct orderProduct) {

        final String query = "INSERT INTO order_product (idOrder, idProduct, quantity) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, orderProduct.getIdOrder());
            preparedStatement.setInt(2, orderProduct.getIdProduct());
            preparedStatement.setInt(3, orderProduct.getQuantity());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return false;
        }
    }

}
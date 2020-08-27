package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.OrderDao;
import com.boiechko.entity.Order;
import com.boiechko.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean add(Order order) {

        String query = "INSERT INTO `order` (idPerson, totalPrice, timeOrder) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, order.getIdPerson());
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

    @Override
    public int getLastId() {

        String query = "SELECT MAX(idOrder) FROM `order`";

        try(Statement statement = DBConnection.getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);

            return rs.next() ? rs.getInt("MAX(idOrder)") : -1;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1;
        }
    }

    @Override
    public Map<Order, List<Product>> getAllOrdersAndTheirProducts(int idUser) {

        String query = "SELECT " +
                "`order`.idOrder, `order`.idPerson, `order`.totalPrice, `order`.timeOrder, " +
                "product.idProduct, product.typeName, product.productName, product.sex, product.brand, " +
                "product.model, product.size, product.color, product.image, product.price, product.description, " +
                "order_product.quantity " +
                "FROM order_product " +
                "INNER JOIN boutique_servlets.order ON order_product.idOrder = boutique_servlets.order.idOrder " +
                "INNER JOIN product ON product.idProduct = order_product.idProduct " +
                "WHERE idPerson = ?;";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idUser);

            return getOrderListMap(preparedStatement);

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return null;

        }


    }

    @Override
    public Map<Order, List<Product>> getOrderAndHisProducts(int idUser, int idOrder) {

        String query = "SELECT " +
                "`order`.idOrder, `order`.idPerson, `order`.totalPrice, `order`.timeOrder, " +
                "product.idProduct, product.typeName, product.productName, product.sex, product.brand, " +
                "product.model, product.size, product.color, product.image, product.price, product.description, " +
                "order_product.quantity " +
                "FROM order_product " +
                "INNER JOIN boutique_servlets.order ON order_product.idOrder = boutique_servlets.order.idOrder " +
                "INNER JOIN product ON product.idProduct = order_product.idProduct " +
                "WHERE idPerson = ? AND `order`.idOrder = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idOrder);

            return getOrderListMap(preparedStatement);

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return null;

        }
    }

    private Map<Order, List<Product>> getOrderListMap(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();

        Map<Order, List<Product>> orderListMap = new HashMap<>();
        List<Product> products = new ArrayList<>();

        Order order = new Order();
        Order copy = new Order();

        while (rs.next()) {

            order.setIdOrder(rs.getInt("idOrder"));
            order.setIdPerson(rs.getInt("idPerson"));
            order.setTotalPrice(rs.getInt("totalPrice"));
            order.setTimeOrder(rs.getDate("timeOrder"));

            if (!order.equals(copy) && copy.getIdOrder() != 0) {

                orderListMap.put(copy, new ArrayList<>(products));
                products.clear();

            }

            Product product = new Product();

            product.setIdProduct(rs.getInt("idProduct"));
            product.setTypeName(rs.getString("typeName"));
            product.setProductName(rs.getString("productName"));
            product.setSex(rs.getString("sex"));
            product.setBrand(rs.getString("brand"));
            product.setModel(rs.getString("model"));
            product.setSize(rs.getString("size"));
            product.setColor(rs.getString("color"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getInt("price"));
            product.setDescription(rs.getString("description"));
            product.setQuantity(rs.getInt("quantity"));

            products.add(product);

            copy = new Order(order);

        }

        if (products.size() != 0)
            orderListMap.put(order, products);

        return orderListMap;
    }
}
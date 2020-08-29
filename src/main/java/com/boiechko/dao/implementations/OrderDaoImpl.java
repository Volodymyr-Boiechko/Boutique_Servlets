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
    public boolean add(final Order order) {

        final String query = "INSERT INTO `order` (idPerson, idAddress, totalPrice, timeOrder) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, order.getIdPerson());
            preparedStatement.setInt(2, order.getIdAddress());
            preparedStatement.setInt(3, order.getTotalPrice());
            preparedStatement.setDate(4, order.getTimeOrder());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getAllById(final int id) {

        final String query = "SELECT * FROM `order` WHERE idPerson = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();

            while (rs.next()) {

                Order order = new Order();

                order.setIdOrder(rs.getInt("idOrder"));
                order.setIdPerson(rs.getInt("idPerson"));
                order.setIdPerson(rs.getInt("idAddress"));
                order.setIdPerson(rs.getInt("totalPrice"));
                order.setTimeOrder(rs.getDate("timeOrder"));

                orders.add(order);

            }

            return orders;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public int getLastId() {

        final String query = "SELECT MAX(idOrder) FROM `order`";

        try (Statement statement = DBConnection.getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);

            return rs.next() ? rs.getInt("MAX(idOrder)") : -1;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return -1;
        }
    }

    @Override
    public Map<Order, List<Product>> getAllOrdersAndTheirProducts(final int idUser) {

        final String query = "SELECT " +
                "`order`.idOrder, `order`.idPerson, `order`.idAddress, `order`.totalPrice, `order`.timeOrder, " +
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
    public Map<Order, List<Product>> getOrderAndHisProducts(final int idUser, final int idOrder) {

        final String query = "SELECT " +
                "`order`.idOrder, `order`.idPerson, `order`.idAddress, `order`.totalPrice, `order`.timeOrder, " +
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

    private Map<Order, List<Product>> getOrderListMap(final PreparedStatement preparedStatement) throws SQLException {

        ResultSet rs = preparedStatement.executeQuery();

        Map<Order, List<Product>> orderListMap = new HashMap<>();
        List<Product> products = new ArrayList<>();

        Order order = new Order();
        Order copy = new Order();

        while (rs.next()) {

            order.setIdOrder(rs.getInt("idOrder"));
            order.setIdPerson(rs.getInt("idPerson"));
            order.setIdAddress(rs.getInt("idAddress"));
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
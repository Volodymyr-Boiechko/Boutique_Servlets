package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.OrderDao;
import com.boiechko.entity.Order;
import com.boiechko.entity.Product;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private final Logger logger = Logger.getLogger(OrderDaoImpl.class);

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
            logger.error(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> getAllOrdersByPersonId(final int idPerson) {

        final String query = "SELECT * FROM `order` WHERE idPerson = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idPerson);
            ResultSet rs = preparedStatement.executeQuery();

            return getOrderList(rs);

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
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
            logger.error(sqlException.getMessage());
            return -1;
        }
    }

    @Override
    public List<Order> getAllOrdersByAddressId(int idAddress) {

        final String query = "SELECT * FROM `order` WHERE idAddress = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idAddress);
            ResultSet rs = preparedStatement.executeQuery();

            return getOrderList(rs);

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return null;
        }

    }

    @Override
    public Map<Order, List<Product>> getAllOrdersAndTheirProductsOfPerson(final int idPerson) {

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

            preparedStatement.setInt(1, idPerson);

            return getOrderListMap(preparedStatement);

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            return null;
        }
    }

    @Override
    public Map<Order, List<Product>> getOrderAndItsProductsOfPerson(final int idPerson, final int idOrder) {

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

            preparedStatement.setInt(1, idPerson);
            preparedStatement.setInt(2, idOrder);

            return getOrderListMap(preparedStatement);

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
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

    private List<Order> getOrderList(final ResultSet rs) throws SQLException {

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

    }
}
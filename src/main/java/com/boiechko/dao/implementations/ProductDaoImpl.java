package com.boiechko.dao.implementations;

import com.boiechko.config.DBConnection;
import com.boiechko.dao.interfaces.ProductDao;
import com.boiechko.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> getProductByColumn(final String column, final String credentials) {

        final String query = "SELECT * FROM product WHERE " + column + " = ? ORDER BY RAND()";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, credentials);
            ResultSet rs = preparedStatement.executeQuery();

            return getListProducts(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getLatestAddedProducts() {

        final String query = "SELECT * FROM product ORDER BY idProduct DESC";

        try (Statement statement = DBConnection.getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);

            return getListProducts(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> groupByColumnWithCondition(final String condition, final String statement, final String column) {

        final String query = "SELECT * FROM product WHERE " + condition + " = ? GROUP BY " + column;

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, statement);
            ResultSet rs = preparedStatement.executeQuery();

            return getListProducts(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> groupByColumn(final String column) {

        final String query = "SELECT * FROM product GROUP BY " + column;

        try (Statement statement = DBConnection.getConnection().createStatement();) {

            ResultSet rs = statement.executeQuery(query);

            return getListProducts(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(final Product product) {

        final String query = "INSERT INTO product(typeName, productName, sex, brand, model, size, color, image, price, description)" +
                "VALUE (?,?,?,?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, product.getTypeName());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getSex());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getModel());
            preparedStatement.setString(6, product.getSize());
            preparedStatement.setString(7, product.getColor());
            preparedStatement.setString(8, product.getImage());
            preparedStatement.setInt(9, product.getPrice());
            preparedStatement.setString(10, product.getDescription());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getById(final int idProduct) {

        final String query = "SELECT * FROM product WHERE idProduct = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idProduct);
            ResultSet rs = preparedStatement.executeQuery();

            Product product = new Product();

            if (rs.next()) product = getProduct(rs);

            return product;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getAll() {

        final String query = "SELECT * FROM product";

        try (Statement statement = DBConnection.getConnection().createStatement()) {

            ResultSet rs = statement.executeQuery(query);

            return getListProducts(rs);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(final Product product) {

        final String query = "UPDATE product SET typeName = ?, productName = ?, sex = ?, brand = ?, model = ?," +
                "size = ?, color = ?, image = ?, price = ?, description = ? WHERE idProduct = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, product.getTypeName());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getSex());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setString(5, product.getModel());
            preparedStatement.setString(6, product.getSize());
            preparedStatement.setString(7, product.getColor());
            preparedStatement.setString(8, product.getImage());
            preparedStatement.setInt(9, product.getPrice());
            preparedStatement.setString(10, product.getDescription());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(final int idProduct) {

        final String query = "DELETE FROM product WHERE idProduct = ?";

        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, idProduct);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    private List<Product> getListProducts(final ResultSet rs) throws SQLException {

        List<Product> list = new ArrayList<>();

        while (rs.next()) {

            Product product = getProduct(rs);

            list.add(product);

        }

        return list;
    }

    private Product getProduct(final ResultSet rs) throws SQLException {

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

        return product;

    }
}
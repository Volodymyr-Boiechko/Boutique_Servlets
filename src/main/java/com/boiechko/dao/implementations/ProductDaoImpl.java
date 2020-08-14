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
    public List<Product> getAllByCredentials(String column, String credentials) {

        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM product WHERE " + column + " = ? ORDER BY RAND()";

        List<Product> list = new ArrayList<>();

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, credentials);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

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

                list.add(product);

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
    public List<Product> getNewest() {

        String query = "SELECT * FROM product ORDER BY idProduct DESC";
        PreparedStatement preparedStatement = null;

        List<Product> products = new ArrayList<>();

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

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

                products.add(product);

            }
            return products;

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
    public List<Product> getUniqueFields(String uniqueColumn, String condition, String statement) {

        String query = "SELECT * FROM product WHERE " + condition + " = ? GROUP BY " + uniqueColumn;
        PreparedStatement preparedStatement = null;

        List<Product> list = new ArrayList<>();

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, statement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

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

                list.add(product);
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
    public List<Product> groupBy(String column) {

        String query = "SELECT * FROM product GROUP BY " + column;
        Statement statement = null;

        List<Product> list = new ArrayList<>();

        try {
            statement = DBConnection.getConnection().createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

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

                list.add(product);

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
    public boolean add(Product product) {

        String query = "INSERT INTO product(typeName, productName, sex, brand, model, size, color, image, price, description)" +
                "VALUE (?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

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
    public Product getById(int id) {

        String query = "SELECT * FROM product WHERE idProduct = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            Product product = new Product();

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

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

            }

            return product;

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
    public List<Product> getAll() {

        String query = "SELECT * FROM product";

        PreparedStatement preparedStatement = null;
        List<Product> list = new ArrayList<>();

        try {

            preparedStatement = DBConnection.getConnection().prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

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

                list.add(product);

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
    public boolean update(Product product) {

        String query = "UPDATE product SET typeName = ?, productName = ?, sex = ?, brand = ?, model = ?," +
                "size = ?, color = ?, image = ?, price = ?, description = ? WHERE idProduct = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);

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

        String query = "DELETE FROM product WHERE idProduct = ?";
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
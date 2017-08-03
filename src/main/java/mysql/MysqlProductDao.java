package mysql;

import dao.ProductDao;
import product.Barcode;
import product.Category;
import product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MysqlProductDao implements ProductDao {

    private final Connection connection;

    public MysqlProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product create() {
        return null;
    }

    @Override
    public Product read(int key) throws SQLException {
        String sql = "SELECT * FROM GoodsNewEra.ProductsV2 WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, key);

        ResultSet resultSet = preparedStatement.executeQuery();

        Product p = new Product();
        p.setId(resultSet.getInt("id"));
        p.setName(resultSet.getString("name"));
        p.setCategory(new Category(resultSet.getString("category")));
        p.setBarcode(new Barcode(String.valueOf(resultSet.getInt("barcode"))));
        p.setDescription(resultSet.getString("description"));
        p.setConsist(resultSet.getString("consist"));
        p.setWeight(resultSet.getString("weight"));
        p.setExpirationDate(resultSet.getString("expirationDate"));
        p.setEnergyConsist(resultSet.getString("energyConsist"));
        p.setStoreRules(resultSet.getString("storeRules"));
        p.setManufacturer(resultSet.getString("manufacturer"));

        return p;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public LinkedList<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM GoodsNewEra.ProductsV2";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        LinkedList<Product> products = new LinkedList<>();

        while(resultSet.next()) {
            Product p = new Product();
            p.setId(resultSet.getInt("id"));
            p.setName(resultSet.getString("name"));
            p.setCategory(new Category(resultSet.getString("category")));
            p.setBarcode(new Barcode(String.valueOf(resultSet.getInt("barcode"))));
            p.setDescription(resultSet.getString("description"));
            p.setConsist(resultSet.getString("consist"));
            p.setWeight(resultSet.getString("weight"));
            p.setExpirationDate(resultSet.getString("expirationDate"));
            p.setEnergyConsist(resultSet.getString("energyConsist"));
            p.setStoreRules(resultSet.getString("storeRules"));
            p.setManufacturer(resultSet.getString("manufacturer"));

            products.add(p);
        }

        return products;
    }
}

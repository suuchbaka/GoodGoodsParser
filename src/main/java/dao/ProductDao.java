package dao;

import product.Product;

import java.sql.SQLException;
import java.util.LinkedList;

public interface ProductDao {
    public Product create();

    public Product read(int key) throws SQLException;

    public void update(Product product);

    public void delete(Product product);

    public LinkedList<Product> getAll() throws SQLException;
}

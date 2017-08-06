package dao;

import product.Category;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by User on 06.08.2017.
 */
public interface CategoryDao {
    public Category create() throws SQLException;
    public Category read() throws SQLException;
    public Category getByPK(int key) throws SQLException;
    public LinkedList<Category> getAll() throws SQLException;
}

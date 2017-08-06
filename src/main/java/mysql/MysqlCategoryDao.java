package mysql;

import dao.CategoryDao;
import product.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by User on 06.08.2017.
 */
public class MysqlCategoryDao implements CategoryDao {

    private final Connection connection;

    public MysqlCategoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Category create() throws SQLException {
        return null;
    }

    @Override
    public Category read() throws SQLException {
        return null;
    }

    @Override
    public Category getByPK(int key) throws SQLException {
        String sql =
    }

    @Override
    public LinkedList<Category> getAll() throws SQLException {
        return null;
    }
}

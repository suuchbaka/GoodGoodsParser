package mysql;

import dao.DaoFactory;
import dao.ProductDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {

    private String user = "root";
    private String password = "285163";
    private String url = "jdbc:mysql://http://194.67.194.24:3306/GoodsNewEra";
    private String driver = "com.mysql.jdbc.Driver";


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public ProductDao getProductDao(Connection connection) {
        return new MysqlProductDao(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package mysql;

import dao.BarcodeDao;
import product.Barcode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Created by User on 06.08.2017.
 */
public class MysqlBarcodeDao implements BarcodeDao {

    private final Connection connection;

    public MysqlBarcodeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public LinkedList<Barcode> getAll() throws SQLException {
        String sql = "SELECT barcode FROM GoodsNewEra.Products";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        LinkedList<Barcode> barcodes = new LinkedList<>();

        while(resultSet.next()) {
            barcodes.add(new Barcode(resultSet.getString("barcode")));
        }

        return barcodes;
    }
}

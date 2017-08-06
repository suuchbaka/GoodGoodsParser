package dao;

import product.Barcode;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by User on 06.08.2017.
 */
public interface BarcodeDao {
    public LinkedList<Barcode> getAll() throws SQLException;
}

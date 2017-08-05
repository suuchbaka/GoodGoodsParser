package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 04.08.2017.
 */
public interface GenericDao<T, PK extends Serializable>{
    public T create() throws SQLException;

    public T perstists(T object) throws SQLException;

    public T getByPK(int key) throws SQLException, PersistenceException;

    public void update(T object) throws SQLException;

    public void delete(T object) throws SQLException;

    public List<T> getAll() throws SQLException;
}

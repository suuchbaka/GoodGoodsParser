package dao;

import com.sun.rmi.rmid.ExecPermission;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 04.08.2017.
 */
public abstract class AbstractDao<T, PK extends Serializable> implements GenericDao<T,PK> {
    private Connection connection;

    protected abstract String getSelectedQuery();
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    @Override
    public T getByPK(int key) throws PersistException {
        List<T> list;

        String sql = getSelectedQuery();

        sql+= " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, key);
            ResultSet rs = preparedStatement.executeQuery();

            list = parseResultSet(rs);
        }   catch (Exception e ) {
            throw new PersistException(e);
        }

        if(list == null || list.size() == 0 ) {
            return null;
        }

        if(list.size() > 1) {
            throw new PersistException("Recieved more than one record");
        }

        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws SQLException {
        List<T> list;
        String sql = getSelectedQuery();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            list = parseResultSet(rs);
        }   catch (Exception e) {
            throw new PersistException(e);
        }

        return list;
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForDelete(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}

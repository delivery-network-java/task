package org.tyrell.task.persistence.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository <T> {

    void setConnection(Connection connection);

    List<T> findAll() throws SQLException;

    T findById(Long id) throws SQLException;

    T persist(T entity) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(Long id) throws SQLException;

}

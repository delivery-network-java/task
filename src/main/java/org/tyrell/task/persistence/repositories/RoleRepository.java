package org.tyrell.task.persistence.repositories;

import org.tyrell.task.domain.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements Repository<Role> {

    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        List<Role> roles = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_app.roles")
        ) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("roe_id"));
                role.setName(resultSet.getString("roe_name"));
                roles.add(role);
            }
        }

        return roles;
    }

    @Override
    public Role findById(Long id) throws SQLException {
        Role role = null;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_app.roles WHERE roe_id = ?");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getLong("roe_id"));
                role.setName(resultSet.getString("roe_name"));
            }
        }

        return role;
    }

    @Override
    public Role persist(Role entity) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_app.roles (roe_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            }
        }

        return entity;
    }

    @Override
    public Role update(Role entity) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_app.roles SET roe_name = ? WHERE roe_id = ?")
        ) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        }

        return entity;
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_app.roles WHERE roe_id = ?")
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

}

package org.tyrell.task.persistence.repositories;

import org.tyrell.task.domain.entities.Role;
import org.tyrell.task.persistence.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements Repository<Role> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();

        try (
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_app.roles")
        ) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("roe_id"));
                role.setName(resultSet.getString("roe_name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    @Override
    public Role findById(Long id) {
        Role role = null;

        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM user_app.roles WHERE roe_id = ?");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getLong("roe_id"));
                role.setName(resultSet.getString("roe_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public Role persist(Role entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO user_app.roles (roe_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return entity;
    }

    @Override
    public Role update(Role entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE user_app.roles SET roe_name = ? WHERE roe_id = ?")
        ) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return entity;
    }

    @Override
    public void delete(Long id) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM user_app.roles WHERE roe_id = ?")
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

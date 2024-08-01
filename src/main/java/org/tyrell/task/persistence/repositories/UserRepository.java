package org.tyrell.task.persistence.repositories;

import org.tyrell.task.domain.entities.User;
import org.tyrell.task.persistence.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_app.users")
        ) {
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("usr_id"));
                user.setUsername(resultSet.getString("usr_username"));
                user.setPassword(resultSet.getString("usr_password"));
                user.setDocument(resultSet.getInt("usr_document"));
                user.setDocumentType(resultSet.getInt("usr_document_type"));
                user.setFirstName(resultSet.getString("usr_first_name"));
                user.setLastName(resultSet.getString("usr_last_name"));
                user.setEmail(resultSet.getString("usr_email"));
                user.setPhone(resultSet.getString("usr_phone"));
                user.setStatus(resultSet.getInt("usr_status"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;

        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM user_app.users WHERE usr_id = ?");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("usr_id"));
                user.setUsername(resultSet.getString("usr_username"));
                user.setPassword(resultSet.getString("usr_password"));
                user.setDocument(resultSet.getInt("usr_document"));
                user.setDocumentType(resultSet.getInt("usr_document_type"));
                user.setFirstName(resultSet.getString("usr_first_name"));
                user.setLastName(resultSet.getString("usr_last_name"));
                user.setEmail(resultSet.getString("usr_email"));
                user.setPhone(resultSet.getString("usr_phone"));
                user.setStatus(resultSet.getInt("usr_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User persist(User entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO user_app.users (usr_document, usr_document_type, usr_username, usr_password, usr_email, usr_first_name, usr_last_name, usr_phone, usr_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, entity.getDocument());
            preparedStatement.setInt(2, entity.getDocumentType());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getFirstName());
            preparedStatement.setString(7, entity.getLastName());
            preparedStatement.setString(8, entity.getPhone());
            preparedStatement.setInt(9, 1);// 1 es status activo

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
    public User update(User entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE user_app.users SET usr_document = ?, usr_document_type = ?, usr_username = ?, usr_password = ?, usr_email = ?, usr_first_name = ?, usr_last_name = ?, usr_phone = ?, usr_status = ? WHERE usr_id = ?")
        ) {
            preparedStatement.setInt(1, entity.getDocument());
            preparedStatement.setInt(2, entity.getDocumentType());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setString(4, entity.getPassword());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getFirstName());
            preparedStatement.setString(7, entity.getLastName());
            preparedStatement.setString(8, entity.getPhone());
            preparedStatement.setInt(9, entity.getStatus());
            preparedStatement.setLong(10, entity.getId());

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
                PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM user_app.users WHERE usr_id = ?")
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

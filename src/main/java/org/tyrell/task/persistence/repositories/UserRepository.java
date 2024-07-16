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
                user.setDocument(resultSet.getInt("usr_document"));
                user.setPassword(resultSet.getString("usr_password"));
                user.setDocumentType(resultSet.getInt("usr_document_type"));
                user.setFirstName(resultSet.getString("usr_first_name"));
                user.setLastName(resultSet.getString("usr_last_name"));
                user.setEmail(resultSet.getString("usr_email"));
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
                user.setDocument(resultSet.getInt("usr_document"));
                user.setPassword(resultSet.getString("usr_password"));
                user.setDocumentType(resultSet.getInt("usr_document_type"));
                user.setFirstName(resultSet.getString("usr_first_name"));
                user.setLastName(resultSet.getString("usr_last_name"));
                user.setEmail(resultSet.getString("usr_email"));
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
                PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO user_app.users (usr_username, usr_password, usr_document, usr_document_type, usr_first_name, usr_last_name, usr_phone, usr_email) VALUES (? ? ? ? ? ? ? ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3, entity.getDocument());
            preparedStatement.setInt(4, entity.getDocumentType());
            preparedStatement.setString(5, entity.getFirstName());
            preparedStatement.setString(6, entity.getLastName());
            preparedStatement.setString(7, entity.getPhone());
            preparedStatement.setString(8, entity.getEmail());
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
                PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE user_app.users SET usr_username = ?, usr_password = ?, usr_document = ?, usr_document_type = ?, usr_first_name = ?, usr_last_name = ?, usr_phone = ?, usr_email = ? WHERE usr_id = ?")
        ) {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3, entity.getDocument());
            preparedStatement.setInt(4, entity.getDocumentType());
            preparedStatement.setString(5, entity.getFirstName());
            preparedStatement.setString(6, entity.getLastName());
            preparedStatement.setString(7, entity.getPhone());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setLong(9, entity.getId());
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

package org.tyrell.task.persistence.repositories;

import org.tyrell.task.domain.entities.RecurringTask;
import org.tyrell.task.persistence.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecurringTaskRepository implements Repository<RecurringTask> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance();
    }

    @Override
    public List<RecurringTask> findAll() {
        List<RecurringTask> recurringTask = new ArrayList<>();

        try (
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM task.recurring_tasks")) {
            while (resultSet.next()) {
                RecurringTask recurringTas = new RecurringTask();
                recurringTas.setId(resultSet.getLong("rtk_id"));
                recurringTas.setTitle(resultSet.getString("rtk_title"));
                recurringTas.setDescription(resultSet.getString("rtk_description"));
                recurringTas.setStatus(resultSet.getInt("rtk_status"));
                recurringTas.setFrequency(resultSet.getString("rtk_frequency"));

                recurringTask.add(recurringTas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recurringTask;
    }

    @Override
    public RecurringTask findById(Long id) {
        RecurringTask recurringTask = null;

        try (
                PreparedStatement preparedStatement = getConnection()
                        .prepareStatement("SELECT * FROM task.recurring_tasks WHERE rtk_id = ?");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                recurringTask = new RecurringTask();
                recurringTask.setId(resultSet.getLong("rtk_id"));
                recurringTask.setTitle(resultSet.getString("rtk_title"));
                recurringTask.setDescription(resultSet.getString("rtk_description"));
                recurringTask.setStatus(resultSet.getInt("rtk_status"));
                recurringTask.setFrequency(resultSet.getString("rtk_frequency"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recurringTask;
    }

    @Override
    public RecurringTask persist(RecurringTask entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement(
                        "INSERT INTO task.recurring_tasks (rtk_title, rtk_description, rtk_status, rtk_frequency) VALUES (?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getStatus()); // Asumiendo que status es un entero
            preparedStatement.setString(4, entity.getFrequency());

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
    public RecurringTask update(RecurringTask entity) {
        try (
                PreparedStatement preparedStatement = getConnection()
                        .prepareStatement("UPDATE task.recurring_tasks SET rtk_title = ? WHERE rtk_id = ?")) {
            preparedStatement.setString(1, entity.getTitle());
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
                PreparedStatement preparedStatement = getConnection()
                        .prepareStatement("DELETE FROM task.recurring_tasks WHERE rtk_id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

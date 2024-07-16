package org.tyrell.task.persistence.repositories;

import org.tyrell.task.domain.entities.DeadlineTask;
import org.tyrell.task.persistence.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeadlineTaskRepository implements Repository<DeadlineTask> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance();
    }

    @Override
    public List<DeadlineTask> findAll() {
        List<DeadlineTask> deadlineTasks = new ArrayList<>();

        try (
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM task.deadline_Tasks")
        ) {
            while (resultSet.next()) {
                DeadlineTask deadlineTask = new DeadlineTask();
                deadlineTask.setId(resultSet.getLong("dtk_id"));
                deadlineTask.setTitle(resultSet.getString("dtk_title"));
                deadlineTask.setDescription(resultSet.getString("dtk_description"));
                deadlineTask.setStatus(resultSet.getInt("dtk_status"));
                deadlineTask.setEndDate(resultSet.getDate("dtk_end_date"));
                deadlineTasks.add(deadlineTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deadlineTasks;
    }

    @Override
    public DeadlineTask findById(Long id) {
        DeadlineTask deadlineTask = null;

        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM task.deadline_Tasks WHERE dtk_id = ?");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                deadlineTask = new DeadlineTask();
                deadlineTask.setId(resultSet.getLong("dtk_id"));
                deadlineTask.setTitle(resultSet.getString("dtk_title"));
                deadlineTask.setDescription(resultSet.getString("dtk_description"));
                deadlineTask.setStatus(resultSet.getInt("dtk_status"));
                deadlineTask.setEndDate(resultSet.getDate("dtk_end_date"));
                deadlineTask.setUser(new UserRepository().findById(resultSet.getLong("usr_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deadlineTask;
    }

    @Override
    public DeadlineTask persist(DeadlineTask entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO task.deadline_Tasks (dtk_title, dtk_description, usr_id, dtk_status, dtk_end_date) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getUser().getId());
            preparedStatement.setInt(4, entity.getStatus());
            java.sql.Date sqlDate = new java.sql.Date(entity.getEndDate().getTime());
            preparedStatement.setDate(5, sqlDate);
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
    public DeadlineTask update(DeadlineTask entity) {
        try (
                PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE task.deadline_Tasks SET dtk_title = ?, dtk_description = ? WHERE dtk_id = ?")
        ) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getId());
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
                PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM task.deadline_Tasks WHERE dtk_id = ?")
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

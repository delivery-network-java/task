package src.org.tyrell.task.factory_example.services;

import src.org.tyrell.task.factory_example.factory.TaskFactory;
import src.org.tyrell.task.factory_example.entities.Task;

import java.util.List;
import java.util.Optional;

public class RecurringTaskService implements TaskFactory {

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> listAll() {
        return null;
    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void edit(Task task) {

    }

    @Override
    public void delete(Task task) {

    }

}

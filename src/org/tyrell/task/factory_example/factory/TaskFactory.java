package src.org.tyrell.task.factory_example.factory;

import src.org.tyrell.task.factory_example.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskFactory {

    Optional<Task> findById(Long id);
    List<Task> listAll();
    void create(Task task);
    void edit(Task task);
    void delete(Task task);

}

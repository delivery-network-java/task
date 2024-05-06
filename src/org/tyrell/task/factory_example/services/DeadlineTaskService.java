package src.org.tyrell.task.factory_example.services;

import src.org.tyrell.task.factory_example.factory.TaskFactory;
import src.org.tyrell.task.factory_example.entities.Task;

import java.util.List;
import java.util.Optional;

import static src.org.tyrell.task.factory_example.DataBase.getDeadlineTaskList;

public class DeadlineTaskService implements TaskFactory {

    @Override
    public Optional<Task> findById(Long id) {
        return getDeadlineTaskList
                .stream()
                .filter(task -> task.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Task> listAll() {
        return getDeadlineTaskList;
    }

    @Override
    public void create(Task task) {
        getDeadlineTaskList.add(task);
        System.out.println("Deadline task created: " + task.getId());
    }

    @Override
    public void edit(Task task) {
        Optional<Task> oldTask = findById(task.getId());

        if (oldTask.isPresent()) {
            for (var currentTask: getDeadlineTaskList) {
                if (currentTask.getId().equals(task.getId())) {
                    currentTask.setTitle(task.getTitle());
                    currentTask.setDescription(task.getDescription());
                    System.out.println("Deadline task updated: " + currentTask.getId());
                }
            }
        } else {
            System.out.println("Deadline task not found: " + task.getId());
        }
    }

    @Override
    public void delete(Task task) {
        Optional<Task> currentTask = findById(task.getId());

        if (currentTask.isPresent()) {
            getDeadlineTaskList.remove(task);
            System.out.println("Deadline task deleted: " + task.getId());
        } else {
            System.out.println("Deadline task not found: " + task.getId());
        }
    }

}

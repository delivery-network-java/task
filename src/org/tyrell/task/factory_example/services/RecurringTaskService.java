package src.org.tyrell.task.factory_example.services;

import src.org.tyrell.task.factory_example.entities.RecurringTask;
import src.org.tyrell.task.factory_example.factory.TaskFactory;
import src.org.tyrell.task.factory_example.entities.Task;

import java.util.List;
import java.util.Optional;

import static src.org.tyrell.task.factory_example.DataBase.getRecurringTaskList;

public class RecurringTaskService implements TaskFactory {

    @Override
    public Optional<Task> findById(Long id) {

        return getRecurringTaskList
                .stream()
                .filter(task -> task.getId().equals(id))
                .map(recurringTask -> (Task) recurringTask)
                .findAny();
    }

    @Override
    public List<Task> listAll() {
        return getRecurringTaskList.stream().map(recurringTask -> (Task) recurringTask).toList();
    }

    @Override
    public void create(Task task) {
        getRecurringTaskList.add((RecurringTask) task);
        System.out.println("Recurring task created: " + task.getId());
    }

    @Override
    public void edit(Task task) {
        Optional<Task> oldTask = findById(task.getId());

        if (oldTask.isPresent()) {
            RecurringTask task1 = (RecurringTask) task;
            for (var currentTask: getRecurringTaskList) {
                if (currentTask.getId().equals(task.getId())) {
                    currentTask.setTitle(task.getTitle());
                    currentTask.setDescription(task.getDescription());
                    currentTask.setFrequency(task1.getFrequency());
                    System.out.println("Recurring task updated: " + currentTask.getId());
                }
            }
        } else {
            System.out.println("Recurring task not found: " + task.getId());
        }
    }

    @Override
    public void delete(Task task) {
        Optional<Task> currentTask = findById(task.getId());

        if (currentTask.isPresent()) {
            getRecurringTaskList.remove((RecurringTask) task);
            System.out.println("Recurring task deleted: " + task.getId());
        } else {
            System.out.println("Recurring task not found: " + task.getId());
        }
    }

}

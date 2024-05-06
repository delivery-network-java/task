package src.org.tyrell.task.factory_example.factory;

import src.org.tyrell.task.factory_example.entities.Task;

import java.util.List;

public abstract class TaskCreator {

    public void listItems() {
        System.out.println("=============== TASKS =================");
        List<Task> taskList = getTask().listAll();

        if (!taskList.isEmpty()) {
            taskList.forEach(System.out::println);
        } else {
            System.out.println("Task list is empty!");
        }
    }

    public abstract TaskFactory getTask();

}

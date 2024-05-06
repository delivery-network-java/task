package  src.org.tyrell.task;

import src.org.tyrell.task.factory_example.entities.DeadlineTask;
import src.org.tyrell.task.factory_example.factory.DeadlineTaskCreator;
import src.org.tyrell.task.factory_example.factory.TaskCreator;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskCreator taskCreator = new DeadlineTaskCreator();
        taskCreator.listItems();

        var taskA = new DeadlineTask("Task A", 1, LocalDate.parse("2024-04-30"));
        taskA.setId(1L);
        taskCreator.getTask().create(taskA);

        taskCreator.listItems();

        var taskB = new DeadlineTask("Task B", 1, LocalDate.parse("2024-04-29"));
        taskB.setId(2L);
        taskCreator.getTask().create(taskB);

        taskCreator.listItems();
    }

}

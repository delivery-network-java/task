package  src.org.tyrell.task;

import src.org.tyrell.task.factory_example.entities.DeadlineTask;
import src.org.tyrell.task.factory_example.entities.RecurringTask;
import src.org.tyrell.task.factory_example.entities.Task;
import src.org.tyrell.task.factory_example.factory.DeadlineTaskCreator;
import src.org.tyrell.task.factory_example.factory.RecurringTaskCreator;
import src.org.tyrell.task.factory_example.factory.TaskCreator;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskCreator taskCreator;
        //taskCreator.listItems();

        /*var taskA = new DeadlineTask("Task A", 1, LocalDate.parse("2024-04-30"));
        taskA.setId(1L);
        taskCreator.getTask().create(taskA);

        taskCreator.listItems();

        taskA = new DeadlineTask("Task B", 1, LocalDate.parse("2024-04-29"));
        taskA.setId(2L);
        taskCreator.getTask().create(taskA);

        //taskCreator.listItems();
        */

        System.out.println("////Recurring Task////");

        taskCreator = new RecurringTaskCreator();
        taskCreator.listItems();

        var taskC = new RecurringTask("Task A", 1, "tres veces por semana");
        taskC.setId(1L);
        taskCreator.getTask().create(taskC);

        taskCreator.listItems();

        taskC = new RecurringTask("Task B", 1, "dos veces por semana");
        taskC.setId(2L);
        taskCreator.getTask().create(taskC);

        var taskE = new RecurringTask("Task B", 1, "cuatro veces por semana");
        taskE.setId(2L);

        taskCreator.getTask().edit(taskE);

        taskCreator.listItems();
    }

}

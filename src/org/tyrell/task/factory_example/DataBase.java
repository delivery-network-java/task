package src.org.tyrell.task.factory_example;

import src.org.tyrell.task.factory_example.entities.DeadlineTask;
import src.org.tyrell.task.factory_example.entities.RecurringTask;
import src.org.tyrell.task.factory_example.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private DataBase() {
    }

    public static final List<DeadlineTask> getDeadlineTaskList = new ArrayList<>();

    public static final List<RecurringTask> getRecurringTaskList = new ArrayList<>();

}

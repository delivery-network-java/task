package src.org.tyrell.task.factory_example.factory;

import src.org.tyrell.task.factory_example.services.DeadlineTaskService;

public class DeadlineTaskCreator extends TaskCreator {

    @Override
    public TaskFactory getTask() {
        return new DeadlineTaskService();
    }

}

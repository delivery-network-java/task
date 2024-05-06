package src.org.tyrell.task.factory_example.entities;

public class RecurringTask extends Task {

    private String frequency;

    public RecurringTask(String title, Integer status, String frequency) {
        super(title, status);
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "RecurringTask{" +
                super.toString() +
                ", frequency='" + frequency + '\'' +
                '}';
    }
}

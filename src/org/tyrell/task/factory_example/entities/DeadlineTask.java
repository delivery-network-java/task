package src.org.tyrell.task.factory_example.entities;

import java.time.LocalDate;

public class DeadlineTask extends Task {

    private LocalDate endDate;

    public DeadlineTask(String title, Integer status, LocalDate endDate) {
        super(title, status);
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DeadlineTask{" +
                super.toString() +
                ", endDate=" + endDate +
                '}';
    }

}

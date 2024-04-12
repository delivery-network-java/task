package  src.org.tyrell.task;

import src.org.tyrell.task.domain.entities.Task;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Task tarea = new Task();
        tarea.setId(1L);
        tarea.setTitle("Crear una tarea");
        tarea.setDescription("Mi primer tarea");
        tarea.setStatus(1);
        System.out.println("tarea = " + tarea);
    }
}
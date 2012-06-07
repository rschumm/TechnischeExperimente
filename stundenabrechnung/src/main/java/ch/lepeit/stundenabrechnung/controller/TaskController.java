package ch.lepeit.stundenabrechnung.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.TaskService;

@Named
@RequestScoped
public class TaskController {

    @EJB
    private TaskService taskService;

    public void deleteTask(String name) {
        this.taskService.delete(name);
    }

    public List<Task> getTasks() {
        return taskService.getTasks();
    }
}

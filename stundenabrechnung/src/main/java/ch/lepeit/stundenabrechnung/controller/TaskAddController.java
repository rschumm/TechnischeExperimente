package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.Buchart;
import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.BuchartService;
import ch.lepeit.stundenabrechnung.service.TaskService;

@Named
@SessionScoped
public class TaskAddController implements Serializable {
    private static final long serialVersionUID = 20120607L;

    private String buchart;

    @EJB
    private BuchartService buchartService;

    private Task task;

    @EJB
    private TaskService taskService;

    public String getBuchart() {
        return buchart;
    }

    public List<Buchart> getBucharten() {
        return buchartService.getBucharten();
    }

    public Task getTask() {
        return task;
    }

    @PostConstruct
    public void init() {
        this.task = new Task();
    }

    public String save() {
        task.setBuchart(buchartService.getBuchart(this.buchart));

        taskService.save(task);

        task = new Task();

        return null;
    }

    public void setBuchart(String buchart) {
        this.buchart = buchart;
    }
}

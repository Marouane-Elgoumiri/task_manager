package com.mr1.task_manager_spring.services;

import com.mr1.task_manager_spring.entities.taskEntity;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class taskService {
    private ArrayList<taskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineformatter = new SimpleDateFormat("dd/MM/yyyy");
    public taskEntity addTask(String title, String description, String deadline) throws ParseException {
        taskEntity task = new taskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineformatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }
    public ArrayList<taskEntity> getTasks(){
        return tasks;
    }

    public taskEntity getTaskById(int id){
        for(taskEntity task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }
    public taskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {
        taskEntity task = getTaskById(id);
        if(task == null){
            return null;
        }
        if(description != null){
            task.setDescription(description);
        }
        if(deadline != null){
            task.setDeadline(deadlineformatter.parse(deadline));
        }
        if(completed != null){
            task.setCompleted(completed);
        }
        return task;
    }
}

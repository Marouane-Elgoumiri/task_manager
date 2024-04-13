package com.mr1.task_manager_spring.services;

import com.mr1.task_manager_spring.entities.taskEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class taskService {
    private ArrayList<taskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    public taskEntity addTask(String title, String description, String deadline){
        taskEntity task = new taskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(new Date(deadline));
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
}

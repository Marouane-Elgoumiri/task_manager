package com.mr1.task_manager_spring.entities;

import lombok.Data;

import java.util.Date;

@Data
public class taskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
}

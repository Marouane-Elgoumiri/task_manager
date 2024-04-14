package com.mr1.task_manager_spring.entities;

import lombok.Data;

@Data
public class noteEntity {
    private int id;
    private String title;
    private String body;
    private String date;
}

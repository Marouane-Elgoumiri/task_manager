package com.mr1.task_manager_spring.dto;

import com.mr1.task_manager_spring.entities.noteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDTO {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<noteEntity> notes;
}

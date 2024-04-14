package com.mr1.task_manager_spring.dto;

import com.mr1.task_manager_spring.entities.noteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponseDTO {
    private Integer taskId;
    private noteEntity note;
}

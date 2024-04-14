package com.mr1.task_manager_spring.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateNoteDTO {
    private String title;
    private String body;
    private String date;
}

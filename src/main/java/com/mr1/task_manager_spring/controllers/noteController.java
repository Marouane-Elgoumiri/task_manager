package com.mr1.task_manager_spring.controllers;

import com.mr1.task_manager_spring.dto.CreateNoteDTO;
import com.mr1.task_manager_spring.dto.CreateNoteResponseDTO;
import com.mr1.task_manager_spring.services.noteService;
import com.mr1.task_manager_spring.entities.noteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class noteController {
    private noteService notesService;

    public noteController(noteService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<noteEntity>> getNotes(@PathVariable("taskId") int taskId) {
        var notes = notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }
    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body) {
        var note = notesService.addNoteForTask(taskId, body.getTitle(), body.getBody(), body.getDate());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }
}

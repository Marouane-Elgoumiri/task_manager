package com.mr1.task_manager_spring.controllers;

import com.mr1.task_manager_spring.dto.CreateTaskDTO;
import com.mr1.task_manager_spring.dto.ErrorResponseDTO;
import com.mr1.task_manager_spring.dto.TaskResponseDTO;
import com.mr1.task_manager_spring.dto.UpdateTaskDTO;
import com.mr1.task_manager_spring.entities.taskEntity;
import com.mr1.task_manager_spring.services.noteService;
import com.mr1.task_manager_spring.services.taskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class taskController {
    private final taskService TaskService;
    private final noteService NoteService;
    private ModelMapper modelMapper= new ModelMapper();

    public taskController(taskService taskService, noteService noteService) {
        this.TaskService = taskService;
        this.NoteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<taskEntity>> getTasks() {
        var tasks = TaskService.getTasks();

        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id) {
        var task = TaskService.getTaskById(id);
        var note = NoteService.getNotesForTask(id);
        if(task == null) return ResponseEntity.notFound().build();

        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(note);
        return ResponseEntity.ok(taskResponse);
    }



    @PostMapping("")
    public ResponseEntity<taskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = TaskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<taskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = TaskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if( e instanceof ParseException ){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }

}

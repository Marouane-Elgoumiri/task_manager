package com.mr1.task_manager_spring.controllers;

import com.mr1.task_manager_spring.dto.CreateTaskDTO;
import com.mr1.task_manager_spring.dto.ErrorResponseDTO;
import com.mr1.task_manager_spring.dto.UpdateTaskDTO;
import com.mr1.task_manager_spring.entities.taskEntity;
import com.mr1.task_manager_spring.services.taskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class taskController {
    private final taskService TaskService;

    public taskController(taskService taskService) {
        this.TaskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<taskEntity>> getTasks() {
        var tasks = TaskService.getTasks();

        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<taskEntity> getTaskById(@PathVariable Integer id) {
        var task = TaskService.getTaskById(id);
        if(task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
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

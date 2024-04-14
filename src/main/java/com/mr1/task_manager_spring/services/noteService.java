package com.mr1.task_manager_spring.services;


import com.mr1.task_manager_spring.entities.noteEntity;
import com.mr1.task_manager_spring.entities.taskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class noteService {
    private taskService taskService;
    private HashMap<Integer, TaskNoteHolder> taskNoteHolder = new HashMap<>();

    public noteService(taskService taskService) {
        this.taskService = taskService;
    }

    class TaskNoteHolder {
        private ArrayList<noteEntity> notes = new ArrayList<>();
        private int noteId = 1;
    }
    public List<noteEntity> getNotesForTask(int taskId) {
        taskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }
        if (taskNoteHolder.get(taskId) == null) taskNoteHolder.put(taskId, new TaskNoteHolder());
        return taskNoteHolder.get(taskId).notes;
    }
    public noteEntity addNoteForTask(int taskId, String title, String body, String date) {
        taskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }
        if (taskNoteHolder.get(taskId) == null) {
            taskNoteHolder.put(taskId, new TaskNoteHolder());
        }
        noteEntity note = new noteEntity();
        note.setTitle(title);
        note.setBody(body);
        note.setDate(date);
        taskNoteHolder.get(taskId).notes.add(note);
        return note;
    }
}
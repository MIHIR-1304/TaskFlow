package com.taskflow.controller;

import com.taskflow.model.ActivityEvent;
import com.taskflow.model.SubTask;
import com.taskflow.model.Task;
import com.taskflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getTasksByBoard(@RequestParam(required = false, defaultValue = "board-1") String boardId) {
        return ResponseEntity.ok(taskRepository.findByBoardId(boardId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        if (task.getStatus() == null) task.setStatus("BACKLOG");
        if (task.getPriority() == null) task.setPriority("MEDIUM");
        if (task.getStoryPoints() == null) task.setStoryPoints(3);
        if (task.getKey() == null) task.setKey("TF-" + (100 + (int)(Math.random() * 900)));
        if (task.getSubtasks() == null) task.setSubtasks(new ArrayList<>());
        if (task.getActivityLog() == null) task.setActivityLog(new ArrayList<>());

        task.getActivityLog().add(ActivityEvent.builder()
                .id(UUID.randomUUID().toString())
                .actorName(task.getAssigneeName() != null ? task.getAssigneeName() : "Mihir Mahato")
                .action("created issue " + task.getKey())
                .timestamp(LocalDateTime.now())
                .build());

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updated) {
        return taskRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setDescription(updated.getDescription());
            existing.setStatus(updated.getStatus());
            existing.setPriority(updated.getPriority());
            existing.setStoryPoints(updated.getStoryPoints());
            existing.setAssigneeName(updated.getAssigneeName());
            existing.setTags(updated.getTags());
            existing.setSubtasks(updated.getSubtasks());
            existing.setDueDate(updated.getDueDate());
            existing.setUpdatedAt(LocalDateTime.now());
            return ResponseEntity.ok(taskRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable String id, @RequestParam String status) {
        return taskRepository.findById(id).map(task -> {
            String oldStatus = task.getStatus();
            task.setStatus(status);
            task.setUpdatedAt(LocalDateTime.now());

            if (task.getActivityLog() == null) task.setActivityLog(new ArrayList<>());
            task.getActivityLog().add(ActivityEvent.builder()
                    .id(UUID.randomUUID().toString())
                    .actorName("User")
                    .action("transitioned status from " + oldStatus + " to " + status)
                    .timestamp(LocalDateTime.now())
                    .build());

            return ResponseEntity.ok(taskRepository.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/subtasks")
    public ResponseEntity<Task> addSubtask(@PathVariable String id, @RequestBody SubTask subTask) {
        return taskRepository.findById(id).map(task -> {
            if (task.getSubtasks() == null) task.setSubtasks(new ArrayList<>());
            subTask.setId(UUID.randomUUID().toString());
            task.getSubtasks().add(subTask);
            task.setUpdatedAt(LocalDateTime.now());
            return ResponseEntity.ok(taskRepository.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

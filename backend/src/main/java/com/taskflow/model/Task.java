package com.taskflow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String key; // e.g. "TF-101"
    private String boardId;
    private String title;
    private String description;
    private String status; // BACKLOG, TO_DO, IN_PROGRESS, REVIEW, DONE
    private String priority; // LOW, MEDIUM, HIGH, URGENT
    private Integer storyPoints;
    private String assigneeId;
    private String assigneeName;
    private String creatorId;
    private List<String> tags;
    private List<SubTask> subtasks;
    private List<ActivityEvent> activityLog;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

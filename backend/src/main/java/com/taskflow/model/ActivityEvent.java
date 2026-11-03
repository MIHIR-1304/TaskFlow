package com.taskflow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityEvent {
    private String id;
    private String actorName;
    private String action; // e.g. "moved to IN_PROGRESS", "added checklist item", "created issue"
    private LocalDateTime timestamp;
}

package com.taskflow.repository;

import com.taskflow.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByBoardId(String boardId);
    List<Task> findByAssigneeId(String assigneeId);
    List<Task> findByBoardIdAndStatus(String boardId, String status);
}

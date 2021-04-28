package com.example.kanban.apis;

import com.example.kanban.domain.Task;
import com.example.kanban.domain.TaskStatus;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();;
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.taskStatus = task.getTaskStatus();
    }
}

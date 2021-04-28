package com.example.kanban.apis;

import com.example.kanban.domain.Member;
import com.example.kanban.domain.Project;
import com.example.kanban.domain.Task;
import com.example.kanban.domain.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProjectDto {
    private Long id;
    private Long ownerId;
    private String name;
    private List<TaskDto> tasks;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.ownerId = project.getOwner().getId();
        this.name = project.getName();
        this.tasks = project.getTasks().stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}

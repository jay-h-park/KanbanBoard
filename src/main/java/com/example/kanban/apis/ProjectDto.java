package com.example.kanban.apis;

import com.example.kanban.domain.Project;
import lombok.Getter;

@Getter
public class ProjectDto {
    private Long id;
    private Long ownerId;
    private String name;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.ownerId = project.getOwner().getId();
        this.name = project.getName();
    }
}

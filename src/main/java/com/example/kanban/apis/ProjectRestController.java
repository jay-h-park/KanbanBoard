package com.example.kanban.apis;

import com.example.kanban.service.ProjectService;
import com.example.kanban.service.TaskService;
import com.example.kanban.utils.ApiUtils;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/projects")
public class ProjectRestController {
    private final ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ApiUtils.ApiResult<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDtoList = projectService.findAll().stream()
                .map(ProjectDto::new)
                .collect(Collectors.toList());

        return ApiUtils.success(projectDtoList);
    }

    @GetMapping(path = "/{id}")
    public ApiUtils.ApiResult<ProjectDto> getProjectById(@PathVariable("id") Long id) throws Exception{
        return ApiUtils.success(
                projectService.findById(id)
                        .map(ProjectDto::new)
                        .orElseThrow(() -> new NotFoundException("There are no project with given id " + id)));
    }
}

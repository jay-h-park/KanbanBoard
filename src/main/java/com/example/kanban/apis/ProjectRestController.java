package com.example.kanban.apis;

import com.example.kanban.domain.Member;
import com.example.kanban.domain.Project;
import com.example.kanban.domain.ProjectMember;
import com.example.kanban.service.MemberService;
import com.example.kanban.service.ProjectMemberService;
import com.example.kanban.service.ProjectService;
import com.example.kanban.service.TaskService;
import com.example.kanban.utils.ApiUtils;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/projects")
public class ProjectRestController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final MemberService memberService;
    private final TaskService taskService;

    public ProjectRestController(ProjectService projectService, ProjectMemberService projectMemberService, MemberService memberService, TaskService taskService) {
        this.projectService = projectService;
        this.projectMemberService = projectMemberService;
        this.memberService = memberService;
        this.taskService = taskService;
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

    @PostMapping(path = "/new")
    public ApiUtils.ApiResult<CreateProjectResponse> createProject(@RequestBody @Valid CreateProjectRequest request) throws Exception{
        Member owner = memberService.findById(request.getOwnerId());
        if (owner == null) {
            throw new NotFoundException("There are no member with given id " + request.getOwnerId());
        }

        Project project = new Project();
        project.setName(request.getName());
        project.setOwner(owner);
        projectService.join(project);

        return ApiUtils.success(new CreateProjectResponse(project.getId(), project.getOwner().getId(), project.getName()));
    }

    @DeleteMapping(path = "/{id}")
    public ApiUtils.ApiResult<?> deleteProject(@PathVariable("id") Long id) throws Exception{
        projectService.findById(id).orElseThrow(() -> new NotFoundException("There are no project with given id " + id));
        projectMemberService.deleteByProjectId(id);
        taskService.deleteByProjectId(id);
        projectService.deleteById(id);

        return this.getAllProjects();
    }

    @Data
    static class CreateProjectRequest {
        private Long ownerId;
        private String  name;
    }

    @Data
    static class CreateProjectResponse {
        private Long id;
        private Long ownerId;
        private String  name;

        public CreateProjectResponse(Long id, Long ownerId, String name) {
            this.id = id;
            this.ownerId = ownerId;
            this.name = name;
        }
    }
}

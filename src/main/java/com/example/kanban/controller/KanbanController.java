package com.example.kanban.controller;

import com.example.kanban.domain.*;
import com.example.kanban.service.MemberService;
import com.example.kanban.service.ProjectMemberService;
import com.example.kanban.service.ProjectService;
import com.example.kanban.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class KanbanController {
    private final TaskService taskService;
    private final MemberService memberService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @GetMapping("/kanban/")
    public String intro(Model model, HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute("id");

        List<Project> projects = projectService.findByOwner_Id(memberId);
        List<ProjectMember> projectMembers = projectMemberService.findByMember_Id(memberId);
        projectMembers.forEach(o -> projects.add(o.getProject()));

        model.addAttribute("projects", projects);
        model.addAttribute("projectId", projects.get(0).getId());

        return  "redirect:/kanban/" + projects.get(0).getId();
    }

    @GetMapping("/kanban/{id}")
    public String list(@PathVariable(name = "id") Long id, Model model, HttpSession httpSession) {
        List<Task> tasks = taskService.findByProjectId(id);
        System.out.println("tasks = " + tasks);

        List<Task> backlogTasks = tasks.stream()
                .filter(item -> item.getTaskStatus() == TaskStatus.BACKLOG)
                .collect(Collectors.toList());

        List<Task> inProgressTasks = tasks.stream()
                .filter(item -> item.getTaskStatus() == TaskStatus.IN_PROGRESS)
                .collect(Collectors.toList());

        List<Task> doneTasks = tasks.stream()
                .filter(item -> item.getTaskStatus() == TaskStatus.DONE)
                .collect(Collectors.toList());

        Long memberId = (Long) httpSession.getAttribute("id");

        List<Project> projects = projectService.findByOwner_Id(memberId);
        List<ProjectMember> projectMembers = projectMemberService.findByMember_Id(memberId);
        projectMembers.forEach(o -> projects.add(o.getProject()));

        model.addAttribute("projects", projects);
        model.addAttribute("projectId", id);
        model.addAttribute("backlogTasks", backlogTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);
        model.addAttribute("doneTasks", doneTasks);

        return "/kanban/items";
    }

    @GetMapping("/kanban/{id}/new")
    public String create(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("taskForm", new TaskForm());
        model.addAttribute("projectId", id);

        return "/kanban/createTaskForm";
    }

    @PostMapping("/kanban/{id}/new")
    public String createTask(@PathVariable(name = "id") Long id, @Valid TaskForm taskForm, BindingResult result, HttpSession httpSession) {
        if (result.hasErrors()) {
            return "/kanban/createTaskForm";
        }

        Task task = new Task();
        task.setDescription(taskForm.getDescription());
        task.setTitle(taskForm.getTitle());
        task.setDueDate(taskForm.getDueDate());
        task.setTaskStatus(TaskStatus.BACKLOG);
        task.setProject(projectService.findById(id));

        Long memberId = (Long)httpSession.getAttribute("id");
        Member member = memberService.findById(memberId);
        task.setReporter(member);

        taskService.join(task);
        return "redirect:/kanban/" + id;
    }

    @GetMapping("/kanban/{id}/task/{taskId}")
    public String getTask(@PathVariable(name = "id") Long id, @PathVariable(name = "taskId") Long taskId, Model model) {
        Task task = taskService.findById(taskId);
        model.addAttribute("taskForm", task);
        model.addAttribute("projectId", task.getProject().getId());
        model.addAttribute("taskId", task.getId());

        return "/kanban/updateTaskForm";
    }

    @PutMapping("/kanban/{id}/task/{taskId}")
    public String updateTask(@PathVariable(name = "id") Long id, @PathVariable(name = "taskId") Long taskId, TaskForm taskForm) {
        Task task = taskService.findById(taskId);

        task.setDueDate(taskForm.getDueDate());
        task.setTitle(taskForm.getTitle());
        task.setDescription(taskForm.getDescription());

        taskService.join(task);

        return "redirect:/kanban/" + id;
    }

    @DeleteMapping("/kanban/{id}/task/{taskId}")
    public String deleteTask(@PathVariable(name = "id") Long id, @PathVariable(name = "taskId") Long taskId, TaskForm taskForm) {
        System.out.println(" Delete Mapping ");
        taskService.remove(id, taskId);

        return "redirect:/kanban/" + id;
    }
}

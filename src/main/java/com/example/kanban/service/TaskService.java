package com.example.kanban.service;

import com.example.kanban.domain.Task;
import com.example.kanban.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public void join(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public Task findById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public List<Task> findByProjectId(Long id) {
        return taskRepository.findByProjectId(id);
    }

    @Transactional
    public List<Task> findByMemberId(Long id) {
        return taskRepository.findByMemberId(id);
    }

    @Transactional
    public List<Task> findByProjectIdAndReporterId(Long projectId, Long reporterId) {
        return taskRepository.findByProjectIdAndReporterId(projectId, reporterId);
    }

    @Transactional
    public void remove(Long id, Long taskId) {
        taskRepository.remove(id, taskId);
    }
}

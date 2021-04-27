package com.example.kanban.service;

import com.example.kanban.domain.Member;
import com.example.kanban.domain.Project;
import com.example.kanban.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Long join(Project project) {
        projectRepository.save(project);
        return project.getId();
    }

    @Transactional
    public List<Project> findByOwner_Id(Long id) {
        return (List<Project>) projectRepository.findByOwner_Id(id);
    }

    @Transactional
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public List<Project> findAll() {
        return (List<Project>) projectRepository.findAll();
    }

    @Transactional
    public void delete(Project project) {
        projectRepository.delete(project);
    }

    @Transactional
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

}

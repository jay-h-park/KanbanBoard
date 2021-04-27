package com.example.kanban.service;

import com.example.kanban.domain.Project;
import com.example.kanban.domain.ProjectMember;
import com.example.kanban.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;

    @Transactional
    public Long join(ProjectMember projectMember) {
        projectMemberRepository.save(projectMember);
        return projectMember.getId();
    }

    @Transactional
    public List<ProjectMember> findByMember_Id(Long id) {
        return (List<ProjectMember>) projectMemberRepository.findByMember_Id(id);
    }

    @Transactional
    public List<ProjectMember> findByProject_Id(Long id) {
        return (List<ProjectMember>) projectMemberRepository.findByProject_Id(id);
    }

    @Transactional
    public ProjectMember findByProject_IdAndMember_Id(Long projectId, Long memberId) {
        return projectMemberRepository.findByProject_IdAndMember_Id(projectId, memberId)
                .orElse(new ProjectMember());
    }
    @Transactional
    public ProjectMember findById(Long id) {
        return projectMemberRepository.findById(id)
                .orElse(new ProjectMember());
    }

    @Transactional
    public List<ProjectMember> findAll() {
        return (List<ProjectMember>) projectMemberRepository.findAll();
    }

    @Transactional
    public void delete(ProjectMember projectMember) {
        projectMemberRepository.delete(projectMember);
    }

    @Transactional
    public Long deleteByProjectId(Long projectId) {
        return projectMemberRepository.deleteByProjectId(projectId);
    }
}

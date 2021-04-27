package com.example.kanban.repository;

import com.example.kanban.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    @Override
    Optional<ProjectMember> findById(Long aLong);

    @Override
    List<ProjectMember> findAll();

    Iterable<ProjectMember> findByProject_Id(Long id);

    Iterable<ProjectMember> findByMember_Id(Long id);

    Optional<ProjectMember> findByProject_IdAndMember_Id(Long projectId, Long memberId);

    @Override
    <S extends ProjectMember> S save(S entity);

    Long deleteByProjectId(Long projectId);
}

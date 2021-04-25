package com.example.kanban.repository;

import com.example.kanban.domain.ProjectMember;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectMemberRepository extends CrudRepository<ProjectMember, Long> {

    @Override
    Optional<ProjectMember> findById(Long aLong);

    @Override
    Iterable<ProjectMember> findAll();

    Iterable<ProjectMember> findByProject_Id(Long id);

    Iterable<ProjectMember> findByMember_Id(Long id);

    Optional<ProjectMember> findByProject_IdAndMember_Id(Long projectId, Long memberId);

    @Override
    <S extends ProjectMember> S save(S entity);


}

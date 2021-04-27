package com.example.kanban.repository;

import com.example.kanban.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Optional<Project> findById(Long aLong);

    Iterable<Project> findByOwner_Id(Long id);

    @Override
    Iterable<Project> findAll();

    @Override
    <S extends Project> S save(S entity);

    @Override
    void delete(Project entity);

    @Override
    void deleteById(Long id);
}

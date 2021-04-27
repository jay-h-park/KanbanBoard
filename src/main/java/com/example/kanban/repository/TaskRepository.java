package com.example.kanban.repository;

import com.example.kanban.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {
    private final EntityManager em;

    public void save(Task task) {
        if (task.getId() == null) {
            em.persist(task);
        } else {
            em.merge(task);
        }
    }

    public Task findById(Long id) {
        return em.find(Task.class, id);
    }

    public List<Task> findByProjectId(Long id) {
        List<Task> tasks = em.createQuery(
                "select t from Task t where t.project.id = :id", Task.class)
                .setParameter("id", id)
                .getResultList();

        return tasks;
    }

    public List<Task> findByMemberId(Long id) {
        List<Task> tasks = em.createQuery(
                "select t from Task t where t.reporter.id = :id", Task.class)
                .setParameter("id", id)
                .getResultList();

        return tasks;
    }

    public List<Task> findByProjectIdAndReporterId(Long projectId, Long reporterId) {
        List<Task> tasks = em.createQuery(
                "select t from Task t where t.project.id = :projectId and t.reporter.id = :reporterId", Task.class)
                .setParameter("projectId", projectId)
                .setParameter("reporterId", reporterId)
                .getResultList();

        return tasks;
    }

    public void remove(Long id, Long taskId) {
        Task task = em.find(Task.class, taskId);
        em.remove(task);
    }

    public void deleteByProjectId(Long projectId) {
        em.createQuery(
                "delete from Task t where t.project.id = :projectId")
                .setParameter("projectId", projectId)
                .executeUpdate();
    }
}

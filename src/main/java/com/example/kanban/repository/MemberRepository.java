package com.example.kanban.repository;

import com.example.kanban.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    public Member findByEmail(String email) {
        Member findMember = em.createQuery(
                "select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getSingleResult();

        return findMember;
    }
}

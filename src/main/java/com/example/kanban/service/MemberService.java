package com.example.kanban.service;

import com.example.kanban.domain.Member;
import com.example.kanban.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Lob;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}

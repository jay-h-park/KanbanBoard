package com.example.kanban.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Project {

    @Id @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Member owner;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
}

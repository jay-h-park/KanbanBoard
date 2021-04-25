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
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Member owner;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", projectMembers=" + projectMembers +
                '}';
    }
}

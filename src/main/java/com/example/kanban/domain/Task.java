package com.example.kanban.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Task {

    @Id @GeneratedValue
    @Column(name = "task_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member reporter;

    @NotBlank
    @Length(max = 25)
    private String title;

    @Lob
    private String description;

    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", reporter=" + reporter +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", taskStatus=" + taskStatus +
                '}';
    }
}

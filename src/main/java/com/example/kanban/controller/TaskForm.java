package com.example.kanban.controller;

import com.example.kanban.domain.TaskStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Getter @Setter
public class TaskForm {
    @NotBlank(message = "제목을 입력하시길 바랍니다.")
    @Length(max = 25, message = "최대 입력 가능한 제목의 길이는 25자 입니다.")
    private String title;

    private String description;

    @FutureOrPresent(message = "오늘 이후의 날짜를 선택하시길 바랍니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
}

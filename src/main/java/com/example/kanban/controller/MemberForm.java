package com.example.kanban.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter @Setter
public class MemberForm {
    @NotEmpty
    @Email(message = "올바른 email 형식을 적어주세요.")
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Size(min = 8, max = 21, message = "가능한 비밀번호의 길이는 8자 이상, 21자 이하 입니다.")
    private String password;

    private String name;

    @Pattern(regexp = "^\\d{11}$", message = "`-`를 제외하고 숫자만을 입력해 주세요.")
    private String phoneNumber;
}

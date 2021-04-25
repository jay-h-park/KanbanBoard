package com.example.kanban.controller;

import com.example.kanban.domain.Member;
import com.example.kanban.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String create(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Member member = new Member();
        member.setEmail(memberForm.getEmail());
        member.setName(memberForm.getName());
        member.setPhoneNumber(memberForm.getPhoneNumber());
        member.setPassword(Integer.toString(Objects.hash(memberForm.getPassword())));

        memberService.join(member);
        return "redirect:/";
    }

    @PostMapping("/members/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession httpSession) {
        Member member = memberService.findByEmail(email);
        if (member.getPassword().equals(Integer.toString(Objects.hash(password)))) {
            httpSession.setAttribute("id", member.getId());

            return "redirect:/kanban/";
        }
        return "/";
    }
}

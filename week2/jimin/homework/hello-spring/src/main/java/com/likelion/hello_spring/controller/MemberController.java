package com.likelion.hello_spring.controller;


import com.likelion.hello_spring.domain.Member;
import com.likelion.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String creteForm()
    {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm)
    {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        // "/", 즉 홈 화면으로 다시 돌아감
        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model)
    {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}

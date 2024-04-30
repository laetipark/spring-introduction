package com.example.introduction.controller;

import com.example.introduction.dto.MemberDTO;
import com.example.introduction.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    private final MemberService memberService;

    public HelloController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello, Spring Boot!");
        return "hello"; // hello.html를 redering
    }

    @GetMapping("hello-mvc")
    public String helloMVC(
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello-static"; // templates에 있는 hello-static.html를 redering
    }

    @GetMapping("hello-string")
    @ResponseBody // http body에 들어가는 데이터, 객체로 주면 json으로 반환
    public String helloString(
            @RequestParam(value = "name", required = false) String name
    ) {
        return "hello " + name;
    }

    @GetMapping("/member/register")
    public String registerMember() {
        return "register";
    }

    @PostMapping("/member/register")
    public String registerMember(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "register";
    }
}

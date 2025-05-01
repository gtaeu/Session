package com.likelion.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 이 기본 경로 "/"에 대한 함수를 정의해두지 않는다면 static > index.html이 열림
    @GetMapping("/")
    public String home()
    {
        return "home";
    }
}

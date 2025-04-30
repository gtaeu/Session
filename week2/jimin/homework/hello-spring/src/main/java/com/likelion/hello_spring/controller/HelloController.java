package com.likelion.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        // model에 key-value를 add
        model.addAttribute("data", "hello!!");

        // html 파일명을 반환
        // 뷰리졸버가 해당 html을 찾아서 스프링에게 넘기는 동작
        return "hello";
    }

    @GetMapping("hello-mvc")
    // @RequestParam: Http의 요청 파라미터의 이름(name)으로 바인딩해서 String name에 저장해줌
    public String helloMvc(@RequestParam("name") String name, Model model)
    {
        // Http 쿼리 스트링에 name이 있다면 가져와서 model에 넣고 -> 뷰로 넘김
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // Http의 Response body에 반환값을 넣겠다는 의미
    public String helloString(@RequestParam("name") String name, Model model)
    {
        return "hello " + name;
    }


}

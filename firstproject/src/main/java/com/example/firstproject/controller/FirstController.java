package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 이 Class가 Controller라는 것을 표시해줌.
public class FirstController {
    @GetMapping("/hi") // main 주소의 /hi 영역에 아래 method를 매핑함.
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "배준범");
        return "greetings"; // throw to browser templates/greeting.mustache
    }

    @GetMapping("bye")
    public String seeYouNext(Model model)
    {
        model.addAttribute("nickname", "배준범");
        return "goodbye";
    }
}

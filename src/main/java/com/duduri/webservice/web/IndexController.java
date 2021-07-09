package com.duduri.webservice.web;

import com.duduri.webservice.config.auth.LoginUser;
import com.duduri.webservice.config.auth.dto.SessionUser;
import com.duduri.webservice.service.PostsService;
import com.duduri.webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // 메인
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    // 글등록
    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        return "posts-save";
    }

    // 글수정
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}


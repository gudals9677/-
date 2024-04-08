package kr.co.springboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(){

        return "/list";
    }

    @GetMapping("/modify")
    public String modifyForm(){
        return "/modify";
    }

    @GetMapping("/view")
    public String view(){
        return "/view";
    }

    @GetMapping("/write")
    public String write(){
        return "/write";
    }

    @PostMapping("/write")
    public String write(HttpServletRequest request, ArticleDTO articleDTO){

        String regip = request.getRemoteAddr();
        articleDTO.setRegip(regip);

        log.info(articleDTO.toString());

        articleService.insertArticle(articleDTO);

        return "redirect:/list";
    }

}

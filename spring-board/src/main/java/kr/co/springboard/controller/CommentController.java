package kr.co.springboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{no}")
    public ResponseEntity<List<ArticleDTO>> getComment(@PathVariable("no") int no){
        return commentService.selectComments(no);
    }

    @PostMapping("/comment")
    public ResponseEntity<Article> postComment(@RequestBody ArticleDTO articleDTO, HttpServletRequest request){
        String regip = request.getRemoteAddr();
        articleDTO.setRegip(regip);
        log.info(articleDTO.toString());

        return commentService.insertComment(articleDTO);
    }
}

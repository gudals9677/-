package kr.co.springboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.dto.FileDTO;
import kr.co.springboard.dto.PageRequestDTO;
import kr.co.springboard.dto.PageResponseDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.service.ArticleService;
import kr.co.springboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final FileService fileService;

    // 게시글 출력
    /*@GetMapping("/list")
    public String list(Model model){

        model.addAttribute("list",articleService.articleList());

        return "/list";
    }*/

    // 게시글 출력 + 페이지 처리 추가
    /*@GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size= 10, sort = "no",
                                    direction = Sort.Direction.DESC)Pageable pageable){

        model.addAttribute("list",articleService.articleList(pageable));

        return "/list";
    }*/

    /*// 페이지 이동 추가
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size= 10, sort = "no",
            direction = Sort.Direction.DESC)Pageable pageable){

        Page<Article> list = articleService.articleList(pageable);

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/list";
    }*/

    // 검색기능 추가
    /*
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size= 10, sort = "no",
            direction = Sort.Direction.DESC)Pageable pageable,String searchKeyword){

        Page<Article> list = null;

        if(searchKeyword == null){
            list = articleService.articleList(pageable);
        } else{
            list = articleService.articleSearch(searchKeyword,pageable);
        }

        int nowPage = pageable.getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchKeyword", searchKeyword);


        return "/list";
    }
    */
    @GetMapping("/list")
    public String list(Model model, String cate, PageRequestDTO pageRequestDTO){

        PageResponseDTO pageResponseDTO = articleService.selectArticles(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);
        model.addAttribute(pageResponseDTO);

        return "/list";
    }

    @GetMapping("/modify/{no}")
    public String modifyForm(@PathVariable("no") int no, Model model){

        model.addAttribute("article", articleService.articleView(no));
        return "/modify";
    }

    @PostMapping("/modify/{no}")
    public String modify(@PathVariable("no") int no, ArticleDTO articleDTO){

        // 기존 글 조회
       ArticleDTO articleViewDTO = articleService.articleView(no);

        // 수정된 내용 설정
        articleViewDTO.setTitle(articleDTO.getTitle());
        articleViewDTO.setContent(articleDTO.getContent());

        // 수정된 글 업데이트
        articleService.updateArticle(articleViewDTO);

        return "redirect:/list";
    }


    @GetMapping("/delete")
    public String delete(int no){

        articleService.articleDelete(no);
        return "redirect:/list";
    }

    @GetMapping("/view")
    public String view(Model model, int no){

        model.addAttribute("view",articleService.articleView(no));
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

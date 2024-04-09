package kr.co.springboard.service;
import java.io.File;
import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    // 게시글 작성
    public void insertArticle(ArticleDTO articleDTO){

        Article article = modelMapper.map(articleDTO, Article.class);
        log.info("article = {}", article.toString());

        Article savedArticle = articleRepository.save(article);
        log.info("insertArticle ={}", savedArticle.toString());
    }

    // 게시글 수정
    public void updateArticle(Article article){

        articleRepository.save(article);

    }
    /*
    // 게시글 리스트
    public List<Article> articleList(){

        return articleRepository.findAll();
    }*/

    // 게시글 리스트 + page처리 추가
    public Page<Article> articleList(Pageable pageable){

        return articleRepository.findAll(pageable);
    }

    // 게시글 검색기능 추가
    public Page<Article> articleSearch(String searchKeyword, Pageable pageable){

        return articleRepository.findByTitleContaining(searchKeyword,pageable);
    }


    // 특정 게시글 불러오기
    public Article articleView(int no){

        return articleRepository.findById(no).get();
    }

    //특정 게시글 삭제
    public void articleDelete(int no){
        articleRepository.deleteById(no);
    }

}

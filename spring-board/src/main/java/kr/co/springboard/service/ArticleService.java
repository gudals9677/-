package kr.co.springboard.service;

import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public void insertArticle(ArticleDTO articleDTO){
        
        Article article = modelMapper.map(articleDTO, Article.class);
        log.info("article = {}", article.toString());

        Article savedArticle = articleRepository.save(article);
        log.info("insertArticle ={}", savedArticle.toString());
    }

}

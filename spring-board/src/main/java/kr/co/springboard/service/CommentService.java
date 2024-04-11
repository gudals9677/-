package kr.co.springboard.service;

import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Article> insertComment(ArticleDTO articleDTO){
        Article article = modelMapper.map(articleDTO, Article.class);
        Article savedArticle = articleRepository.save(article);

        return ResponseEntity.ok().body(savedArticle);
    }
}

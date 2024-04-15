package kr.co.springboard.service;

import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<ArticleDTO>> selectComments(int no){

        // ArticleRepository > findByParent() 쿼리 메서드 정의
        List<Article> articleList = articleRepository.findByParent(no);

        List<ArticleDTO> articleDTOS = articleList.stream()

                .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                .toList();

        return ResponseEntity.ok().body(articleDTOS);
    }

    public ResponseEntity<?> deleteComment(int no){

        log.info("no = {}", no);
        // 삭제 전 조회
        Optional<Article> optArticle = articleRepository.findById(no);

        log.info("optArticle = {}", optArticle);

        if(optArticle.isPresent()){

            log.info("here1");

            articleRepository.deleteById(no);

            return ResponseEntity.ok().body(optArticle.get());
        } else{

            log.info("here2");

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("not found");
        }
    }
}

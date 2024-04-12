package kr.co.springboard.repository;

import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.entity.Article;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ArticleRepositoryTests {


    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Test
    public void findAllWithNick(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("no").descending());

        Page<Article> pageArticle = articleRepository.findByParentWithNick(0, pageable);
        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .toList();
        //log.info(Arrays.asList(pageArticle));
        for(ArticleDTO article : dtoList){
            log.info(article);
        }


        /*
        for(Object[] object : articleList){
            log.info(Arrays.asList(object));

        }
        */
    }


}
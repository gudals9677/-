package kr.co.springboard.repository;

import kr.co.springboard.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTests {


    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void findAllWithNick(){
        List<Object[]> articleList = articleRepository.findAllWithNick();


        for(Object[] object : articleList){
            System.out.println(object[0]);
        }

    }


}
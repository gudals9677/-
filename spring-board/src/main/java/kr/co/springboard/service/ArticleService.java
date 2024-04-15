package kr.co.springboard.service;
import com.querydsl.core.Tuple;
import kr.co.springboard.dto.ArticleDTO;
import kr.co.springboard.dto.FileDTO;
import kr.co.springboard.dto.PageRequestDTO;
import kr.co.springboard.dto.PageResponseDTO;
import kr.co.springboard.entity.Article;
import kr.co.springboard.entity.File;
import kr.co.springboard.repository.ArticleRepository;
import kr.co.springboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final FileService fileService;
    private final FileRepository fileRepository;

    // 게시글 작성
    public void insertArticle(ArticleDTO articleDTO){

        // 파일 첨부
        List<FileDTO> files = fileService.fileUpload(articleDTO);

        // 파일 첨부 갯수 초기화
        articleDTO.setFile(files.size());

        Article article = modelMapper.map(articleDTO, Article.class);
        log.info("article = {}", article.toString());

        Article savedArticle = articleRepository.save(article);
        log.info("insertArticle ={}", savedArticle.toString());

        // 파일 insert
        for (FileDTO fileDTO : files){

            fileDTO.setAno(savedArticle.getNo());

            // 여기서 에러나는데 RootConfig 파일에 ModelMapper 설정에 이거 추가 -> .setMatchingStrategy(MatchingStrategies.STRICT)
            File file = modelMapper.map(fileDTO, File.class);

            fileRepository.save(file);
        }
    }

    public PageResponseDTO selectArticles(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Article> pageArticle = articleRepository.findByParent(null, pageable);
        log.info("pageArticle = {}", pageArticle);
        //Page<Article> pageArticle = articleRepository.findByParentWithNick(0, pageable);

        List<ArticleDTO> dtoList = pageArticle.getContent().stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .toList();

        log.info("selectArticles...4 : " + dtoList);

        int total = (int) pageArticle.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }

    // 게시글 수정
    public void updateArticle(ArticleDTO articleDTO){

            //게시글 번호
            int articleNo = articleDTO.getNo();

            Article article = modelMapper.map(articleDTO, Article.class);
            Article savedArticles = articleRepository.save(article);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
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
    public ArticleDTO articleView(int no){

        Article article = articleRepository.findById(no).orElse(null);
        if (article != null) {
            return modelMapper.map(article, ArticleDTO.class);
        }
        return null; // 해당 번호에 해당하는 게시글이 없는 경우
    }


    //특정 게시글 삭제
    public void articleDelete(int no){

        // 게시글에 첨부된 파일들을 먼저 삭제
        List<File> files = fileRepository.findByAno(no);
        for (File file : files) {
            fileRepository.delete(file);
        }

        articleRepository.deleteById(no);
    }

}

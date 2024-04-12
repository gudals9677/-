package kr.co.springboard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Builder.Default
    private Integer parent = 0;

    @Builder.Default
    private Integer comment = 0;

    private String cate;

    private String title;
    private String content;
    private String writer;

    @Builder.Default
    private Integer file = 0;

    @Builder.Default
    private Integer hit = 0;

    private String regip;

    @CreationTimestamp
    private LocalDateTime rdate;

    @OneToMany(mappedBy = "ano") // mappedBy는 매핑 되는 엔티티(테이블)의 FK 컬럼 지정
    private List<File> fileList;

}

package kr.co.springboard.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "config")
public class Config {
    /*
        날짜 : 2024/03/19
        이름 : 김철학
        내용 : 게시판 테이블 설정 정보

        cate: 게시판 카테고리
        boardName : 게시판 이름
        admin : 게시판 관리자
        total : 게시판 총 글 등록수
        createDate : 게시판 생성일
    */

    @Id
    private String cate;
    private String boardName;
    private String admin;

    @ColumnDefault("0")
    private int total;

    @CreationTimestamp
    private LocalDateTime createDate;



}

package kr.co.springboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String email;
    private String hp;

    @Builder.Default
    private String role = "USER";

    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private String sms;
    private String provider;

    @CreationTimestamp
    private LocalDateTime regDate;
    private LocalDateTime leaveDate;


}

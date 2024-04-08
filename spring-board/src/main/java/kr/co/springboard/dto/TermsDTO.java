package kr.co.springboard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermsDTO {

    private int seq;
    private String terms;
    private String privacy;
    private String sms;

}

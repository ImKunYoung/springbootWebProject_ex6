package com.example.ex5_board.BoardDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    /*식별키*/
    private Long bno;

    /*제목*/
    private String title;

    /*내용*/
    private String content;

    /*작성자 이메일 (member id)*/
    private String writerEmail;

    /*작성자 이름*/
    private String writerName;

    /*최초 작성일*/
    private LocalDateTime regDate;

    /*최종 수정일*/
    private LocalDateTime modDate;

    /*해당 게시글의 댓글 수*/
    private int replyCount;
}

package com.example.ex5_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private Long rno;

    private String text;

    private String replyer;

    /*게시물 번호*/
    private Long bno;

    private LocalDateTime regDate, modDate;

}

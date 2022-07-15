package com.example.ex5_board.service;

import com.example.ex5_board.BoardDTO.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    /*게시물 등록*/
    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com") // 현재 데이터베이스에 존재하는 회원 이메일
                .build();

        Long bno = boardService.register(dto);

        System.out.println("bno------------------------: " + bno);

    }

}

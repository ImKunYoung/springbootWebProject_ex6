package com.example.ex5_board.service;

import com.example.ex5_board.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    ReplyService replyService;

    /*register(ReplyDTO) -> Long 테스트*/
    @Test
    public void testRegister() {

        ReplyDTO replyDTO = ReplyDTO.builder()
                .text("Register_test_100")
                .replyer("user55@aaa.com") // 현재 데이터베이스에 존재하는 회원 이메일
                .bno(100L)
                .build();

        System.out.println(replyService.register(replyDTO));

    }

    /*getList(Long) -> List<ReplyDTO> 테스트*/
    @Test
    public void testGetList() {

        List<ReplyDTO> replyDTOS = replyService.getList(100L);

        replyDTOS.forEach(System.out::println);

    }

}

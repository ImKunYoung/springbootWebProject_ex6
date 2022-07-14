package com.example.ex5_board.repository;

import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    /*INSERT SAMPLE DATA*/
    @Test
    public void insertReply() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            /*1부터 100까지의 임의의 번호*/
            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply......."+i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);

        });
    }

    /*테이블 조인*/
    /*SELECT * FROM reply
    LEFT OUTER JOIN board ON reply.board_bno = board.bno
    LEFT OUTER JOIN member ON member.email = board.writer_email
    WHERE reply.rno = '1';*/
    @Test
    public void readReply1() {

        Optional<Reply> result = replyRepository.findById(1L);

        if(result.isPresent()) {
            Reply reply = result.get();

            System.out.println(reply);
            System.out.println(reply.getBoard());

        }

    }

}

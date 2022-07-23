package com.example.ex5_board.repository;

import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    WHERE reply.rno = '1';*/ // => 연관된 모든 테이블이 조인됨(Eager Loading): 비효율적
    @Test
    public void readReply1() {

        Optional<Reply> result = replyRepository.findById(1L);

        if(result.isPresent()) {
            Reply reply = result.get();

            System.out.println(reply);
            System.out.println(reply.getBoard());

        }

    }

    @Transactional
    @Test
    @DisplayName("댓글을 삭제한다")
    public void deleteReply() {

        Long bno = 100L;

        if(replyRepository.findById(bno).isPresent()) {
            replyRepository.deleteByBno(bno);
        }

    }

    /*게시물에 댓글 목록 가지고 오기*/
    @Test
    public void testListByBoard() {

        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(104L).build());

        // replyList.forEach(reply -> System.out.println(reply));

        // replyList.forEach(System.out::println);

//        for (Reply reply : replyList) {
//            System.out.println(reply.toString());
//        }

        for (Reply reply : replyList) {
            System.out.println(reply);
        }

    }

}

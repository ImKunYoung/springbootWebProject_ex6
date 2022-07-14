package com.example.ex5_board.repository;

import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    /*INSERT SAMPLE DATA*/
    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            
            Member member = Member.builder().email("user"+i+"@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content...."+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        });

    }

    /*테이블 조인*/
    /*SELECT * FROM board LEFT OUTER JOIN  member on board.writer_email = member.email;*/
    @Test
    public void testRead1() {

        Optional<Board> result = boardRepository.findById(100L); /*데이터베이스에 존재하는 번호*/

        if(result.isPresent()) {
            Board board = result.get();

            System.out.println(board);
            System.out.println(board.getWriter());
        }

    }
}

package com.example.ex5_board.repository;

import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Member;
import com.example.ex5_board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

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
    @Transactional /*해당 메서드를 하나의 트랜잭션으로 처리 - 필요할 때 다시 db와 연결 , eager loading 과의 조인 방식에 차이가 있음. eager - left outer join, lazy - inner join? */
    @Test
    public void testRead1() {

        Optional<Board> result = boardRepository.findById(100L); /*데이터베이스에 존재하는 번호*/

        if(result.isPresent()) {
            Board board = result.get();

            System.out.println(board);
            System.out.println(board.getWriter());
        }

    }

    /*lazy loading - 엔티티 클래스 내부에 연관관계가 있는 경우 조인 처리*/
    @Test
    public void testReadWithWriter() {

        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("-------------------------------------");
        System.out.println(Arrays.toString(arr));

    }

    /*lazy loading - 연관관계가 없는 엔티티 조인 처리 +OM*/
    @Test
    public void testGetBoardWithReply() {

        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        for(Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }

    /*게시물 목록 불러오기*/
    @Test
    public void testWithReplyCount() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> System.out.println(Arrays.toString(row)));

    }

    /*게시물 조회하기*/
    @Test
    public void testRead3() {

        Object result = boardRepository.getBoardByBno(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));

    }

    /*게시물 삭제하기*/
    @Test
    public void testRemove() {

        Long bno = 102L;

        boardService.removeWithReplies(bno);

    }

    /*repository 확장*/
    @Test
    public void testSearch1() {

        boardRepository.search1();

    }

    /*JPQLQuery로 Page<Object[]> 처리*/
    @Test
    public void testSearchPage() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.searchPage("tw", "1", pageable);

    }

}

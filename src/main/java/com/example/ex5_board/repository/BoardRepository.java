package com.example.ex5_board.repository;

import com.example.ex5_board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.ex5_board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    /*연관관계가 있는 엔티티 조인*/
    String q1 = "select b, w from Board b left join b.writer w where b.bno =:bno";
    @Query(value = q1)
    Object getBoardWithWriter(@Param("bno") Long bno);

    /*연관관계가 없는 엔티티 조인 +ON*/
    String q2 = "SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno";
    @Query(value = q2)
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    /*목록 화면*/
    String q3 = "SELECT b, w, count(r) FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b GROUP BY b";
    String q4 = "SELECT count(b) FROM Board b";
    @Query(value = q3, countQuery = q4)
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    /*조회 화면*/
    String q5 = "SELECT b, w, count(r) FROM Board b LEFT JOIN b.writer w LEFT OUTER JOIN Reply r ON r.board = b WHERE b.bno = :bno";
    @Query(q5)
    Object getBoardByBno(@Param("bno") Long bno);

}
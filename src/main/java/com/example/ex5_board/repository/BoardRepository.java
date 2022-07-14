package com.example.ex5_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.ex5_board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    /*연관관계가 있는 엔티티 조인*/
    String q1 = "select b, w from Board b left join b.writer w where b.bno =:bno";
    @Query(q1)
    Object getBoardWithWriter(@Param("bno") Long bno);

    /*연관관계가 없는 엔티티 조인*/
    String q2 = "SELECT b, r, FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno";
    @Query(q2)
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

}
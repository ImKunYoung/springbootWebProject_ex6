package com.example.ex5_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.ex5_board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    /*한개의 로우(Object) 내에 Object[]로 나옴*/
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

}
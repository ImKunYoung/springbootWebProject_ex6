package com.example.ex5_board.repository;

import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    /*댓글을 삭제한다*/
    String q1 = "delete from Reply r where r.board.bno = :bno";
    @Modifying // JPQL 에서 update, delete 실행을 위함
    @Query(q1)
    void deleteByBno(Long bno);

    /*게시물로 댓글 목록 가져오기*/
    List<Reply> getRepliesByBoardOrderByRno(Board board);

}

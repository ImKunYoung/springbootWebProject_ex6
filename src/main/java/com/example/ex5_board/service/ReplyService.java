package com.example.ex5_board.service;

import com.example.ex5_board.dto.ReplyDTO;
import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Reply;

import java.util.List;

public interface ReplyService {

    /*댓글을 등록한다*/
    Long register(ReplyDTO replyDTO);

    /*특정 게시물의 댓글 리스트를 가지고 온다*/
    List<ReplyDTO> getList(Long bno);

    /*댓글을 수정한다*/
    void modify(ReplyDTO replyDTO);

    /*댓글을 삭제한다*/
    void remove(Long rno);

    /*ReplyEntity -> ReplyDTO*/
    default Reply dtoToEntity(ReplyDTO replyDTO) {

        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;

    }

    /*ReplyDTO -> ReplyEntity*/
    default ReplyDTO dtoToEntity(Reply reply) {

        // Reply 객체를 ReplyDTO 로 변환 시 Board 객체가 필요하지 않으므로 게시물 번호만
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();


        return dto;

    }

}

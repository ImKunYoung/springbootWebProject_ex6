package com.example.ex5_board.service;

import com.example.ex5_board.dto.BoardDTO;
import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Member;

public interface BoardService {
    /*게시물 등록*/
    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

    }

}

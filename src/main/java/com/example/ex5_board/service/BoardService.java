package com.example.ex5_board.service;

import com.example.ex5_board.dto.BoardDTO;
import com.example.ex5_board.dto.PageRequestDTO;
import com.example.ex5_board.dto.PageResultDTO;
import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Member;

public interface BoardService {

    /*게시물 등록하기*/
    Long register(BoardDTO dto);

    /*게시물 목록 불러오기*/
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    /*게시물 조회하기*/
    BoardDTO get(Long bno);

    /*게시물 삭제하기*/
    void removeWithReplies(Long bno);

    /*DTO -> Entity*/
    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

    }

    /*Entity -> DTO*/
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue()) // long 으로 나오므로 int 로 처리
                .build();

        return boardDTO;
    }

}

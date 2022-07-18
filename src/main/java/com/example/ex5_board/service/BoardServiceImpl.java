package com.example.ex5_board.service;

import com.example.ex5_board.dto.BoardDTO;
import com.example.ex5_board.dto.PageRequestDTO;
import com.example.ex5_board.dto.PageResultDTO;
import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Member;
import com.example.ex5_board.repository.BoardRepository;
import com.example.ex5_board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository; // 자동 주입 final

    private final ReplyRepository replyRepository;

    /*게시물 등록*/
    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();

    }

    /*게시물 목록 불러오기*/
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member)en[1], (Long) en[2]));

        Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    /*게시물 조회하기*/
    @Override
    public BoardDTO get(Long bno) {

        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);

    }

    /*게시물 삭제하기*/
    @Transactional
    @Override
    public void removeWithReplies(Long bno) { // 삭제 기능 구현, 트랜잭션 추가

        // 1. 참조 댓글 삭제
        replyRepository.deleteByBno(bno);

        // 2. 게시물 삭제
        repository.deleteById(bno);

    }

    /*게시물 수정하기*/
    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = repository.getReferenceById(boardDTO.getBno()); // 필요한 순간까지 로딩지연

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        repository.save(board);

    }


}

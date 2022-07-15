package com.example.ex5_board.service;

import com.example.ex5_board.BoardDTO.BoardDTO;
import com.example.ex5_board.entity.Board;
import com.example.ex5_board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository; // 자동 주입 final

    /*게시물 등록*/
    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();

    }

}

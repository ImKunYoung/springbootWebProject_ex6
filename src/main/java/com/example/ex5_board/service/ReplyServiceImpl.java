package com.example.ex5_board.service;

import com.example.ex5_board.dto.ReplyDTO;
import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.Reply;
import com.example.ex5_board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {

        log.info(replyDTO);

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        return reply.getRno();

    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        log.info(bno);

        Board board = Board.builder().bno(bno).build();

        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(board);

        return result.stream().map(this::entityToDTO).collect(Collectors.toList());

    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

    }

    @Override
    public void remove(Long rno) {

    }
}

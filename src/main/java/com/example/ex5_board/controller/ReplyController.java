package com.example.ex5_board.controller;

import com.example.ex5_board.dto.ReplyDTO;
import com.example.ex5_board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    /*자동주입을 위한 final*/
    private final ReplyService replyService;


    /*게시물 목록 불러오기*/
    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {

        log.info("bno: "+bno);

        List<ReplyDTO> replyDTOList = replyService.getList(bno);

        return new ResponseEntity<>(replyDTOList, HttpStatus.OK);

    }

    /*댓글 등록하기*/
    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {

        log.info(replyDTO);

        Long rno = replyService.register(replyDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);

    }

}

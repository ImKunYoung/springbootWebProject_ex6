package com.example.ex5_board.controller;

import com.example.ex5_board.dto.PageRequestDTO;
import com.example.ex5_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /*게시물 목록 페이지*/
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list..............." + pageRequestDTO);

        model.addAttribute("result", boardService.getList(pageRequestDTO));

    }


    /*게시물 등록 페이지*/
    @GetMapping("/register")
    public void register() {

        log.info("register get...");

    }


}

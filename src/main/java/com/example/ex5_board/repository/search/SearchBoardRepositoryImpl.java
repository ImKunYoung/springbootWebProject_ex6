package com.example.ex5_board.repository.search;

import com.example.ex5_board.entity.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    /*description - QuerydslRepositorySupport 에 생성자가 존재함으로 클래스 내에서 super() 를 이용해 호출*/
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {

        log.info("search1............................");

        return null;

    }

}

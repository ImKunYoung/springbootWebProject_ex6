package com.example.ex5_board.repository.search;

import com.example.ex5_board.entity.Board;
import com.example.ex5_board.entity.QBoard;
import com.example.ex5_board.entity.QMember;
import com.example.ex5_board.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    /*description - QuerydslRepositorySupport 에 생성자가 존재함으로 클래스 내에서 super() 를 이용해 호출*/
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    /*JPQL Query 객체*/
    @Override
    public Board search1() {

        log.info("search1............................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
        tuple.groupBy(board);

        log.info("_________________________________________________");
        log.info(tuple);
        log.info("_________________________________________________");

        List<Tuple> result = tuple.fetch();

        log.info(result);

        return null;

    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage----------------------------------------");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);

        if (type!=null) {
           String[] typeArr = type.split("");
           BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t: typeArr) {
                switch (t) {
                    case "t" -> conditionBuilder.or(board.title.contains(keyword));
                    case "w" -> conditionBuilder.or(member.email.contains(keyword));
                    case "c" -> conditionBuilder.or(board.content.contains(keyword));
                }
           }
        }

        tuple.where(booleanBuilder);

        /*Order By*/
        Sort sort = pageable.getSort();

        // tuple.orderBy(board.bno.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder<Board> orderByExpression = new PathBuilder<>(Board.class, "board");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));

        });

        tuple.groupBy(board);


        /*Page 처리*/
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("COUNT: "+ count);

        return new PageImpl<>(result.stream().map(Tuple::toArray).collect(Collectors.toList()), pageable, count);

    }

}

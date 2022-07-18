package com.example.ex5_board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; /*게시물 고유 번호 PK*/

    private String title; /*제목*/

    private String content; /*내용*/

    /*연관 관계 지정*/
    @ManyToOne (fetch = FetchType.LAZY) // Lazy 로딩 명시적으로 지정
    @ToString.Exclude // 연관관계 엔티티의 변수는 ToString.Exclude 처리할 것
    private Member writer; /*(게시물)작성자*/

    /*제목 수정하기*/
    public void changeTitle(String title) {
        this.title = title;
    }

    /*내용 수정하기*/
    public void changeContent(String content) {
        this.content = content;
    }

}

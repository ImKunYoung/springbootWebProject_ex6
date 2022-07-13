package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno; /*댓글 고유번호*/

    private String text; /*내용*/

    private String replyer; /*작성자*/

    /*연관 관계 지정*/
    @ManyToOne
    private Board board; /*게시물 정보*/
}

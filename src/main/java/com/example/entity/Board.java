package com.example.entity;

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

    @ManyToOne
    private Member writer; /*(게시물)작성자*/
}

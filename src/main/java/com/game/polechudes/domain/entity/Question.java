package com.game.polechudes.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {
    @Id
    @SequenceGenerator(name = "question_seq", allocationSize = 1)
    @GeneratedValue(generator = "question_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="name", unique=true)
    private String name;
    private String hint;
    private String answer;
}

package com.game.polechudes.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class GameHistory {
    @Id
    @SequenceGenerator(name = "history_seq", allocationSize = 1)
    @GeneratedValue(generator = "history_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="player_name", unique=true)
    private String playerName;

    @ElementCollection
    private List<String> QuestionName;
}

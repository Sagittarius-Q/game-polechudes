package com.game.polechudes.domain.model.view;

import lombok.Data;

import java.util.List;

@Data
public class GameHistoryViewModel {
    private String playerName;
    private List<String> QuestionName;
}

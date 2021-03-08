package com.game.polechudes.service.history;

import com.game.polechudes.domain.model.history.GameHistoryModel;


import java.util.List;

public interface GameHistoryService {
    void save(GameHistoryModel gameResultModel);
    List<String> getAllByPlayerName(String player);
}

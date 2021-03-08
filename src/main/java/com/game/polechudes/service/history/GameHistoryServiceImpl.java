package com.game.polechudes.service.history;

import com.game.polechudes.domain.entity.GameHistory;
import com.game.polechudes.domain.model.history.GameHistoryModel;
import com.game.polechudes.repo.GameHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameHistoryServiceImpl implements GameHistoryService{
    private final GameHistoryRepository gameHistoryRepository;

    @Override
    public void save(GameHistoryModel historyModel) {
        if(!gameHistoryRepository.existsByPlayerName(historyModel.getPlayerName())){
            GameHistory gameHistory = new GameHistory();
            gameHistory.setPlayerName(historyModel.getPlayerName());
            gameHistory.setQuestionName(new ArrayList<>());
            gameHistory.getQuestionName().add(historyModel.getQuestionName());
            gameHistoryRepository.save(gameHistory);
        }

        GameHistory history = gameHistoryRepository.findByPlayerName(historyModel.getPlayerName());
        history.getQuestionName().add(historyModel.getQuestionName());
        this.gameHistoryRepository.save(history);
    }

    @Override
    public List<String> getAllByPlayerName(String player) {
        if(gameHistoryRepository.existsByPlayerName(player)){
            return gameHistoryRepository.findByPlayerName(player).getQuestionName();
        }
        return null;
    }
}

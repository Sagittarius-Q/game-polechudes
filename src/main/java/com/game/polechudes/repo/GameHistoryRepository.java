package com.game.polechudes.repo;

import com.game.polechudes.domain.entity.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameHistoryRepository extends JpaRepository<GameHistory,Long> {
   GameHistory findByPlayerName(String playerName);
   boolean existsByPlayerName(String name);
}

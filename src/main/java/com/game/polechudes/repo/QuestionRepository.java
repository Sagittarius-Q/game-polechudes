package com.game.polechudes.repo;

import com.game.polechudes.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select p.id from Question p")
    List<Long> getAllIds();
    boolean existsByName(String name);

}

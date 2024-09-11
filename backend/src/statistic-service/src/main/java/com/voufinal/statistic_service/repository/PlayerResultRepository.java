package com.voufinal.statistic_service.repository;

import com.voufinal.statistic_service.model.PlayerResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerResultRepository extends JpaRepository<PlayerResult, Long> {
    //PlayerResult findByIdEvent(Long idEvent);
    List<PlayerResult> findPlayerResultByIdEvent(Long idEvent);
}
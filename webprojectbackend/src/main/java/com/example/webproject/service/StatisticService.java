package com.example.webproject.service;

import com.example.webproject.model.Statistic;

import java.util.List;
import java.util.Optional;

public interface StatisticService {

    Statistic save(Statistic statistic);

    List<Statistic> getAllStatistics();

    Optional<Statistic> findById(long id);

    Statistic updateStatistic(long id, int numPicks, int win, int lose, int _void, float winRatio, double profit);

    void deleteStatistic(long id);
}

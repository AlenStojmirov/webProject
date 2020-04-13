package com.example.webproject.service.serviceImpl;

import com.example.webproject.model.Statistic;
import com.example.webproject.model.exception.InvalidStatisticException;
import com.example.webproject.repository.StatisticRepository;
import com.example.webproject.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Statistic save(Statistic statistic) {
        return this.statisticRepository.save(statistic);
    }

    @Override
    public List<Statistic> getAllStatistics() {
        return this.statisticRepository.findAll();
    }

    @Override
    public Optional<Statistic> findById(long id) {
        return this.statisticRepository.findById(id);
    }

    @Override
    public Statistic updateStatistic(long id, int numPicks, int win, int lose, int _void, float winRatio, double profit) {
        Statistic statistic = this.statisticRepository.findById(id).orElseThrow(InvalidStatisticException::new);
        statistic.setNumPicks(numPicks);
        statistic.setWin(win);
        statistic.setLose(lose);
        statistic.set_void(_void);
        statistic.setWinRatio(winRatio);
        statistic.setProfit(profit);

        return this.statisticRepository.save(statistic);
    }

    @Override
    public void deleteStatistic(long id) {
        this.statisticRepository.deleteById(id);
    }
}

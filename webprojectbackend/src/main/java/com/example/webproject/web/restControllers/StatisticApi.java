package com.example.webproject.web.restControllers;

import com.example.webproject.model.Statistic;
import com.example.webproject.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "statistics",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class StatisticApi {

    private final StatisticService statisticService;

    public StatisticApi(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Statistic> getAllStatistic(){
        return this.statisticService.getAllStatistics();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Statistic createStatistic(@RequestBody Statistic statistic){
        return this.statisticService.save(statistic);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Statistic> getStatistic(@PathVariable long id){
        return this.statisticService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Statistic updateStatistic(@PathVariable long id,
                             @RequestBody Statistic statistic){
        statistic.setId(id);
        return this.statisticService.save(statistic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStatistic(@PathVariable long id){
        this.statisticService.deleteStatistic(id);
    }

}

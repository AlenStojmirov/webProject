package com.example.webproject.web.restControllers;

import com.example.webproject.model.Pick;
import com.example.webproject.model.Statistic;
import com.example.webproject.service.MapValidationErrorService;
import com.example.webproject.service.PickService;
import com.example.webproject.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/picks", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PickApi {

    private final PickService pickService;
    private final MapValidationErrorService mapValidationErrorService;
    private final StatisticService statisticService;


    public PickApi(PickService pickService, MapValidationErrorService mapValidationErrorService, StatisticService statisticService) {
        this.pickService = pickService;
        this.mapValidationErrorService = mapValidationErrorService;
        this.statisticService = statisticService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pick> getAllPicks(){
        return this.pickService.getAllPicks();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createPick(@Valid @RequestBody Pick pick, BindingResult result, Principal principal){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        return pickService.save(pick,principal.getName());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Pick> getPick(@PathVariable long id){
        return this.pickService.findById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePick(@PathVariable long id,
                               @RequestBody String status){
        Pick pick = pickService.findById(id).get();
        Statistic stats = statisticService.findById(pick.getUser().getStatisticId().getId()).get();
        pick.setStatus(status);

        if(status.equals("WIN")){
            stats.setNumPicks(stats.getNumPicks() + 1);
            stats.setWinRatio(
                    ((float)(stats.getWin() + 1) / (stats.getNumPicks())) * 100);
            stats.setProfit(stats.getProfit() +
                    ((pick.getOdd() * ((float)pick.getStake()/10)) - (float)(pick.getStake()/10)));
            stats.setWin(stats.getWin() + 1);
        }else if(status.equals("LOSE")){
            stats.setNumPicks(stats.getNumPicks() + 1);
            stats.setWinRatio(
                    ((float)stats.getWin() / (stats.getNumPicks())) * 100);
            stats.setProfit(stats.getProfit() -
                    ((pick.getOdd() * ((float)pick.getStake()/10)) - (float)(pick.getStake()/10)));
            stats.setLose(stats.getLose() + 1);
        }else {
            stats.setNumPicks(stats.getNumPicks() + 1);
            stats.set_void(stats.get_void() + 1);
        }

        pickService.save(pick,pick.getUser().getUsername());
        statisticService.save(stats);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLeague(@PathVariable long id){
        this.pickService.deletePick(id);
    }

    @GetMapping("/nullPicks")
    public List<Pick> getAllNotRegularPicks(){
        return this.pickService.getAllPicksWithStatusNull();
    }
}
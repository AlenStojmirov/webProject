package com.example.webproject.repository;

import com.example.webproject.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select * from user \n" +
            "left join statistic on statistic.id = user.statistic_id \n" +
            "where statistic.profit is not null\n" +
            "order by statistic.profit desc limit 5", nativeQuery = true)
    List<User> theBest5TipsterByProfit();

    @Query(value = "select * from user \n" +
            "left join statistic on statistic.id = user.statistic_id \n" +
            "where statistic.win_ratio is not null\n" +
            "order by statistic.win_ratio desc limit 5", nativeQuery = true)
    List<User> theBest5TipstersByWinRatio();

    @Query(value = "select distinct * from user \n"+
            "left join statistic on statistic.id = user.id_stats\n" +
            "left join pick on pick.user_id = user.id\n" +
            "where pick.data > current_date - interval '30 days'\n" +
            "and statistic.win_ratio is not null\n" +
            "order by statistic.win_ratio desc limit 5", nativeQuery = true)
    List<User> theBest5TipsterByWinRatioInLast30Days();

    @Query(value = "select distinct * from user \n"+
            "left join statistic on statistic.id = user.id_stats\n" +
            "left join pick on pick.user_id = user.id\n" +
            "where pick.data > current_date - interval '30 days'\n" +
            "and statistic.profit is not null\n" +
            "order by statistic.profit desc limit 5", nativeQuery = true)
    List<User> theBest5TipsterByProfitInLast30Days();


}

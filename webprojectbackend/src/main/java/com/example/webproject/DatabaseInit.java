package com.example.webproject;

import com.example.webproject.model.*;
import com.example.webproject.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DatabaseInit implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClubRepository clubRepository;
    private final SportRepository sportRepository;
    private final PickRepository pickRepository;
    private final PrivateUserRepository privateUserRepository;
    private final MatchRepository sportMatchRepository;
    private final LeagueRepository leagueRepository;
    private final BookmarkRepository bookmarkRepository;
    private final StatisticRepository statisticRepository;

    public DatabaseInit(UserRepository userRepository, RoleRepository roleRepository, ClubRepository clubRepository, SportRepository sportRepository, PickRepository pickRepository, PrivateUserRepository privateUserRepository, MatchRepository sportMatchRepository, LeagueRepository leagueRepository, BookmarkRepository bookmarkRepository, StatisticRepository statisticRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.clubRepository = clubRepository;
        this.sportRepository = sportRepository;
        this.pickRepository = pickRepository;
        this.privateUserRepository = privateUserRepository;
        this.sportMatchRepository = sportMatchRepository;
        this.leagueRepository = leagueRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.statisticRepository = statisticRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("### DATABASE INIT ###");

        //Roles ------------------------------------->
        Role role = new Role();
        role.setId((long) 1);
        role.setName("ADMIN");
        roleRepository.save(role);

        Role role1 = new Role();
        role1.setId((long) 2);
        role1.setName("TIPSTER");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setId((long)3);
        role2.setName("PRIVATE TIPSTER");
        roleRepository.save(role2);

        //Users Admins ------------------------------------->
        User user= new User();
        user.setId((long) 1);
        user.setUsername("Alen");
        user.setPassword("$2a$04$gXQQqZ/Hx..Mi0imMrXKFuFcJKwcoiwxa8QInBlDHT8p/yWx8zhg.");
        user.setEmail("Alen@gmail.com");
        userRepository.save(user);

        role.setUsers(Collections.singletonList(user));
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        roleRepository.save(role);

        //Users Tipsters ------------------------------------->

        User user1 = new User();
        user1.setId((long) 2);
        user1.setUsername("Kiko");
        user1.setPassword("$2a$04$gXQQqZ/Hx..Mi0imMrXKFuFcJKwcoiwxa8QInBlDHT8p/yWx8zhg.");
        user1.setEmail("Kristijan@gmail.com");
        userRepository.save(user1);

        role1.setUsers(Collections.singletonList(user1));
        user1.setRoles(Collections.singletonList(role1));

        roleRepository.save(role1);
        userRepository.save(user1);

        User user2 = new User();
        user2.setId((long) 3);
        user2.setUsername("Borce");
        user2.setPassword("$2a$04$gXQQqZ/Hx..Mi0imMrXKFuFcJKwcoiwxa8QInBlDHT8p/yWx8zhg.");
        user2.setEmail("Borceborcev@gmail.com");

        userRepository.save(user2);
        role1.setUsers(Collections.singletonList(user2));
        user2.setRoles(Collections.singletonList(role1));

        roleRepository.save(role1);
        userRepository.save(user2);

        User user3 = new User();
        user3.setId((long) 4);
        user3.setUsername("Dras");
        user3.setPassword("$2a$04$gXQQqZ/Hx..Mi0imMrXKFuFcJKwcoiwxa8QInBlDHT8p/yWx8zhg.");
        user3.setEmail("Dragan@gmail.com");

        userRepository.save(user3);
        role1.setUsers(Collections.singletonList(user3));
        user3.setRoles(Collections.singletonList(role1));

        roleRepository.save(role1);
        userRepository.save(user3);

       //Private Users ------------------------------------->

        User user4 = new User();
        user4.setId((long) 5);
        user4.setUsername("Bogdan");
        user4.setPassword("$2a$04$gXQQqZ/Hx..Mi0imMrXKFuFcJKwcoiwxa8QInBlDHT8p/yWx8zhg.");
        user4.setEmail("Bogdan@gmail.com");

        userRepository.save(user4);
        role2.setUsers(Collections.singletonList(user4));
        user4.setRoles(Collections.singletonList(role2));

        roleRepository.save(role2);
        userRepository.save(user4);

        User user5 = new User();
        user5.setId((long) 6);
        user5.setUsername("Kliment");
        user5.setPassword("$2a$04$gXQQqZ/Hx..Mi0imMrXKFuFcJKwcoiwxa8QInBlDHT8p/yWx8zhg.");
        user5.setEmail("Kliment@gmail.com");

        userRepository.save(user5);
        role2.setUsers(Collections.singletonList(user5));
        user5.setRoles(Collections.singletonList(role2));

        roleRepository.save(role2);
        userRepository.save(user5);


        //Private Users--------------------------->
        PrivateUser privateUser1 = new PrivateUser();
        privateUser1.setId(1);
        privateUser1.setNumOfSubscribers(2);
        privateUser1.setCostSubscribe(25);
        privateUser1.setMoney(50);
        privateUser1.setUserId(user4);

        privateUserRepository.save(privateUser1);
        privateUser1.setUserSubscribers(Collections.singletonList(user1));
        privateUser1.setUserSubscribers(Collections.singletonList(user2));
        user1.setSubscribers(Collections.singletonList(privateUser1));
        user2.setSubscribers(Collections.singletonList(privateUser1));

        userRepository.save(user1);
        userRepository.save(user2);
        privateUserRepository.save(privateUser1);

        PrivateUser privateUser2 = new PrivateUser();
        privateUser2.setId(2);
        privateUser2.setNumOfSubscribers(3);
        privateUser2.setCostSubscribe(40);
        privateUser2.setMoney(120);
        privateUser2.setUserId(user5);

        privateUserRepository.save(privateUser2);
        privateUser2.setUserSubscribers(Collections.singletonList(user1));
        privateUser2.setUserSubscribers(Collections.singletonList(user2));
        privateUser2.setUserSubscribers(Collections.singletonList(user3));
        user1.setSubscribers(Collections.singletonList(privateUser2));
        user2.setSubscribers(Collections.singletonList(privateUser2));
        user3.setSubscribers(Collections.singletonList(privateUser2));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        privateUserRepository.save(privateUser2);

        //Statistics ------------------------------------------>
        Statistic stat1 = new Statistic();
        stat1.setId(1);
        stat1.setNumPicks(0);
        stat1.setWin(0);
        stat1.setLose(0);
        stat1.set_void(0);
        stat1.setWinRatio(0);
        stat1.setProfit(0);

        statisticRepository.save(stat1);
        user1.setStatisticId(stat1);
        userRepository.save(user1);

        Statistic stat2 = new Statistic();
        stat2.setId(2);
        stat2.setNumPicks(0);
        stat2.setWin(0);
        stat2.setLose(0);
        stat2.set_void(0);
        stat2.setWinRatio(0);
        stat2.setProfit(0);

        statisticRepository.save(stat2);
        user2.setStatisticId(stat2);
        userRepository.save(user2);

        Statistic stat3 = new Statistic();
        stat3.setId(3);
        stat3.setNumPicks(0);
        stat3.setWin(0);
        stat3.setLose(0);
        stat3.set_void(0);
        stat3.setWinRatio(0);
        stat3.setProfit(0);

        statisticRepository.save(stat3);
        user3.setStatisticId(stat3);
        userRepository.save(user3);


        Statistic stat4 = new Statistic();
        stat4.setId(4);
        stat4.setNumPicks(0);
        stat4.setWin(0);
        stat4.setLose(0);
        stat4.set_void(0);
        stat4.setWinRatio(0);
        stat4.setProfit(0);

        statisticRepository.save(stat4);
        user4.setStatisticId(stat4);
        userRepository.save(user4);

        Statistic stat5 = new Statistic();
        stat5.setId(5);
        stat5.setNumPicks(0);
        stat5.setWin(0);
        stat5.setLose(0);
        stat5.set_void(0);
        stat5.setWinRatio(0);
        stat5.setProfit(0);

        statisticRepository.save(stat5);
        user5.setStatisticId(stat5);
        userRepository.save(user5);

        //--------------------Bookmarks-------------------------->
        Bookmark bookmark = new Bookmark();
        bookmark.setId(1);
        bookmark.setName("Bet365");
        bookmark.setRating((float) 4.5);
        bookmarkRepository.save(bookmark);

        Bookmark bookmark1 = new Bookmark();
        bookmark1.setId(2);
        bookmark1.setName("Bet3000");
        bookmark1.setRating((float) 3.2);
        bookmarkRepository.save(bookmark1);

        Bookmark bookmark2 = new Bookmark();
        bookmark2.setId(3);
        bookmark2.setName("SOBET");
        bookmark2.setRating((float) 3);
        bookmarkRepository.save(bookmark2);

        //--------------------Sports------------------------->
        Sport sport = new Sport();
        sport.setId(1);
        sport.setName("Football");
        sportRepository.save(sport);

        Sport sport1 = new Sport();
        sport1.setId(2);
        sport1.setName("Basketball");
        sportRepository.save(sport1);

        Sport sport2 = new Sport();
        sport2.setId(3);
        sport2.setName("HandBall");
        sportRepository.save(sport2);

        //--------------------Clubs-------------------------->
        Club club = new Club();
        club.setId(1);
        club.setName("Chelsea");
        club.setYear(1568);
        club.setCountry("England");
        club.setSport(sport);
        club.setIndividual("No");
        club.setLeagues(null);
        clubRepository.save(club);

        Club club1 = new Club();
        club1.setId(2);
        club1.setName("Liverpool");
        club1.setYear(1577);
        club1.setCountry("England");
        club1.setSport(sport);
        club1.setIndividual("No");
        club1.setLeagues(null);
        clubRepository.save(club1);

        Club club2 = new Club();
        club2.setId(3);
        club2.setName("Arsenal");
        club2.setYear(1342);
        club2.setCountry("England");
        club2.setSport(sport);
        club2.setIndividual("No");
        club2.setLeagues(null);
        clubRepository.save(club2);

        Club club3 = new Club();
        club3.setId(4);
        club3.setName("Manchester City");
        club3.setYear(1535);
        club3.setCountry("England");
        club3.setSport(sport);
        club3.setIndividual("No");
        club3.setLeagues(null);
        clubRepository.save(club3);

        Club club4 = new Club();
        club4.setId(5);
        club4.setName("Manchester United");
        club4.setYear(1235);
        club4.setCountry("England");
        club4.setSport(sport);
        club4.setIndividual("No");
        club4.setLeagues(null);
        clubRepository.save(club4);

        Club club5 = new Club();
        club5.setId(6);
        club5.setName("Manchester United");
        club5.setYear(1235);
        club5.setCountry("England");
        club5.setSport(sport);
        club5.setIndividual("No");
        club5.setLeagues(null);
        clubRepository.save(club5);

        //--------------------League-------------------------->
        League league = new League();
        league.setId(1);
        league.setName("Premier League");
        league.setSport(sport);
        league.setClubs(null);
        leagueRepository.save(league);

        league.setClubs(Collections.singletonList(club));
        league.setClubs(Collections.singletonList(club1));
        league.setClubs(Collections.singletonList(club2));
        league.setClubs(Collections.singletonList(club3));
        league.setClubs(Collections.singletonList(club4));
        league.setClubs(Collections.singletonList(club5));
        leagueRepository.save(league);

        club.setLeagues(Collections.singletonList(league));
        club1.setLeagues(Collections.singletonList(league));
        club2.setLeagues(Collections.singletonList(league));
        club3.setLeagues(Collections.singletonList(league));
        club4.setLeagues(Collections.singletonList(league));
        club5.setLeagues(Collections.singletonList(league));
        clubRepository.save(club);
        clubRepository.save(club1);
        clubRepository.save(club2);
        clubRepository.save(club3);
        clubRepository.save(club4);
        clubRepository.save(club5);

        League league1 = new League();
        league1.setId(2);
        league1.setName("League Champions");
        league1.setSport(sport);
        league1.setClubs(null);
        leagueRepository.save(league1);

        League league2 = new League();
        league2.setId(3);
        league2.setName("League Europe");
        league2.setSport(sport);
        league2.setClubs(null);
        leagueRepository.save(league2);

        //--------------------Matches------------------------>
        SportMatch match = new SportMatch();
        match.setId(1);
        match.setName("Chelsea vs Liverpool");
        match.setStatus("PreMatch");
        match.setTime("2020-03-04");
        match.setClubHome(club);
        match.setClubAway(club1);
        sportMatchRepository.save(match);

        SportMatch match1 = new SportMatch();
        match1.setId(2);
        match1.setName("Arsenal vs Manchester City");
        match1.setStatus("PreMatch");
        match1.setTime("2020-03-04");
        match1.setClubHome(club2);
        match1.setClubAway(club3);
        sportMatchRepository.save(match1);

        SportMatch match2 = new SportMatch();
        match2.setId(3);
        match2.setName("Manchester United vs Everton");
        match2.setStatus("PreMatch");
        match2.setTime("2020-03-04");
        match2.setClubHome(club4);
        match2.setClubAway(club5);
        sportMatchRepository.save(match2);

        //--------------------Picks-------------------------->
        Pick pick = new Pick();
        pick.setId(1);
        pick.setData("2020-03-03");
        pick.setPick("1");
        pick.setOdd((float) 1.85);
        pick.setStake(7);
        pick.setStatus("null");
        pick.setInfo("asbjdhjsavdajvhdjvdja");
        pick.setStatus_private_public("public");
        pick.setUser(user1);
        pick.setBookmark(bookmark);
        pick.setSportMatch(match);
        pickRepository.save(pick);

        Pick pick1 = new Pick();
        pick1.setId(2);
        pick1.setData("2020-03-03");
        pick1.setPick("X");
        pick1.setOdd((float) 3.25);
        pick1.setStake(5);
        pick1.setStatus("null");
        pick1.setInfo("asbjdhjsavdajvhdjvdja");
        pick1.setStatus_private_public("public");
        pick1.setUser(user2);
        pick1.setBookmark(bookmark1);
        pick1.setSportMatch(match1);
        pickRepository.save(pick1);

        Pick pick2 = new Pick();
        pick2.setId(3);
        pick2.setData("2020-03-03");
        pick2.setPick("Over 2.5");
        pick2.setOdd((float) 1.95);
        pick2.setStake(7);
        pick2.setStatus("null");
        pick2.setInfo("asbjdhjsavdajvhdjvdja");
        pick2.setStatus_private_public("public");
        pick2.setUser(user3);
        pick2.setBookmark(bookmark2);
        pick2.setSportMatch(match2);
        pickRepository.save(pick2);

    }
}

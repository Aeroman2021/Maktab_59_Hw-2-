package hw16.q1.presentation.viewer;

import hw16.q1.entity.MatchSchedule;
import hw16.q1.entity.Stadium;
import hw16.q1.entity.Team;
import hw16.q1.entity.TeamPerformance;
import hw16.q1.manager.MatchScheduleManager;
import hw16.q1.manager.TeamPerformanceManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;
import java.sql.Date;

public class MatchScheduleViewer {
    MatchScheduleManager matchScheduleManager;
    TeamPerformanceManager teamPerformanceManager;
    EntityManager em = EMFSigleton.getEntityManager();

    public MatchScheduleViewer() {
        matchScheduleManager = new MatchScheduleManager();
        teamPerformanceManager = new TeamPerformanceManager();
    }

    public void saveMatchSchedule() {


        Integer homeTeamId = Input.getInputValue("Enter home_team id");
        Team home = em.find(Team.class, homeTeamId);
        Stadium stadium = home.getStadium();
        Integer awayTeamId = Input.getInputValue("Enter away_team id");
        Team away = em.find(Team.class, awayTeamId);

        int matchDay = Input.getInputValue("Enter the day of the match");
        int matchMonth = Input.getInputValue("Enter the month of the match");
        String strDate = 2021 + "-" + matchMonth + "-" + matchDay;
        Date date = Date.valueOf(strDate);

        MatchSchedule matchSchedule = new MatchSchedule();
        matchSchedule.setDate(date);
        matchSchedule.setStadium(stadium);
        matchSchedule.setHomeTeam(home);
        matchSchedule.setAwayTeam(away);
        matchScheduleManager.saveOrUpdate(matchSchedule);
    }

    public void updateMatchSchedule() {
        int id = Input.getInputValue("Enter match schedule id");
        MatchSchedule matchSchedule = em.find(MatchSchedule.class, id);
        Team homeTeam = matchSchedule.getHomeTeam();
        Team awayTeam = matchSchedule.getAwayTeam();

        int matchDay = Input.getInputValue("Enter the day of the match");
        int matchMonth = Input.getInputValue("Enter the month of the match");
        String strDate = 2021 + "-" + matchMonth + "-" + matchDay;
        Date date = Date.valueOf(strDate);

        TeamPerformance homeTeamPerformance = new TeamPerformance();
        TeamPerformance awayTeamPerformance = new TeamPerformance();
        homeTeamPerformance.setTeam(homeTeam);
        awayTeamPerformance.setTeam(awayTeam);
        int homeTeamScoredGoals = Input.getInputValue("Enter scored goals for the home team");
        homeTeamPerformance.setGoalScored(homeTeamScoredGoals);
        awayTeamPerformance.setGoalsrecived(homeTeamScoredGoals);
        int homeTeamReceivedGoals = Input.getInputValue("Enter scored goals for the home team");
        homeTeamPerformance.setGoalsrecived(homeTeamReceivedGoals);
        awayTeamPerformance.setGoalScored(homeTeamReceivedGoals);
        if (homeTeamScoredGoals > homeTeamReceivedGoals) {
            homeTeamPerformance.setPoint(3);
            awayTeamPerformance.setPoint(0);
        } else if (homeTeamScoredGoals > homeTeamReceivedGoals) {
            homeTeamPerformance.setPoint(0);
            awayTeamPerformance.setPoint(3);
        } else {
            homeTeamPerformance.setPoint(1);
            awayTeamPerformance.setPoint(1);
        }
        teamPerformanceManager.saveOrUpdate(homeTeamPerformance);
        teamPerformanceManager.saveOrUpdate(awayTeamPerformance);
        matchSchedule.setHomeTeamPerformance(homeTeamPerformance);
        matchSchedule.setAwayTeamPerformance(awayTeamPerformance);
        matchScheduleManager.saveOrUpdate(matchSchedule);
    }

    public void deleteMatchSchedule() {
        int id = Input.getInputValue("Enter match schedule id");
        matchScheduleManager.delete(id);
    }


    public void loadMatchScheduleById() {
        int id = Input.getInputValue("Enter match schedule id");
        MatchSchedule matchSchedule = matchScheduleManager.loadById(id);
        System.out.println(" match number " +
                matchSchedule.getId() + " between " +
                matchSchedule.getHomeTeam().getName() + " and " +
                matchSchedule.getAwayTeam().getName() + " at " +
                matchSchedule.getDate() + " in " +
                matchSchedule.getStadium().getName() + " stadium");
    }

    public void loadAllMatchSchedule() {
        for (MatchSchedule matchSchedule : matchScheduleManager.loadAll()) {
            System.out.println(" match number " +
                    matchSchedule.getId() + " between " +
                    matchSchedule.getHomeTeam().getName() + " and " +
                    matchSchedule.getAwayTeam().getName() + " at " +
                    matchSchedule.getDate() + " in " +
                    matchSchedule.getStadium().getName() + " stadium");
        }

    }
}

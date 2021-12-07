package hw16.q1.presentation.viewer;

import hw16.q1.entity.TeamPerformance;
import hw16.q1.manager.TeamPerformanceManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;

public class TeamPerformanceViewer {
    TeamPerformanceManager teamPerformanceManager;
    EntityManager em = EMFSigleton.getEntityManager();

    public TeamPerformanceViewer() {
        teamPerformanceManager = new TeamPerformanceManager();
    }

    public void save() {

    }

    public void update() {

    }

    public void delete() {
        int teamPerformanceId = Input.getInputValue("Enter team performance id");
        teamPerformanceManager.delete(teamPerformanceId);
    }

    public void loadById() {
        Integer teamPerformanceId = Input.getInputValue("Enter team performance id");
        TeamPerformance teamPerformance = teamPerformanceManager.loadById(teamPerformanceId);
        System.out.println(
                "Team " + teamPerformance.getTeam().getName() +
                        " with performance id " + teamPerformance.getId() +
                        " Scored " + teamPerformance.getGoalScored() +
                        " goals and received " +
                        teamPerformance.getGoalsrecived() + " goals and the point is " +
                        teamPerformance.getPoint()
        );

    }

    public void loadAll() {
        for (TeamPerformance teamPerformance : teamPerformanceManager.loadAll()) {
            System.out.println(
                    "Team " + teamPerformance.getTeam().getName() +
                            " with performance id " + teamPerformance.getId() +
                            " Scored " + teamPerformance.getGoalScored() +
                            " goals and received " +
                            teamPerformance.getGoalsrecived() + " goals and the point is " +
                            teamPerformance.getPoint()
            );
        }
    }

    public void printRankingTableList() {
        teamPerformanceManager.getBaseDao().rankingTable();
    }
}

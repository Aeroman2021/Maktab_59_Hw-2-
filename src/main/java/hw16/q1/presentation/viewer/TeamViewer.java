package hw16.q1.presentation.viewer;

import hw16.q1.entity.City;
import hw16.q1.entity.Stadium;
import hw16.q1.entity.Team;
import hw16.q1.manager.TeamManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamViewer {
    private TeamManager teamManager;
    EntityManager em= EMFSigleton.getEntityManager();

    public TeamViewer() {
        this.teamManager = new TeamManager();
    }



    public void saveTeam() {
        String name = Input.getStringInputValue("Enter name");
        int cityId = Input.getInputValue("Enter city id");
        City city = em.find(City.class, cityId);
        int stadiumId = Input.getInputValue("Enter stadium id");
        Stadium stadium = em.find(Stadium.class,stadiumId);
        Team team = new Team();
        team.setName(name);
        team.setCity(city);
        team.setStadium(stadium);
        teamManager.saveOrUpdate(team);
    }


    public void updateTeam() {
//        int teamId = Input.getInputValue("Enter team id");
//        Team foundTeam = em.find(Team.class,teamId);
//        String name = Input.getStringInputValue("Enter name");
//        String stadiumName = Input.getStringInputValue("Enter stadium name");
//        String city = Input.getStringInputValue("Enter city name");
//        foundTeam.setName(name);
//        foundTeam.setStadium(stadiumName);

    }

    public void deleteTeam() {
        int teamId = Input.getInputValue("Enter team id");
        teamManager.delete(teamId);
    }


    public Team loadTeamById() {
        int teamId = Input.getInputValue("Enter team id");
        return teamManager.loadById(teamId);

    }

    public List<Team> loadAll() {
        return teamManager.loadAll();
    }
}

package hw16.q1.presentation.viewer;

import hw16.q1.entity.Player;
import hw16.q1.entity.Team;
import hw16.q1.manager.PlayerManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class PlayerViewer {

    private PlayerManager playerManager;
    EntityManager em = EMFSigleton.getEntityManager();

    public PlayerViewer() {
        playerManager = new PlayerManager();
    }

    public void savePlayer() {
        String name = Input.getStringInputValue("Enter name");
        String lastname = Input.getStringInputValue("Enter lastname");
        Long contract = Input.getLongValue("Enter contract");
        Integer age = Input.getInputValue("Enter age");
        Integer teamId = Input.getInputValue("Enter team Id");
        Team foundTeam = em.find(Team.class, teamId);
        String position = Input.getStringInputValue("Enter position");
        Long salary = Input.getLongValue("Enter salary");
        Player newPlayer = new Player(name,lastname,contract,age,foundTeam,position,salary);
        playerManager.saveOrUpdate(newPlayer);
    }

    public void updatePlayer() {
        Integer id = Input.getInputValue("Enter player id");
        Player foundPlayer = em.find(Player.class, id);
        Long contract = Input.getLongValue("Enter contract");
        Integer teamId = Input.getInputValue("Enter team Id");
        Team foundTeam = em.find(Team.class, teamId);
        String position = Input.getStringInputValue("Enter position");
        Long salary = Input.getLongValue("Enter salary");
        foundPlayer.setContract(contract);
        foundPlayer.setPosition(position);
        foundPlayer.setSalary(salary);
        foundPlayer.setTeam(foundTeam);
        playerManager.saveOrUpdate(foundPlayer);
    }

    public void deletePlayer() {
        int id = Input.getInputValue("Enter the player id");
        playerManager.delete(id);
    }

    public Player loadPlayerById() {
        int id = Input.getInputValue("Enter the player id");
        return playerManager.loadById(id);
    }

    public List<Player> loadAllPlayers() {
        return playerManager.loadAll();
    }

    public void printPlayersListBaseOnContract() {
        for (Player player : playerManager.printListOfPlayersBasedOnSalary()) {
            System.out.println(player);
        }
    }

    public void printTheMostPaidPlayer() {
        Player player = playerManager.printTheMostPaidPlayer();
        System.out.println(player);
    }
}

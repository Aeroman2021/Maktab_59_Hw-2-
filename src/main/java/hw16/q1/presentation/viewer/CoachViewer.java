package hw16.q1.presentation.viewer;

import hw16.q1.entity.City;
import hw16.q1.entity.Coach;
import hw16.q1.entity.Team;
import hw16.q1.manager.CoachManager;
import hw16.q1.utility.EMFSigleton;
import hw16.q1.utility.Input;

import javax.persistence.EntityManager;
import java.util.List;

public class CoachViewer {
    CoachManager coachManager;
    EntityManager em = EMFSigleton.getEntityManager();

    public CoachViewer() {
        coachManager = new CoachManager();
    }

    public void saveCoach() {
        String name = Input.getStringInputValue("Enter the coach name");
        String lastname = Input.getStringInputValue("Enter the coach lastname");
        Long contract = Input.getLongValue("Enter the amount of contract");
        Integer age = Input.getInputValue("Enter age");
        Integer teamId = Input.getInputValue("Enter team Id");
        Team foundTeam = em.find(Team.class, teamId);
        Coach newCoach = new Coach(name,lastname,contract,age,foundTeam);
        coachManager.saveOrUpdate(newCoach);
    }

    public void updateCoach() {
        Integer id = Input.getInputValue("Enter the coach id");
        Coach foundCoach = em.find(Coach.class, id);
        Long contract = Input.getLongValue("Enter the amount of contract");
        Integer teamId = Input.getInputValue("Enter team Id");
        Team foundTeam = em.find(Team.class, teamId);
        foundCoach.setTeam(foundTeam);
        foundCoach.setContract(contract);
        coachManager.saveOrUpdate(foundCoach);
    }

    public void deleteCoach() {
        int id = Input.getInputValue("Enter coach id");
        coachManager.delete(id);
    }

    public Coach loadCoachById() {
        int id = Input.getInputValue("Enter coach id");
        return coachManager.loadById(id);
    }

    public List<Coach> loadAll() {
        return coachManager.loadAll();
    }

    public void printCoachListBaseOnContract() {
        for (Coach coach : coachManager.printCoachListBasedOnContract())
            System.out.println(coach);
    }

    public void printTheMostPaidCoach(){
        Coach coach = coachManager.PrintMostPaidCoach();
        System.out.println(coach);
    }

}

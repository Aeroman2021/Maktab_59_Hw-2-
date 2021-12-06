package hw16.q1.dao;

import hw16.q1.entity.Team;
import hw16.q1.entity.TeamPerformance;
import hw16.q1.exception.DataNotFoundException;
import hw16.q1.exception.DuplicateInputDataException;
import hw16.q1.utility.EMFSigleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeamPerformanceDao implements BaseDao<TeamPerformance, Integer> {
    EntityManager em = EMFSigleton.getEntityManager();


    @Override
    public void save(TeamPerformance teamPerformance) {
        if(!entityIsExist(teamPerformance)){
            em.getTransaction().begin();
            em.persist(teamPerformance);
            em.getTransaction().commit();
        }
        else
            throw new DuplicateInputDataException("The input Data is already exist in the data base! ");
    }

    @Override
    public void update(TeamPerformance teamPerformance) {
        em.getTransaction().begin();
        em.merge(teamPerformance);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        TeamPerformance teamPerformance = em.find(TeamPerformance.class, id);
        if (em.find(TeamPerformance.class, id) != null) {
            em.remove(teamPerformance);
            em.getTransaction().commit();
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public TeamPerformance loadById(Integer id) {
        em.getTransaction().begin();
        TeamPerformance teamPerformance = em.find(TeamPerformance.class, id);
        if (em.find(TeamPerformance.class, id) != null) {
            return teamPerformance;
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public List<TeamPerformance> loadAll() {
        TypedQuery<TeamPerformance> namedQuery = em.createNamedQuery("teamPerformance.loadAll",
                TeamPerformance.class);
        return namedQuery.getResultList();
    }

    @Override
    public boolean entityIsExist(TeamPerformance teamPerformance) {
        for (TeamPerformance performance : loadAll()) {
            if(performance.equals(teamPerformance))
                return true;
        }
        return false;
    }

    public void rankingTable(){
//                "SELECT " +
//                " team_id," +
//                " premire_league.team.name," +
//                " SUM(point) AS 'point'," +
//                " SUM(scored_golas) - SUM(recieved_goals) AS goal_difference" +
//                " FROM premire_league.team_match_performance" +
//                " JOIN premire_league.team" +
//                " ON premire_league.team_match_performance.team_id = premire_league.team.id" +
//                " GROUP BY team_id" +
//                " ORDER BY `point` DESC";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> c = cb.createQuery(Object[].class);
        Root<TeamPerformance> teamPerformance = c.from(TeamPerformance.class);
        Join<TeamPerformance, Team> team = teamPerformance.join("team");
        CriteriaQuery<Object[]> criteriaQuery = c.multiselect(team.get("name"),
                        cb.sum(teamPerformance.get("point")))
                .groupBy(team.get("id"))
                .orderBy(cb.desc(cb.sum(teamPerformance.get("point"))));
        List<Object[]> objects = em.createQuery(c).getResultList();
        System.out.print("Team");
        System.out.println("\t point");
        System.out.println("-------------------");

        for (int i = 0; i < objects.size(); i++) {
            System.out.println(i+1 + " " + objects.get(i)[0] + " " + objects.get(i)[1]);

        }
        System.out.println("-------------------");
        System.out.println("The champion is " + objects.get(0)[0]);
    }





}

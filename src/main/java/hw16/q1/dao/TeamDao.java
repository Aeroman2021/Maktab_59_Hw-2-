package hw16.q1.dao;

import hw16.q1.entity.City;
import hw16.q1.entity.Coach;
import hw16.q1.entity.Team;
import hw16.q1.exception.DataNotFoundException;
import hw16.q1.exception.DuplicateInputDataException;
import hw16.q1.utility.EMFSigleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeamDao implements BaseDao<Team, Integer> {
    EntityManager em = EMFSigleton.getEntityManager();

    @Override
    public void save(Team team) {
        if (!entityIsExist(team)) {
            em.getTransaction().begin();
            em.persist(team);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputDataException("The input Data is already exist in the data base! ");

    }

    @Override
    public void update(Team team) {
        em.getTransaction().begin();
        em.merge(team);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        Team team = em.find(Team.class, id);
        if (team != null) {
            em.remove(team);
            em.getTransaction().commit();
        } else
            throw new DataNotFoundException("Data not found in the database");

    }

    @Override
    public Team loadById(Integer id) {
        em.getTransaction().begin();
        Team team = em.find(Team.class, id);
        if (team != null) {
            return team;
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public List<Team> loadAll() {
        TypedQuery<Team> namedQuery = em.createNamedQuery("team.loadAll", Team.class);
        return namedQuery.getResultList();
    }

    @Override
    public boolean entityIsExist(Team entity) {
        for (Team team : loadAll()) {
            if(team.equals(entity))
                return true;
        }
        return false;
    }

}

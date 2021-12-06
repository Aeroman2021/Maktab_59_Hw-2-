package hw16.q1.dao;

import hw16.q1.entity.MatchSchedule;
import hw16.q1.exception.DataNotFoundException;
import hw16.q1.exception.DuplicateInputDataException;
import hw16.q1.utility.EMFSigleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MatchScheduleDao implements BaseDao<MatchSchedule, Integer> {
    EntityManager em = EMFSigleton.getEntityManager();

    @Override
    public void save(MatchSchedule matchSchedule) {
        if (!entityIsExist(matchSchedule)) {
            em.getTransaction().begin();
            em.persist(matchSchedule);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputDataException("The input Data is already exist in the data base! ");
    }

    @Override
    public void update(MatchSchedule matchSchedule) {
        em.getTransaction().begin();
        em.merge(matchSchedule);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        MatchSchedule matchSchedule = em.find(MatchSchedule.class, id);
        if (em.find(MatchSchedule.class, id) != null) {
            em.remove(matchSchedule);
            em.getTransaction().commit();
        } else
            throw new DataNotFoundException("Data not found in the database");

    }

    @Override
    public MatchSchedule loadById(Integer id) {
        em.getTransaction().begin();
        MatchSchedule matchSchedule = em.find(MatchSchedule.class, id);
        if (em.find(MatchSchedule.class, id) != null) {
            return matchSchedule;
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public List<MatchSchedule> loadAll() {
        TypedQuery<MatchSchedule> namedQuery = em.createNamedQuery("matchSchedule.loadAll",
                MatchSchedule.class);
        return namedQuery.getResultList();
    }

    @Override
    public boolean entityIsExist(MatchSchedule entity) {
        for (MatchSchedule matchSchedule : loadAll()) {
            if(matchSchedule.equals(entity))
                return true;
        }
        return false;
    }
}

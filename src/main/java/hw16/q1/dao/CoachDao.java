package hw16.q1.dao;

import hw16.q1.entity.Coach;
import hw16.q1.exception.DataNotFoundException;
import hw16.q1.exception.DuplicateInputDataException;
import hw16.q1.utility.EMFSigleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CoachDao implements BaseDao<Coach, Integer> {

    EntityManager em = EMFSigleton.getEntityManager();


    @Override
    public void save(Coach coach) {
        if (!entityIsExist(coach)) {
            em.getTransaction().begin();
            em.persist(coach);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputDataException("The input Data is already exist in the data base! ");
    }

    @Override
    public void update(Coach coach) {
        em.getTransaction().begin();
        em.merge(coach);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        Coach coach = em.find(Coach.class, id);
        if (coach != null) {
            em.remove(coach);
            em.getTransaction().commit();
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public Coach loadById(Integer id) {
        em.getTransaction().begin();
        Coach coach = em.find(Coach.class, id);
        if (em.find(Coach.class, id) != null) {
            return coach;
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public List<Coach> loadAll() {
        TypedQuery<Coach> query = em.createNamedQuery("coach.loadAll", Coach.class);
        query.setMaxResults(100);
        List<Coach> result = query.getResultList();
        if (result != null)
            return result;
        else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public boolean entityIsExist(Coach coach) {
        for (Coach currentCoach : loadAll())
            if (currentCoach.equals(coach))
                return true;

        return false;
    }

    public List<Coach> SortCoachBasedOnContract(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Coach> c = cb.createQuery(Coach.class);
        Root<Coach> coach = c.from(Coach.class);
        CriteriaQuery<Coach> contract = c.orderBy(cb.desc(coach.get("contract")));
        TypedQuery<Coach> query = em.createQuery(contract);
        return query.getResultList();
    }

    public Coach findMaxPaidCoach(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Coach> c = cb.createQuery(Coach.class);
        Root<Coach> coach = c.from(Coach.class);
        CriteriaQuery<Coach> contract = c.orderBy(cb.desc(coach.get("contract")));
        TypedQuery<Coach> query = em.createQuery(contract);
        return query.getResultList().get(0);

    }

}

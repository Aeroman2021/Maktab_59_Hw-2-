package hw16.q1.dao;

import hw16.q1.entity.City;
import hw16.q1.entity.Stadium;
import hw16.q1.exception.DataNotFoundException;
import hw16.q1.exception.DuplicateInputDataException;
import hw16.q1.utility.EMFSigleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StadiumDao implements BaseDao<Stadium, Integer> {

    EntityManager em = EMFSigleton.getEntityManager();


    @Override
    public void save(Stadium stadium) {
        if (!entityIsExist(stadium)) {
            em.getTransaction().begin();
            em.persist(stadium);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputDataException("The input Data is already exist in the data base! ");
    }

    @Override
    public void update(Stadium stadium) {
        em.getTransaction().begin();
        em.merge(stadium);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        Stadium stadium = em.find(Stadium.class, id);
        if (stadium != null) {
            em.remove(stadium);
            em.getTransaction().commit();
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public Stadium loadById(Integer id) {
        em.getTransaction().begin();
        Stadium stadium = em.find(Stadium.class, id);
        if (stadium != null) {
            return stadium;
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public List<Stadium> loadAll() {
        TypedQuery<Stadium> query = em.createNamedQuery("stadium.loadAll", Stadium.class);
        List<Stadium> result = query.getResultList();
        if (result != null)
            return result;
        else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public boolean entityIsExist(Stadium stadium) {
        for (Stadium currentStudium : loadAll()) {
            if(currentStudium.equals(stadium))
                return true;
        }
        return false;
    }
}

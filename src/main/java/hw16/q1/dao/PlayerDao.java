package hw16.q1.dao;

import hw16.q1.entity.Player;
import hw16.q1.exception.DataNotFoundException;
import hw16.q1.exception.DuplicateInputDataException;
import hw16.q1.utility.EMFSigleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PlayerDao implements BaseDao<Player, Integer> {

    EntityManager em = EMFSigleton.getEntityManager();

    @Override
    public void save(Player player) {
        if (!entityIsExist(player)) {
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputDataException("The input Data is already exist in the data base! ");

    }

    @Override
    public void update(Player player) {
        em.getTransaction().begin();
        em.merge(player);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        Player player = em.find(Player.class, id);
        if (player != null) {
            em.remove(player);
            em.getTransaction().commit();
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public Player loadById(Integer id) {
        em.getTransaction().begin();
        Player player = em.find(Player.class, id);
        if (player != null) {
            return player;
        } else
            throw new DataNotFoundException("Data not found in the database");
    }

    @Override
    public List<Player> loadAll() {
        em.getTransaction().begin();
        TypedQuery<Player> query = em.createNamedQuery("Player.loadAll", Player.class);
        query.setMaxResults(100);
        List<Player> result = query.getResultList();
        if (result != null)
            return result;
        else
            throw new DataNotFoundException("Data not found in the database");
    }


    @Override
    public boolean entityIsExist(Player player) {
        for (Player currentPlayer : loadAll())
            if (currentPlayer.equals(player))
                return true;
        return false;
    }

    public List<Player> SortPlayerBasedOnSalary(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> c = cb.createQuery(Player.class);
        Root<Player> player = c.from(Player.class);
        CriteriaQuery<Player> contract = c.orderBy(cb.desc(player.get("salary")));
        TypedQuery<Player> query = em.createQuery(contract);
        return query.getResultList();
    }

    public Player findMaxPaidPlayer(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> c = cb.createQuery(Player.class);
        Root<Player> player = c.from(Player.class);
        CriteriaQuery<Player> contract = c.orderBy(cb.desc(player.get("salary")));
        TypedQuery<Player> query = em.createQuery(contract);
        return query.getResultList().get(0);
    }


}

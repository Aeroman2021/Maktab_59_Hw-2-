package hw15.q1.dao;

import hw15.q1.entities.BankManager;

import javax.persistence.EntityManager;
import java.util.List;

public class BankManagerDao implements BaseDao<BankManager, Integer> {
    EntityManager entityManager;

    public BankManagerDao() {
        this.entityManager = emfSingleton.getEntityManager();
    }

    @Override
    public void save(BankManager bankManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(bankManager);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Integer integer, BankManager entity) {

    }

    @Override
    public void deleteByID(Integer integer) {

    }

    @Override
    public BankManager loadById(Integer id) {
        entityManager.getTransaction().begin();
        return entityManager.find(BankManager.class,id);
    }

    @Override
    public List<BankManager> loadAll() {
        return null;
    }

    @Override
    public boolean entityIsExist(BankManager bankManager) {
        return false;
    }
}

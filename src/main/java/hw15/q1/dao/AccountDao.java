package hw15.q1.dao;

import hw15.q1.entities.Account;

import hw15.q1.exception.DuplicateInputData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDao implements BaseDao<Account, Integer> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");
    EntityManager em = emf.createEntityManager();


    @Override
    public void save(Account newAccount) {
        em.getTransaction().begin();
        if (!entityIsExist(newAccount)) {
            em.persist(newAccount);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputData("DuplicateInputDataException");
    }

    @Override
    public void update(Integer number, Account newAccount) {
        em.getTransaction().begin();
        Account account = em.find(Account.class, number);
        account.setBalance(newAccount.getBalance());
        em.merge(account);
        em.getTransaction().commit();
    }

    @Override
    public void update(Account account) {
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
    }

    @Override
    public void deleteByID(Integer number) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Account account = entityManager.find(Account.class, number);
        entityManager.remove(account);
    }

    @Override
    public Account loadById(Integer number) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.find(Account.class, number);
    }

    @Override
    public List<Account> loadAll() {
        TypedQuery<Account> query = em.createNamedQuery("account.findAll", Account.class);
        query.setMaxResults(100);
        return query.getResultList();
    }

    @Override
    public boolean entityIsExist(Account account) {
        for (Account acc : loadAll()) {
            if (acc.equals(account))
                return true;
        }
        return false;
    }

}

package hw15.q1.dao;

import hw15.q1.entities.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransactionDao implements BaseDao<Transaction,Integer> {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");
    EntityManager em = emf.createEntityManager();

    @Override
    public void save(Transaction newTransaction) {
        em.getTransaction().begin();
        em.persist(newTransaction);
        em.getTransaction().commit();

    }

    @Override
    public void update(Integer integer, Transaction entity) {

    }

    @Override
    public void deleteByID(Integer integer) {

    }

    @Override
    public Transaction loadById(Integer id) {
        em.getTransaction().begin();
       return em.find(Transaction.class, id);

    }

    @Override
    public List<Transaction> loadAll() {
        TypedQuery<Transaction> query = em.createNamedQuery("transaction.finsAll", Transaction.class);
        return query.getResultList();
    }

    @Override
    public boolean entityIsExist(Transaction transaction) {
        return false;
    }
}

package hw15.q1.dao;

import hw15.q1.entities.CreditCard;
import hw15.q1.entities.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransactionDao implements BaseDao<Transaction,Integer> {


    private final EntityManagerFactory emf;

    public TransactionDao() {
        emf = Persistence.createEntityManagerFactory("bank_application");
    }

    @Override
    public void save(Transaction newTransaction) {
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
       return em.find(Transaction.class, id);

    }

    @Override
    public List<Transaction> loadAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Transaction> query = em.createNamedQuery("transaction.finsAll", Transaction.class);
        return query.getResultList();
    }
}

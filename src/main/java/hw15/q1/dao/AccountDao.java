package hw15.q1.dao;

import hw15.q1.entities.Account;
import hw15.q1.entities.Customer;
import hw15.q1.exception.DuplicateInputData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDao implements BaseDao<Account, Integer> {

    private final EntityManagerFactory emf;

    public AccountDao() {
        this.emf = Persistence.createEntityManagerFactory("bank_application");
    }


    @Override
    public void save(Account newAccount) {
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            if(!accountIsExist(newAccount)){
                entityManager.persist(newAccount);
                entityManager.getTransaction().commit();
            }else
                throw new DuplicateInputData("DuplicateInputDataException");
    }

    @Override
    public void update(Integer number, Account newAccount) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Account account = entityManager.find(Account.class, number);
        account.setBalance(newAccount.getBalance());
        entityManager.merge(account);
        entityManager.getTransaction().commit();
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
        return  entityManager.find(Account.class, number);
    }

    @Override
    public List<Account> loadAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Account> query = em.createNamedQuery("account.findAll", Account.class);
        query.setMaxResults(100);
        return query.getResultList();
    }

    private boolean accountIsExist(Account account){
        for (Account acc : loadAll()) {
            if(acc.equals(account))
                return true;
        }
        return false;
    }

}

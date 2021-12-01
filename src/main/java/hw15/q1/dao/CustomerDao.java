package hw15.q1.dao;

import hw15.q1.entities.Account;
import hw15.q1.entities.Customer;
import hw15.q1.exception.DuplicateInputData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;


public class CustomerDao implements BaseDao<Customer, Integer> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");
    EntityManager em = emf.createEntityManager();

    @Override
    public void save(Customer newCustomer) {
        em.getTransaction().begin();
        if (!entityIsExist(newCustomer)) {
            em.persist(newCustomer);
            em.getTransaction().commit();
        } else
            throw new DuplicateInputData("DuplicateInputDataException");
    }

    @Override
    public void update(Integer id, Customer newCustomer) {
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
    }

    @Override
    public void update(Customer newCustomer) {
        em.getTransaction().begin();
        em.merge(newCustomer);
        em.getTransaction().commit();
    }

    @Override
    public void deleteByID(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        entityManager.getTransaction().commit();

    }

    @Override
    public Customer loadById(Integer id) {
        em.getTransaction().begin();
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> loadAll() {
        TypedQuery<Customer> query = em.createNamedQuery("customer.findAll", Customer.class);
        return query.getResultList();
    }


    public void printAccountInformationById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);
        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts)
            System.out.println(account);
    }


    public Customer findByUserNameAndPassword(String username, String password) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customer = cq.from(Customer.class);
        cq.where(cb.equal(customer.get("username"), cb.parameter(String.class, "username")),
                cb.equal(customer.get("password"), cb.parameter(String.class, "password")));

        TypedQuery<Customer> query = em.createQuery(cq);
        query.setParameter("username",username);
        query.setParameter("password",password);
        return query.getSingleResult();
    }

    @Override
    public boolean entityIsExist(Customer customer) {
        for (Customer cust : loadAll()) {
            if (cust.equals(customer))
                return true;
        }
        return false;
    }
}

package hw15.q1.dao;

import hw15.q1.entities.Account;
import hw15.q1.entities.Customer;
import hw15.q1.exception.DuplicateInputData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class CustomerDao implements BaseDao<Customer, Integer> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");


    @Override
    public void save(Customer newCustomer) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (!customerIsExist(newCustomer)) {
            entityManager.persist(newCustomer);
            entityManager.getTransaction().commit();
        } else
            throw new DuplicateInputData("DuplicateInputDataException");
    }

    @Override
    public void update(Integer id, Customer newCustomer) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);

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
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> loadAll() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Customer> query = entityManager.createNamedQuery("customer.findAll", Customer.class);
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

//    public void printAccountInformationById(Customer customer) {
//        EntityManager entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
//        Customer customer = entityManager.find(Customer.class, id);
//        Set<Account> accounts = customer.getAccounts();
//        for (Account account : accounts)
//            System.out.println(account);
//    }


    private boolean customerIsExist(Customer customer) {
        for (Customer cust : loadAll()) {
            if (cust.equals(customer))
                return true;
        }
        return false;
    }

    public Customer findByUserNameAndPassword(String username, String password) {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c WHERE username=:username AND password=:password"
                , Customer.class).setParameter(username, username).setParameter(password, password);
        return customers.getResultList().get(0);
    }
}

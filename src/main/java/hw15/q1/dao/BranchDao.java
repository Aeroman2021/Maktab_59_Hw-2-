package hw15.q1.dao;

import hw15.q1.entities.Branch;
import hw15.q1.entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class BranchDao implements BaseDao<Branch,Integer>{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");
    EntityManager em = emf.createEntityManager();

    @Override
    public void save(Branch entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void update(Integer integer, Branch entity) {

    }

    @Override
    public void deleteByID(Integer integer) {

    }

    @Override
    public Branch loadById(Integer id) {
        return em.find(Branch.class,id);
    }

    @Override
    public List<Branch> loadAll() {
        TypedQuery<Branch> query = em.createNamedQuery("branch.findAll", Branch.class);
        return query.getResultList();
    }

    @Override
    public boolean entityIsExist(Branch branch) {
        return false;
    }


}

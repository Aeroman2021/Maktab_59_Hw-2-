package hw15.q1.dao;

import hw15.q1.entities.CreditCard;
import hw15.q1.exception.DuplicateInputData;
import hw15.q1.exception.InvalidCreditCardData;

import javax.persistence.*;
import java.util.List;

public class CreditCardDao implements BaseDao<CreditCard, Integer> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");

    @Override
    public void save(CreditCard creditCard) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (!entityIsExist(creditCard) && cardNumberChecker(creditCard.getNumber())) {
            entityManager.persist(creditCard);
            entityManager.getTransaction().commit();
        } else
            throw new InvalidCreditCardData("InvalidCardData");
    }

    @Override
    public void update(Integer id, CreditCard newCreditCard) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        CreditCard oldCreditCard = entityManager.find(CreditCard.class, id);
        oldCreditCard.setPassword(newCreditCard.getPassword());
        oldCreditCard.setCVV2(newCreditCard.getCVV2());
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(CreditCard entity) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteByID(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        CreditCard foundCreditCard = entityManager.find(CreditCard.class, id);
        entityManager.remove(foundCreditCard);
        entityManager.getTransaction().commit();
    }

    @Override
    public CreditCard loadById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.find(CreditCard.class, id);
    }

    @Override
    public List<CreditCard> loadAll() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<CreditCard> query = entityManager.
                createNamedQuery("credit_cards.findAll", CreditCard.class);
        return query.getResultList();

    }


    @Override
    public boolean entityIsExist(CreditCard creditCard) {
        for (CreditCard crdCard : loadAll()) {
            if (crdCard.equals(creditCard))
                return true;
        }
        return false;
    }

    public boolean cardNumberChecker(Long number) {
        int count = 0;
        while (number != 0) {
            number /= 10;
            count++;
        }
        return (count == 9 || count == 12);
    }

    public CreditCard findCardByAccountId(Integer accId) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CreditCard c WHERE c.account.id= :accId");
        return (CreditCard) query.setParameter("accId", accId).getSingleResult();
    }

    public CreditCard findCardByCardNumberAndPass(Long number, Integer password) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CreditCard c WHERE " +
                "c.number= :cardNumber AND c.password= :cardPassword");
        return (CreditCard) query.setParameter("cardNumber", number)
                .setParameter("cardPassword", password).getSingleResult();
    }

    public CreditCard findCardByCardNumber(Long number) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CreditCard c WHERE " +
                " c.number= :cardNumber ");
        return (CreditCard) query.setParameter("cardNumber", number)
                .getSingleResult();
    }


}

package hw15.q1.dao;

import hw15.q1.entities.Account;
import hw15.q1.entities.CreditCard;
import hw15.q1.exception.DuplicateInputData;
import hw15.q1.exception.InvalidCreditCardData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CreditCardDao implements BaseDao<CreditCard,Long> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank_application");

    @Override
    public void save(CreditCard creditCard) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if(creditCardIsExist(creditCard)){
            entityManager.persist(creditCard);
            entityManager.getTransaction().commit();
        }else
            throw new DuplicateInputData("DuplicateInputDataException");
    }

    @Override
    public void update(Long creditCardNumber, CreditCard newCreditCard) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        CreditCard oldCreditCard = entityManager.find(CreditCard.class, creditCardNumber);
        oldCreditCard.setPassword(newCreditCard.getPassword());
        oldCreditCard.setCVV2(newCreditCard.getCVV2());
        entityManager.getTransaction().commit();
    }


    @Override
    public void deleteByID(Long creditCardNumber) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        CreditCard foundCreditCard = entityManager.find(CreditCard.class, creditCardNumber);
        entityManager.remove(foundCreditCard);
        entityManager.getTransaction().commit();
    }

    @Override
    public CreditCard loadById(Long creditCardNumber) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager.find(CreditCard.class, creditCardNumber);
    }

    @Override
    public List<CreditCard> loadAll() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<CreditCard> query = entityManager.
                createNamedQuery("credit_cards.findAll", CreditCard.class);
        return query.getResultList();

    }

    public void createTransaction(Long srcCardNumber,Long destCardNumber,Double amount){
        if(transactionIsValid(srcCardNumber,destCardNumber,amount)){
            EntityManager entityManager = emf.createEntityManager();
            Account srcAccount = entityManager.find(Account.class, srcCardNumber);
            Account destAccount = entityManager.find(Account.class, destCardNumber);

            double updatedSrcCardBalance = loadById(srcCardNumber).getAccount().getBalance() - amount;
            srcAccount.setBalance(updatedSrcCardBalance);
            entityManager.merge(srcAccount);

            double updatedDestCardBalance = loadById(destCardNumber).getAccount().getBalance() + amount;
            destAccount.setBalance(updatedDestCardBalance);
            entityManager.merge(destAccount);

        }else
            throw new InvalidCreditCardData("InvalidCreditCard");
    }


    private boolean transactionIsValid(Long srcCardNumber,Long destCardNumber,Double amount){
        return (checkSrcAndDesCardsValidity(srcCardNumber,destCardNumber) &&
                checkSufficientMoneyValidity(srcCardNumber,amount));
    }

    private boolean checkSrcAndDesCardsValidity(Long srcCardNumber,Long destCardNumber){

        for (CreditCard creditCard : loadAll()) {
            if(creditCard.getNumber().equals(srcCardNumber) && creditCard.getNumber().equals(destCardNumber))
                return true;
        }
        return false;
    }

    private boolean checkSufficientMoneyValidity(Long srcCardNumber, Double amount){
        CreditCard srcCard = loadById(srcCardNumber);
         return srcCard.getAccount().getNumber() > amount;
    }

    private boolean creditCardIsExist(CreditCard creditCard){
        for (CreditCard crdCard : loadAll()) {
            if(crdCard.equals(creditCard))
                return true;
        }
        return false;
    }







}

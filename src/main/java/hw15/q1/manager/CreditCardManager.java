package hw15.q1.manager;

import hw15.q1.dao.BaseDao;
import hw15.q1.dao.CreditCardDao;
import hw15.q1.entities.CreditCard;

public class CreditCardManager extends AbstractCRUDService<CreditCard,Long> {

    public CreditCardManager() {
        setBaseDao(new CreditCardDao());
    }

    @Override
    public CreditCardDao getBaseDao() {
        return (CreditCardDao) super.getBaseDao();
    }

    public void createCreditCard(CreditCard creditCard,Long accNumber){

    }

    public void createTransactionByCard(Long srcCardNumber,Long destCardNumber,Double amount){
        getBaseDao().createTransaction(srcCardNumber,destCardNumber,amount);
    }
}

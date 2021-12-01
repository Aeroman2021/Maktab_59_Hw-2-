package hw15.q1.manager;

import hw15.q1.dao.CreditCardDao;
import hw15.q1.entities.CreditCard;

public class CreditCardManager extends AbstractCRUDService<CreditCard,Integer> {

    public CreditCardManager() {
        setBaseDao(new CreditCardDao());
    }

    @Override
    public CreditCardDao getBaseDao() {
        return (CreditCardDao) super.getBaseDao();
    }

    public CreditCard findCardByAccId(Integer accId){
        return getBaseDao().findCardByAccountId(accId);
    }

}

package hw15.q1.manager;

import hw15.q1.dao.BaseDao;
import hw15.q1.dao.TransactionDao;
import hw15.q1.entities.Transaction;

public class TransactionManager extends AbstractCRUDService<Transaction,Integer>{

    public TransactionManager() {
        setBaseDao(new TransactionDao());
    }

    @Override
    public TransactionDao getBaseDao() {
        return (TransactionDao) super.getBaseDao();
    }


}

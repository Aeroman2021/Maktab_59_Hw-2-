package hw15.q1.manager;

import hw15.q1.dao.BankManagerDao;
import hw15.q1.dao.BaseDao;
import hw15.q1.entities.BankManager;

public class BankManagerManager extends AbstractCRUDService<BankManager,Integer>{

    public BankManagerManager() {
        setBaseDao(new BankManagerDao());
    }

    @Override
    public BankManagerDao getBaseDao() {
        return (BankManagerDao) super.getBaseDao();
    }
}

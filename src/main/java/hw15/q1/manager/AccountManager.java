package hw15.q1.manager;

import hw15.q1.dao.AccountDao;
import hw15.q1.entities.Account;

public class AccountManager extends AbstractCRUDService<Account, Integer> {

    public AccountManager() {
        setBaseDao(new AccountDao());
    }

    @Override
    public AccountDao getBaseDao() {
        return (AccountDao) super.getBaseDao();
    }
}

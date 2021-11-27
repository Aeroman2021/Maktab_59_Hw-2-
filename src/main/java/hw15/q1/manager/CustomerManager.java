package hw15.q1.manager;

import hw15.q1.dao.CustomerDao;
import hw15.q1.entities.Customer;

public class CustomerManager extends AbstractCRUDService<Customer,Integer>{

    public CustomerManager() {
        setBaseDao(new CustomerDao());
    }

    @Override
    public CustomerDao getBaseDao() {
        return (CustomerDao) super.getBaseDao();
    }

    public Customer login(String username,String password){
        return getBaseDao().findByUserNameAndPassword(username,password);
    }
}

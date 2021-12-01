package hw15.q1.manager;

import hw15.q1.dao.BranchDao;
import hw15.q1.entities.Branch;

public class BranchManager extends AbstractCRUDService<Branch,Integer> {

    public BranchManager() {
        setBaseDao(new BranchDao());
    }

    @Override
    public BranchDao getBaseDao() {
        return (BranchDao) super.getBaseDao();
    }
}

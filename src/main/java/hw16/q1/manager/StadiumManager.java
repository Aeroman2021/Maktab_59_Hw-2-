package hw16.q1.manager;

import hw16.q1.dao.BaseDao;
import hw16.q1.dao.StadiumDao;
import hw16.q1.entity.Stadium;

public class StadiumManager extends AbstractCrudService<Stadium,Integer> {
    public StadiumManager() {
        setBaseDao(new StadiumDao());
    }

    @Override
    public StadiumDao getBaseDao() {
        return (StadiumDao) super.getBaseDao();
    }
}

package hw16.q1.manager;

import hw16.q1.dao.BaseDao;
import hw16.q1.dao.CityDao;
import hw16.q1.entity.City;

public class CityManager extends AbstractCrudService<City,Integer>{

    public CityManager() {
        setBaseDao(new CityDao());
    }

    @Override
    public CityDao getBaseDao() {
        return (CityDao) super.getBaseDao();
    }
}

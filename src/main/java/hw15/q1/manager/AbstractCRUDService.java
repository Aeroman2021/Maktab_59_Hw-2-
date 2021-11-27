package hw15.q1.manager;

import hw15.q1.dao.BaseDao;
import hw15.q1.entities.BaseEntity;


import java.util.List;

public class AbstractCRUDService<T extends BaseEntity<ID>, ID extends Number> {

    private BaseDao<T, ID> baseDao;

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseDao<T, ID> getBaseDao() {
        return baseDao;
    }

    public void save(T newEntity) {
        baseDao.save(newEntity);
    }

    public void update(ID id, T newEntity) {
        baseDao.update(id, newEntity);
    }

    public void delete(ID id) {
        baseDao.deleteByID(id);
    }

    public T loadById(ID id) {
        return baseDao.loadById(id);
    }

    public List<T> loadAll() {
        return baseDao.loadAll();
    }

}

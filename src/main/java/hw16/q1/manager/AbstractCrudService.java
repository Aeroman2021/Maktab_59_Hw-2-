package hw16.q1.manager;

import hw16.q1.dao.BaseDao;
import hw16.q1.entity.BaseEntity;

import java.util.List;

public class AbstractCrudService <T extends BaseEntity<ID>,ID extends Number> {

    private BaseDao<T,ID> baseDao;

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseDao<T, ID> getBaseDao() {
        return baseDao;
    }

    public void saveOrUpdate(T newEntity){
        if(newEntity.getId() == null)
            baseDao.save(newEntity);
        else
            baseDao.update(newEntity);
    }

    public void delete(ID id){
        baseDao.delete(id);
    }

    public T loadById(ID id){
        return baseDao.loadById(id);
    }

    public List<T> loadAll(){
        return baseDao.loadAll();
    }

    public boolean entityIsExist(T t){
        return baseDao.entityIsExist(t);
    }
}

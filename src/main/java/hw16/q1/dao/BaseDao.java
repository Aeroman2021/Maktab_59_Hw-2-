package hw16.q1.dao;

import hw16.q1.entity.BaseEntity;

import java.util.List;

public interface BaseDao <T extends BaseEntity<ID>,ID extends Number> {

    void save(T entity);
    void update(T entity);

    void delete(ID id);
    T loadById(ID id);
    List<T> loadAll();
    boolean entityIsExist(T entity);
}

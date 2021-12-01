package hw15.q1.dao;
import hw15.q1.entities.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity<ID>, ID extends Number> {



    void save(T entity);

    void update(ID id, T entity);

    default void update(ID id){}

    default void update(T entity){}

    void deleteByID(ID id);

    T loadById(ID id);

    List<T> loadAll();

    boolean entityIsExist(T t);

}

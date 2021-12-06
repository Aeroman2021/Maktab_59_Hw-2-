package hw16.q1.entity;

public interface BaseEntity<ID extends Number> {
    ID getId();

    void setId(ID id);
}

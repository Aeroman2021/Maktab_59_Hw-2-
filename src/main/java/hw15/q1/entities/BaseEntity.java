package hw15.q1.entities;


public interface BaseEntity<ID extends Number> {
    ID getId();
    void setId(ID id);

}

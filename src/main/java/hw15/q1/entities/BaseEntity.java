package hw15.q1.entities;
import java.io.Serializable;

public interface BaseEntity<ID extends Serializable> {
    ID getNumber();
    void setNumber(ID number);

}

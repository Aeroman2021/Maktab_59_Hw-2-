package hw16.q1.entity;


import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "coach.loadAll",query = "SELECT  c FROM Coach c")
public class Coach extends Person {

    public Coach(String firstName, String lastName, Long contract, Integer age, Team team) {
        super(firstName, lastName, contract, age, team);
    }

    public Coach() {
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Coach{}";
    }
}

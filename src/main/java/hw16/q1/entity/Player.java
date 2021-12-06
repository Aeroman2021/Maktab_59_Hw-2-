package hw16.q1.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Player.loadAll",query = "SELECT p FROM Player p")
public class Player extends Person {

    private String position;
    private Long salary;

    public Player(String firstName, String lastName, Long contract, Integer age, Team team,
                  String position, Long salary) {
        super(firstName, lastName, contract, age, team);
        this.position = position;
        this.salary = salary;
    }

    public Player() {
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;

    }
}

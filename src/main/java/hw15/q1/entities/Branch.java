package hw15.q1.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Branch implements BaseEntity<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Clerk> clerks;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<Account> accounts;

    public Branch(Integer id, Set<Clerk> clerks, Set<Account> accounts) {
        this.clerks = clerks;
        this.accounts = accounts;
        this.id = id;
    }

    public Branch() {
    }

    public Set<Clerk> getClerks() {
        return clerks;
    }

    public void setClerks(Set<Clerk> clerks) {
        this.clerks = clerks;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public Integer getNumber() {
        return id;
    }

    @Override
    public void setNumber(Integer number) {
        this.id = number;
    }
}

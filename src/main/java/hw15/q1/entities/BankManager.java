package hw15.q1.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankManager implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @OneToOne
    private Branch branch;

    @OneToMany
    private Set<Clerk> clerks;

    public BankManager(Integer number, Branch branch) {
        this.number = number;
        this.branch = branch;
        this.clerks = new HashSet<>();
    }


    public BankManager() {
        this.clerks = new HashSet<>();
    }


    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public void setNumber(Integer number) {
        this.number = number;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Set<Clerk> getClerks() {
        return clerks;
    }

    public boolean addClerks(Clerk clerk) {
        return clerks.add(clerk);
    }
}

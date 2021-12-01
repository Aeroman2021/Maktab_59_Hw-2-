package hw15.q1.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankManager implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "bankManager",cascade = CascadeType.ALL)
    private Branch branch;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Set<Clerk> clerks;

    public BankManager(Branch branch) {
        this.branch = branch;
        this.clerks = new HashSet<>();
    }


    public BankManager() {
        this.clerks = new HashSet<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

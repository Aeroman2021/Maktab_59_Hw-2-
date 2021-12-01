package hw15.q1.entities;


import javax.persistence.*;

@Entity
public class Clerk implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Branch branch;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private BankManager bankManager;

    public Clerk(Branch branch, BankManager bankManager) {
        this.branch = branch;
//        this.bankManager = bankManager;
    }

    public Clerk() {
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

//    public BankManager getBankManager() {
//        return bankManager;
//    }
//
//    public void setBankManager(BankManager bankManager) {
//        this.bankManager = bankManager;
//    }
}

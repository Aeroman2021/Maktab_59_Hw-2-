package hw15.q1.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "branch.findAll", query = "SELECT b FROM Branch b ")
public class Branch implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Set<Clerk> clerks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private BankManager bankManager;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Set<Account> accounts;


    public Branch() {
        accounts = new HashSet<>();
        clerks = new HashSet<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Clerk> getClerks() {
        return clerks;
    }

    public void addClerks(Clerk clerk) {
        clerks.add(clerk);
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }

    public BankManager getBankManager() {
        return bankManager;
    }

    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", bankManager=" + bankManager +
                '}';
    }
}

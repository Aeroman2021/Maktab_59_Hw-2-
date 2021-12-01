package hw15.q1.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
@NamedQuery(name = "account.findAll", query = "SELECT a from Account a")
public class Account implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Double balance;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private CreditCard creditCard;

    public Account(Integer number, Double balance, Customer customer) {
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    public Account(Integer number, Double balance, Branch branch) {
        this.number = number;
        this.balance = balance;
        this.branch = branch;
    }

    public Account(Integer number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public Account() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Account {" +
                "id=" + id +
                ", accNumber=" + number +
                ", balance=" + balance +
                ", branch=" + branch +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getNumber().equals(account.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getBranch());
    }
}

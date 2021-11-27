package hw15.q1.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounts")
@NamedQuery(name = "account.findAll",query = "SELECT a from Account a")
public class Account implements BaseEntity<Integer> {


    @Id
    @SequenceGenerator(name = "SeqGen", sequenceName = "mySeq", initialValue = 1000_000, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGen")
    private Integer number;

    private double balance;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;

    public Account(Integer number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public Account() {
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public void setNumber(Integer number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.getBalance(), getBalance()) == 0 && getNumber().equals(account.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getBalance());
    }


}

package hw15.q1.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "credit_cards")
@NamedQuery(name = "credit_cards.findAll", query = " SELECT c FROM CreditCard c")
public class CreditCard implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long number;
    private Integer password;
    private Integer CVV2;

    @Temporal(TemporalType.DATE)
    private Calendar expiredDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

//    @OneToMany(targetEntity = Transaction.class)
//    @JoinColumn(name = "card_id")
//    private List<Transaction> transactions;

    public CreditCard(Long number,Integer password, Integer CVV2, Calendar expiredDate, Account account) {
        this.password = password;
        this.CVV2 = CVV2;
        this.expiredDate = expiredDate;
        this.account = account;
        this.number=number;
//        this.transactions = new ArrayList<>();
    }

    public CreditCard() {
//        this.transactions = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getCVV2() {
        return CVV2;
    }

    public void setCVV2(Integer CVV2) {
        this.CVV2 = CVV2;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(Transaction transaction) {
//        transactions.add(transaction);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard that)) return false;
        return getNumber().equals(that.getNumber()) && getCVV2().equals(that.getCVV2()) && expiredDate.equals(that.expiredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getCVV2(), expiredDate);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number=" + number +
                ", password=" + password +
                ", CVV2=" + CVV2 +
                ", expiredDate=" + expiredDate +
                ", account=" + account +
                '}';
    }
}

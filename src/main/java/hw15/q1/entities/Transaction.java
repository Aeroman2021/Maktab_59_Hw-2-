package hw15.q1.entities;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;


@Table(name = "transaction")
@Entity
@NamedQuery(name = "transaction.finsAll", query = "SELECT t FROM Transaction t")
public class Transaction implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private double amount;
    private Integer srcAccNumber;
    private Integer destAccNumber;

    @CreationTimestamp
    private Date transactionDate;

    @ManyToOne
    private CreditCard creditCard;

    public Transaction(String type, double amount,
                       Integer srcAccNumber, Integer destAccNumber, Date transactionDate,CreditCard card) {
        this.type = type;
        this.amount = amount;
        this.srcAccNumber = srcAccNumber;
        this.destAccNumber = destAccNumber;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getSrcAccNumber() {
        return srcAccNumber;
    }

    public void setSrcAccNumber(Integer srcAccNumber) {
        this.srcAccNumber = srcAccNumber;
    }

    public Integer getDestAccNumber() {
        return destAccNumber;
    }

    public void setDestAccNumber(Integer destAccNumber) {
        this.destAccNumber = destAccNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}

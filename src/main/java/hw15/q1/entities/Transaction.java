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
    private Integer number;

    private TransactionType type;
    private double amount;
    private Long srcAccNumber;
    private Long destAccNumber;

    @CreationTimestamp
    private Date transactionDate;


    public Transaction(Integer number, TransactionType type, double amount,
                       Long srcAccNumber, Long destAccNumber, Date transactionDate) {
        this.number = number;
        this.type = type;
        this.amount = amount;
        this.srcAccNumber = srcAccNumber;
        this.destAccNumber = destAccNumber;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
    }


    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public void setNumber(Integer number) {
        this.number = number;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getSrcAccNumber() {
        return srcAccNumber;
    }

    public void setSrcAccNumber(Long srcAccNumber) {
        this.srcAccNumber = srcAccNumber;
    }

    public Long getDestAccNumber() {
        return destAccNumber;
    }

    public void setDestAccNumber(Long destAccNumber) {
        this.destAccNumber = destAccNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}

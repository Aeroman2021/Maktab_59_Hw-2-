package hw15.q1.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
@NamedQuery(name = "customer.findAll", query = "SELECT c FROM Customer c ")
public class Customer implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastName;
    private String sex;
    private Integer age;
    private String username;
    private String password;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Account> accounts;

    public Customer(Integer id, String firstname, String lastName, String sex, Integer age) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

    public Customer() {
        this.accounts = new HashSet<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getNumber() {
        return id;
    }

    @Override
    public void setNumber(Integer number) {
        this.id = number;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(Account account) {
        accounts.add(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return firstname.equals(customer.firstname) && lastName.equals(customer.lastName) && age.equals(customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastName, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}

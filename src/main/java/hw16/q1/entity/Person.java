package hw16.q1.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private Long contract;
    private Integer age;

    @ManyToOne
    private Team team;

    public Person(String firstName, String lastName, Long contract, Integer age, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contract = contract;
        this.age = age;
        this.team = team;
    }

    public Person() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContract() {
        return contract;
    }

    public void setContract(Long contract) {
        this.contract = contract;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getFirstName().equals(person.getFirstName()) &&
                getLastName().equals(person.getLastName()) &&
                getTeam().equals(person.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getTeam());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contract=" + contract +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}

package hw16.q1.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "stadium.loadAll",query = "SELECT s FROM Stadium s")
public class Stadium implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

    private Long population;

    public Stadium(String name, City city, Long population) {
        this.name = name;
        this.city = city;
        this.population = population;
    }

    public Stadium() {
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stadium)) return false;
        Stadium stadium = (Stadium) o;
        return getName().equals(stadium.getName()) && getCity().equals(stadium.getCity()) && getPopulation().equals(stadium.getPopulation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCity(), getPopulation());
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", population=" + population +
                '}';
    }
}

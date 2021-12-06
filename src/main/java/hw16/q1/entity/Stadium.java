package hw16.q1.entity;


import javax.persistence.*;

@Entity
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
}

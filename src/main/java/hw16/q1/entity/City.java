package hw16.q1.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "city.loadAll",query = "SELECT c FROM City c")
public class City implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private Set<Team> teams;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    private Set<Stadium> stadiums;


    public City(String name) {
        this.name = name;
    }

    public City() {

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


    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Stadium> getStadiums() {
        return stadiums;
    }

    public void setStadiums(Set<Stadium> stadiums) {
        this.stadiums = stadiums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getName().equals(city.getName()) && getTeams().equals(city.getTeams()) && getStadiums().equals(city.getStadiums());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTeams(), getStadiums());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teams=" + teams +
                ", stadiums=" + stadiums +
                '}';
    }

}

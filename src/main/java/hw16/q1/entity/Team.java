package hw16.q1.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "team.loadAll",query = "SELECT t FROM Team t")
public class Team implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private City city;

    @OneToOne
    private Stadium stadium;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private Set<MatchSchedule> homeMatches;

    @OneToMany(mappedBy = "awayTeam",cascade = CascadeType.ALL)
    private Set<MatchSchedule> awayMatches;


    public Team(String name, City city, Stadium stadium) {
        this.name = name;
        this.city = city;
        this.stadium = stadium;
        homeMatches = new HashSet<>();
        awayMatches = new HashSet<>();
    }

    public Team() {
        homeMatches = new HashSet<>();
        awayMatches = new HashSet<>();
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Set<MatchSchedule> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(MatchSchedule homeMatch) {
        this.homeMatches.add(homeMatch);
    }

    public Set<MatchSchedule> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(MatchSchedule awayMatch) {
        this.awayMatches.add(awayMatch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getName().equals(team.getName()) && getCity().equals(team.getCity()) && getStadium().equals(team.getStadium());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCity(), getStadium());
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", stadium=" + stadium +
                '}';
    }
}

package hw16.q1.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@NamedQuery(name = "matchSchedule.loadAll",query = "SELECT ms FROM MatchSchedule ms")
public class MatchSchedule implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private TeamPerformance homeTeamPerformance;

    @OneToOne(cascade = CascadeType.ALL)
    private TeamPerformance awayTeamPerformance;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team awayTeam;

    @OneToOne(cascade = CascadeType.ALL)
    private Stadium stadium;

    private Date date;

    public MatchSchedule(TeamPerformance homeTeamPerformance, TeamPerformance awayTeamPerformance,
                         Team homeTeam, Team awayTeam, Stadium stadium, Date date) {
        this.homeTeamPerformance = homeTeamPerformance;
        this.awayTeamPerformance = awayTeamPerformance;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadium = stadium;
        this.date = date;
    }

    public MatchSchedule() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }
    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TeamPerformance getHomeTeamPerformance() {
        return homeTeamPerformance;
    }

    public void setHomeTeamPerformance(TeamPerformance homeTeamPerformance) {
        this.homeTeamPerformance = homeTeamPerformance;
    }

    public TeamPerformance getAwayTeamPerformance() {
        return awayTeamPerformance;
    }

    public void setAwayTeamPerformance(TeamPerformance awayTeamPerformance) {
        this.awayTeamPerformance = awayTeamPerformance;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchSchedule)) return false;
        MatchSchedule matchSchedule = (MatchSchedule) o;
        return getHomeTeam().equals(matchSchedule.getHomeTeam()) &&
                getAwayTeam().equals(matchSchedule.getAwayTeam()) &&
                getStadium().equals(matchSchedule.getStadium()) &&
                getDate().equals(matchSchedule.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam(), getStadium(), getDate());
    }

    @Override
    public String toString() {
        return "MatchSchedule{" +
                "id=" + id +
                ", homeTeamPerformance=" + homeTeamPerformance +
                ", awayTeamPerformance=" + awayTeamPerformance +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", stadium=" + stadium +
                ", date=" + date +
                '}';
    }
}

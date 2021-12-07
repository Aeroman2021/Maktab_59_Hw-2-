package hw16.q1.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teams_performance")
@NamedQuery(name = "teamPerformance.loadAll",query = " SELECT tp from TeamPerformance tp")
public class TeamPerformance implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Team team;

    @Column(name = "goals_scored")
    private Integer goalScored;

    @Column(name = "goals_received")
    private Integer goalsReceived;

    private Integer point;

    public TeamPerformance(Team team, Integer goalScored, Integer goalsReceived, Integer point) {
        this.team = team;
        this.goalScored = goalScored;
        this.goalsReceived = goalsReceived;
        this.point = point;
    }

    public TeamPerformance() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(Integer goalScored) {
        this.goalScored = goalScored;
    }

    public Integer getGoalsrecived() {
        return goalsReceived;
    }

    public void setGoalsrecived(Integer goalsrecived) {
        this.goalsReceived = goalsrecived;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamPerformance)) return false;
        TeamPerformance that = (TeamPerformance) o;
        return getTeam().equals(that.getTeam()) && getGoalScored().equals(that.getGoalScored()) && goalsReceived.equals(that.goalsReceived) && getPoint().equals(that.getPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeam(), getGoalScored(), goalsReceived, getPoint());
    }

    @Override
    public String toString() {
        return "TeamPerformance{" +
                "id=" + id +
                ", team=" + team +
                ", goalScored=" + goalScored +
                ", goalsReceived=" + goalsReceived +
                ", point=" + point +
                '}';
    }
}

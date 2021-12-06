package hw16.q1.entity;

import javax.persistence.*;

@Entity
@Table(name = "player_performance")
public class PlayerPerformance implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Player player;

    @OneToOne
    private MatchSchedule matchSchedule;

    private Integer goals_scored;

    public PlayerPerformance(Player player, MatchSchedule matchSchedule, Integer goals_scored) {
        this.player = player;
        this.matchSchedule = matchSchedule;
        this.goals_scored = goals_scored;
    }

    public PlayerPerformance() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MatchSchedule getMatch() {
        return matchSchedule;
    }

    public void setMatch(MatchSchedule matchSchedule) {
        this.matchSchedule = matchSchedule;
    }

    public Integer getGoals_scored() {
        return goals_scored;
    }

    public void setGoals_scored(Integer goals_scored) {
        this.goals_scored = goals_scored;
    }
}

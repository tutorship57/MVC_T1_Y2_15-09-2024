package Model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private List<Team> teams;
    private int rounds;
    private int pins;

    public GameModel() {
        teams = new ArrayList<>();
        teams.add(new Team("Team White", "white"));
        teams.add(new Team("Team Black", "black"));
        teams.add(new Team("Team Brown", "brown"));
        rounds = 10;
        pins = 10;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public int getRounds() {
        return rounds;
    }

    public int getPins() {
        return pins;
    }

    public void reset() {
        teams.forEach(team -> team.getCows().forEach(cow -> {
            cow.resetScore();
            cow.getThrows().clear();
        }));
    }
}
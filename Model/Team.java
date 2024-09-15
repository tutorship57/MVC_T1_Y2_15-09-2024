package Model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String color;
    private List<Cow> cows;

    public Team(String name, String color) {
        this.name = name;
        this.color = color;
        cows = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            cows.add(new Cow(color + "_" + i, color));
        }
    }

    public List<Cow> getCows() {
        return cows;
    }

    public String getName() {
        return name;
    }

    public int totalScore() {
        return cows.stream().mapToInt(Cow::getScore).sum();
    }
}
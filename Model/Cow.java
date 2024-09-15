package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cow {
    private String cowId;
    private String color;
    private int score;
    private List<Integer> throwsList;

    public Cow(String cowID,String color){
        this.cowId = cowID;
        this.color = color ;
        this.score = 0 ;
        this.throwsList = new ArrayList<>();
    }

    public int throwBall(int pinLeft){
        Random rand = new Random();    
            return rand.nextInt(pinLeft+1);
    }
    public void addThrow(int pins){
        throwsList.add(pins);
        addScore(pins);
    }
    public List<Integer> getThrows(){
        return throwsList;
    }
    public void addScore(int points){
        this.score += points;
    }
    public int getScore() {
        return score;
    }

    public String getCowId() {
        return cowId;
    }

    public String getColor() {
        return color;
    }
    public void resetScore(){
        this.score =0 ;
    }

}

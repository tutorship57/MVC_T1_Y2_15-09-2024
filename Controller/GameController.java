package Controller;
import Model.GameModel;
import Model.Team;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import Model.Cow;
import View.View;
public class GameController {
    private GameModel model;
    private View view ; 

    public GameController(GameModel model,View view){
        this.model = model;
        this.view = view ;
        this.view.playButtonListener(new playButtonListener());
    }
    class playButtonListener implements ActionListener{     
        public void actionPerformed(ActionEvent e) {
            playGame();
        }
    }
    public void playGame(){
        ArrayList<String> outputRound= new ArrayList<>();
        for (int i = 0; i < model.getRounds(); i++) {
            outputRound.add("round "+(i+1)+": ");
            for(Team team :model.getTeams()){
                for(Cow cow : team.getCows()){
                    int pinsLeft = model.getPins();
                    for (int throwNum = 1; throwNum <= 2; throwNum++) {
                        int knockedPins = cow.throwBall(pinsLeft);
                        Random rand = new Random();
                        String CowColor = cow.getColor();
                        int cowtellPins;
                        
                        // โอกาสวัวโกหก
                        if(CowColor.equals("black")){
                            cowtellPins =  rand.nextInt(101)<20 ? pinsLeft:knockedPins;
                        }else{
                            cowtellPins = rand.nextInt(101)<10 ? 0:knockedPins;
                        }
                        
                        boolean cowLied ;
                        // ดูว่าวัวเหลี่ยมไหม(โกหก) 
                        if ("black".equals(cow.getColor()) && cowtellPins != knockedPins) {
                            knockedPins = 0;  // Black cow lied about all pins, reset to 0
                            cowLied = true ;
                        } else if ("white".equals(cow.getColor()) && knockedPins != 0 && cowtellPins == 0) {
                            knockedPins = pinsLeft;  // White cow lied about gutter, give full points
                            cowLied = true ;
                        }else{
                            knockedPins = cowtellPins;
                            cowLied =false ;
                        }
                        outputRound.add(this.view.displayCowAction(cow,knockedPins,cowLied));
                        
                        cow.addThrow(knockedPins);
                        pinsLeft -= knockedPins;
                        if (pinsLeft == 0) break;  // Cow Strike ends the round for this cow = = 
                    }
                }
            }
        this.view.showPopup(view, outputRound); 
        outputRound.clear();
        }
        for (Team cowTeam: model.getTeams()) {
            this.view.RecordScoreEachTeam(cowTeam, outputRound);
            this.view.showPopup(view, outputRound);
            outputRound.clear();
        }
        this.view.RecordFinalScore(this.model.getTeams(), outputRound);
        this.view.showPopup(view, outputRound);
        outputRound.clear();
        this.model.reset();
    }
}

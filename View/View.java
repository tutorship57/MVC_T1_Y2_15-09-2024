package View;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Cow;
import Model.Team;
public class View extends JFrame{
    private JButton cowplayButton = new JButton("playGame");
    
    public View(){
        JPanel Panel = new JPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setTitle("Cow Strike");
        Panel.add(cowplayButton);
        this.setLayout(new BorderLayout());
        this.add(cowplayButton,BorderLayout.CENTER);
    }
    public void playButtonListener(ActionListener sendButton){
        cowplayButton.addActionListener(sendButton);
    }
    public String displayCowAction(Cow cow, int pins, boolean lied) {
        //พวก วัวเหลี่ยม เชิงหนา
        if (lied) {
            if ("black".equals(cow.getColor())) {
                return cow.getCowId() + " telling lied" + " knocked down " + pins + " pins." ;
            } else if ("white".equals(cow.getColor())) {
                return cow.getCowId() + " telling lied" +" knocked down " + pins + " pins.";
            }
        }
        return cow.getCowId() + " knocked down " + pins + " pins.";
    }

    public void RecordScoreEachTeam(Team team,ArrayList<String> Cow) {
        Cow.add("Scores for " + team.getName() + ":");
        for (Cow cow : team.getCows()) {
            Cow.add("  " + cow.getCowId() + ": " + cow.getScore() + " points");
        }
    }

    public void RecordFinalScore(List<Team> teams,ArrayList<String> Ranking) {
        teams.sort((a, b) -> Integer.compare(b.totalScore(), a.totalScore()));
        Ranking.add("Final Rankings:");
        for (Team team : teams) {
            Ranking.add(team.getName() + ": " + team.totalScore() + " points");
        }
        Ranking.add("The winning team is " + teams.get(0).getName() + "!");
    }
    public void showPopup(JFrame parentFrame, ArrayList<String> result) {
        // Create the popup dialog
        JDialog dialog = new JDialog(parentFrame, "Action", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        for (int i = 0; i < result.size(); i++) {
            JLabel label = new JLabel(result.get(i));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
        }
        JButton button = new JButton("Close");
        button.addActionListener(e -> dialog.dispose());
        // button with the dispose on close to control
        dialog.setLayout(new BorderLayout());
        dialog.add(panel,BorderLayout.CENTER);
        dialog.add(button,BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}


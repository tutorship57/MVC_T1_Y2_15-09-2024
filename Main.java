import Controller.GameController;
import Model.GameModel;
import View.View;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        View view = new View();
        GameController controller = new GameController(model, view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}

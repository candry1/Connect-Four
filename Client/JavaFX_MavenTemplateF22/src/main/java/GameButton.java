import javafx.scene.control.Button;

public class GameButton extends Button{
    String color;
    boolean enabled;
    int row;
    int col;
    int player;


    GameButton(String color, int r, int c){
        this.color = color;
        this.row = r;
        this.col = c;
        this.enabled = false;
    }
}

import java.io.Serializable;
import java.util.ArrayList;

public class CFourInfo implements Serializable{
    String message;
    String color;
    boolean p1Turn;
    boolean p2Turn;
    boolean has2Players;
    boolean hasWinner;      //if false, we haven't reached one or there's a tie
    int winner;             //1 for p1, 2 for p2
    int row;
    int col;
    int id;
    int playerCount;

    CFourInfo(){
        this.message = "";
        this.p1Turn = true;
        this.p2Turn = false;
        this.has2Players = false;
        this.hasWinner = false;
        this.winner = 0;
        this.row = -1;
        this.col = -1;
        this.playerCount = 0;
    }

}
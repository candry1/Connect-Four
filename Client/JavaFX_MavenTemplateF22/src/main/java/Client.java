import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.ArrayList;
import javafx.application.Platform;

public class Client extends Thread {
    Socket socketClient;
    ObjectOutputStream out;
    ObjectInputStream in;
    private Consumer<Serializable> callback;
    ArrayList<GameButton> grid;
    int portNum;
    String IPAddress;

    Client(){
        grid = new ArrayList<GameButton>();
    }

    Client(Consumer<Serializable> call, int portNumber, String IP){
        callback = call;
        portNum = portNumber;
        IPAddress = IP;
        grid = new ArrayList<GameButton>();
        this.start();
    }

    public void run(){
        try {
            socketClient= new Socket(IPAddress,portNum);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        } catch(Exception e) {}

        while(true) {
            try {
                CFourInfo gameInfo = (CFourInfo) in.readObject();
                if(gameInfo.row != -1 && gameInfo.col != -1) {
                    GameButton b = grid.get((gameInfo.row * 7) + gameInfo.col);
                    System.out.println(gameInfo.row + " " + gameInfo.col + " --> " + ((gameInfo.row * 7) + gameInfo.col));
                    b.enabled = false;
                    b.setDisable(true);
                    b.setStyle("-fx-background-color: " + gameInfo.color + ";");

                    for (int i = 0; i < grid.size(); i++) {
                        if (grid.get(i) == b && i - 7 >= 0) {
                            grid.get(i - 7).enabled = true;
                            grid.get(i - 7).setDisable(false);
                        }
                    }
                }
                callback.accept(gameInfo);
            }
            catch(Exception e) {}
        }
    }

    public void send(CFourInfo gameInfo){
        try{
            out.reset();
            out.writeObject(gameInfo);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public boolean checkForWinner(CFourInfo info){
        int rowToCheck = info.row;
        int colToCheck = info.col;
        int count = 0;

        //horizontal check
        for(int i = 0; i < 7; i++){
            if(grid.get((rowToCheck * 7) + i).player == info.id){
                count++;
            } else{
                count = 0;
            }

            if(count == 4){
                return true;
            }
        }

        //vertical check
        count = 0;
        for(int i = 0; i < 6; i++){
            if(grid.get((i * 7) + colToCheck).player == info.id){
                count++;
            } else{
                count = 0;
            }

            if(count == 4){
                return true;
            }
        }

        //diagnal starting at top right(row 0-2)

        return false;
    }

}

















//    public void winner(){
//       ArrayList<Integer> directions = new ArrayList<Integer>();
//       directions.add(-7);
//       directions.add(+1);
//       directions.add(+7);
//       directions.add(-1);
//
//       winnerHelper(directions, 0, 0, 0 + directions.get(0), grid.get(0).color, 0);
//    }
//
//    public void winnerHelper(ArrayList<Integer> dir, int moveAmountIndex, int startPos, int comparePos, String color, int count){
//        if(comparePos >= 0 && comparePos < grid.size()){
//            if(color != "white" && color == grid.get(comparePos).color) {
//                if (count == 4) {
//                    return;
//                }
//                winnerHelper(dir, moveAmountIndex, startPos, comparePos + dir.get(moveAmountIndex), color, count + 1);
//            }
//        }
//
//        while (moveAmountIndex < dir.size() - 1) {
//            winnerHelper(dir, moveAmountIndex + 1, startPos, startPos + dir.get(moveAmountIndex + 1), color, count);
//        }
//
//        if(startPos + 1 < grid.size()) {
//            winnerHelper(dir, 0, startPos + 1, startPos + 1 + dir.get(0), grid.get(startPos + 1).color, 0);
//        } else {
//            return;
//        }
//
//        return;
//    }
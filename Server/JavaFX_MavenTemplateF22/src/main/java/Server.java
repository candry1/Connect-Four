import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server {
    int count = 1;
    theServer s;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    CFourInfo gameInfoS;
    private Consumer<Serializable> callback;		//the server's output stream!
    int portNum;

    Server(Consumer<Serializable> call, int portNumber){
        gameInfoS = new CFourInfo();
        portNum = portNumber;

        callback = call;
        s = new theServer();
        s.start();
    }

    public class theServer extends Thread {
        public void run() {
            try(ServerSocket mysocket = new ServerSocket(portNum);){
                callback.accept("Waiting for players...");
                while(true) {
                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept("Player"+count+ " just connected to the server!");  //sends info the server
                    gameInfoS.message = "Player"+count+ " just connected to the server!";
                    c.updateClients(gameInfoS);    //causing server socket not to launch
                    if(count == 1){
                        callback.accept("Waiting for one more to connect!");
                        gameInfoS.message = "Waiting for one more to connect!";
                        c.updateClients(gameInfoS);
                    }
                    clients.add(c);
                    c.start();

                    count++;
                }
            } catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }
    }

    //-------------------------------------------------------------------------------------------

    class ClientThread extends Thread {
        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        public void updateClients(CFourInfo gameinfo1){     //writes info to the clients
            for(int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);
                try {
                    t.out.reset();
                    t.out.writeObject(gameinfo1);
                }
                catch(Exception e) {}
            }
        }
        public void run(){
            try{
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            } catch(Exception e) {
                System.out.println("Streams not open");
            }

            CFourInfo gameInfoC = new CFourInfo();
            gameInfoC.message = "Player"+count+ " just joined!";
            updateClients(gameInfoC);           //sending to client

            if(count == 1){
                gameInfoC.message = "Waiting for one more to connect!";
                updateClients(gameInfoC);
            }

            while(true) {
                try {
                    gameInfoC = (CFourInfo) in.readObject();  //not grabbing saved info
                    if(gameInfoC.message == "") {
                        gameInfoC.message = "blank?";
                    }
                    callback.accept(gameInfoC.message);
                    updateClients(gameInfoC);
                }
                catch(Exception e) {
                    callback.accept("Player" + count + " left the game");
                    gameInfoC.message = "Player" + count + " left the game";
                    updateClients(gameInfoC);
                    clients.remove(this);
                    break;
                }
            }
        }
    }
}

package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int i=0;
    static int hod=0;
    final static int PORT = 3333;
    ServerSocket server;
    Socket[] clientSocket = new Socket[20];

    Server(){

        try {
            server = new ServerSocket(PORT);
        } catch(Exception e) {
            System.out.println("Main.Server could not be put");
        }

        //Всегда стараться принять новых игроков
        while (true){
            attemptToMatchPlayers();
        }
    }

    //Matching players policy
    private void attemptToMatchPlayers() {
        //Where there are 2 players waiting - create a game (match)
        try{
            clientSocket[i]=server.accept();
            i++;
            if (i % 2 == 0){
                new Match(clientSocket[i-2], clientSocket[i-1]);
            }
        } catch (IOException e){
            System.out.println("Could not be connected");
        }
    }
}

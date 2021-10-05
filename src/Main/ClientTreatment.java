package Main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTreatment extends Thread{
    private Match match;
    private int type; //0 для белых 1 для чёрных
    private String outString;
    private PrintWriter out;
    private Scanner in;

    private InputSender inputSender;

    /*
    At creating each thread of client interaction,
    player's pieces' color is transmitted.
    Readers and senders are created as well
     */

    /* При создании каждого потока взаимодействия с клиентом,
        Клиенту сообщается вызвавший его сервер. Также передаётся тип
        (0 - играет белыми, 1 - играет чёрными)
        И создаются считыватели и сообщатели
     */
    public ClientTreatment(Match match, Socket clientSocket, int type) {

        System.out.println("Client "+type);
        this.type = type;
        this.match = match;
        try {
            in= new Scanner(clientSocket.getInputStream());
            out= new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Input/Output stream error");
        }
    }

    /*
    Waiting for message from client.
    Check if it's his turn and if it is, forwarding information
     */

    /* В потоке вечное ожидание того, что скажет клиент,
       Проверка, его ли ход, и, если его, отправка информации о ходе серверу
     */
    @Override
    public void run() {
        System.out.println("Thread running");
        while(true){
            if (in.hasNext()){

                String nextLine = in.nextLine();
                if (type==(match.getHod() % 2) && nextLine.length()==4){
                    System.out.println("Move is being processed");
                    inputSender = new InputSender(match, nextLine, type);
                    new Thread(inputSender).start();
                } else {
                    System.out.println("Move is blocked, сurent line - "+nextLine);
                }

                if (nextLine.startsWith("Type")){
                    System.out.println("Client Treatment: type "+ nextLine);
                    match.setReplacement(Character.getNumericValue(nextLine.charAt(nextLine.length()-1)));
                    match.waitingForPlayersResponse=false;
                }

            }
        }
    }


    public void sendMessage(String out){
        this.out.println(out);
        this.out.flush();
    }

    public int getType(){
        return this.type;
    }
}

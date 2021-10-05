package Main;

import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

//Class with interaction between 2 clients

class Match {
    private int hod;
    private ClientTreatment client1, client2;
    private Board brd;
    boolean waitingForPlayersResponse = false;
    private Socket socket1, socket2;
    Thread thredOfClient2;
    Thread thredOfClient1;
    CountDownLatch permissionToAvoidWaiting = new CountDownLatch(0);

    public Match(Socket socket1, Socket socket2) {
        this.socket1=socket1;
        this.socket2=socket2;

        //Старт потоков для каждого учатника матча
        client1 = new ClientTreatment(this, socket1, 0);
        thredOfClient1=new Thread(client1);
        thredOfClient1.start();
        client1.sendMessage("Hi! Your client number is 0");

        client2 = new ClientTreatment(this, socket2, 1);
        thredOfClient2 = new Thread(client2);
        thredOfClient2.start();
        client2.sendMessage("Hi! Your client number is 1");

        thredOfClient2.setPriority(Thread.MAX_PRIORITY);
        thredOfClient1.setPriority(Thread.MAX_PRIORITY);
        //Сбитие счётчика ходов
        this.hod=0;

        //Новая доска
        brd = new Board(this);

        sendMessageToBothPlayers(brd.getBoard());
    }

    public int getHod(){
        return hod;
    }

    /*
    If server evaluates turn as good in MovesLogic,
    information about turn is sent to both clients.
    Otherwise bad turn message is sent to moving player
     */

    //Если сервер признаёт ход игрока хорошим в классе MovesLogic,
    //то обоим клиентам отсылается сообщение о хорошем ходу и координаты
    //иначе сообщение о плохои ходу отсылается отправителю
    //Также при удачном ходу возвращается информация о поле. Она начинается со слова Field

    public void send(String moveCord, int clientType) throws InterruptedException {

        if (MovesLogic.moveIsPosible(moveCord, brd, clientType, this)){
            System.out.println("Good move with cords "+moveCord);

            //While server awaits client's information about piece replacement - do nothing

            if (waitingForPlayersResponse){
                permissionToAvoidWaiting=new CountDownLatch((int)permissionToAvoidWaiting.getCount()+1);
            }

            permissionToAvoidWaiting.await();

            while(waitingForPlayersResponse){
                int i=0;
                System.out.print(i++);
            }

            sendMessageToMovingPlayer("Good Move "+moveCord);
            sendMessageToWaitingPlayer("Good Move made by your opponent "+moveCord);
            String curBoard = brd.getBoard();
            sendMessageToBothPlayers(curBoard);

            //Check verification
            boolean check=MovesLogic.check(brd, 1-(hod % 2));

            hod++;

            if (check){
                sendMessageToMovingPlayer("Vam postavlen shah!");
                if (MovesLogic.mat(brd, hod % 2, this)){
                    sendMessageToMovingPlayer("Vam postavlen mat!");
                    sendMessageToWaitingPlayer("Vi pobedili!");
                    System.out.println("MAT");
                }
            } else {
                if (MovesLogic.mat(brd, hod % 2, this)){
                    sendMessageToBothPlayers("Bil postavlen pat! Nicia!");
                    System.out.println("PAT");
                }
            }



        } else {
            System.out.println("Was set up next move on "+System.currentTimeMillis());
            sendMessageToMovingPlayer("Bad Move "+moveCord);
            System.out.println("Bad move with cord "+moveCord);
        }
    }

    //Next moves send messages to client
    public void sendMessageToMovingPlayer(String message){
        if (hod % 2 == 0){
            client1.sendMessage(message);
        } else {
            client2.sendMessage(message);
        }
    }

    public void sendMessageToWaitingPlayer(String message){
        if (hod % 2 == 1){
            client1.sendMessage(message);
        } else {
            client2.sendMessage(message);
        }
    }

    public void sendMessageToBothPlayers(String message){
        client1.sendMessage(message);
        client2.sendMessage(message);
    }


    //message, sent to client when their pawn reached end of the desk
    //Client will respond with piece information
    public void pawnHasReachedEndOfTheDesc() {
        sendMessageToMovingPlayer("Your pawn has reached the end! Choose figure to replace it!");
        setWaitingPosition(true);
    }

    //Передача данных о типе выбранной фигуры доске
    //Ход при этом по факту на 1 больше, т.к. MovesLogic.moveIsPosible(moveCord, brd, clientType, this) вернул true после того,
    //как запрос сообщение о достижении конца доски был отослан.
    //Ответ же от сервера пришёл после того, как прграмма продвинулась дальше
    public void setReplacement(int numericValue) {
        brd.setReplacement(numericValue, this.getHod());
        System.out.println("For replacement were sent "+numericValue+this.getHod());
        setWaitingPosition(false);
        permissionToAvoidWaiting.countDown();
    }

    public void setWaitingPosition(boolean b) {
        this.waitingForPlayersResponse=b;
        System.out.println("Match is waiting = "+b);
    }
}

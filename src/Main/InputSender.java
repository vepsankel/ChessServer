package Main;

public class InputSender extends Thread {

    private Match match;
    private String nextLine;
    private int type;

    /*
    "Wrapper" thread for server-side calculations.
    Was created in order to avoid deadlocking application in case, where
    player's pawn reached end of the board but player has not selected replacement
     */

    /*
    Класс, загоняющий дальнейшие вычисления в отдельный поток.
    Сделав так, мы избавимся от проблемы,
    при которой ждущий выбора фигуры оппонентом при пешке достигнувшей края доски сможет совершать ходы
     */
    public InputSender(Match match, String nextLine, int type) {
        this.match=match;
        this.nextLine=nextLine;
        this.type=type;
    }

    @Override
    public void run() {
        try {
            System.out.println("Match cords were send on time "+System.currentTimeMillis());
            this.match.send(nextLine, type);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

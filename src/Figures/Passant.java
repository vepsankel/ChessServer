package Figures;

public class Passant extends Pair {
    private int hod = 0;

    public Passant(int x, int y, int hod) {
        super(x, y);
        this.hod=hod;
    }

    public int getHod() {
        return hod;
    }
}

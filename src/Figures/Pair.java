package Figures;

public class Pair {
    int x;
    int y;

    public Pair(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCords(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object o){
        boolean result;
        Pair pair = (Pair)o;

        if (pair.getX()==this.x && pair.getY()==this.y){
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}

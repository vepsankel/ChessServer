package Figures;
import Main.*;

import java.util.ArrayList;

public abstract class Figure {
    public int type;
    public int hod=0;
    Pair cord;
    Board brd;
    public enum color{BLACK, WHITE}
    public color a;

    Figure(int x, int y, Board brd, color a){
        cord = new Pair(x,y);
        this.a = a;
        this.brd = brd;
    }

    Figure(Pair pair, Board brd, color a, int hod){
        cord = pair;
        this.a = a;
        this.brd = brd;
        this.hod = hod;
    }

    public int getColor(){
        if (a==color.WHITE){
            return 0;
        } else {
            return 1;
        }
    }

    public void setCord(int x, int y){
        cord = new Pair(x,y);
    }

    public boolean isMovePisible(Pair pair){
        boolean result = false;

        if (possibleMovesList()!=null){
            for (Pair E : possibleMovesList()){
                if (E.equals(pair)){
                    result=true;
                }
            }
        }

        return result;
    };

    abstract ArrayList<Pair> possibleMovesList();

    public Pair getCord(){
        return cord;
    }

    public boolean adequateCords(int a){
        if (a>=1 && a<=8){
            return true;
        } else {
            return false;
        }
    }

    public boolean adequateCords(int a, int b){
        if (a>=1 && a<=8 && b>=1 && b<=8){
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Pair> getPossibleMoves(){
        return this.possibleMovesList();
    }

    public abstract void makeMove(Pair chosenPlaceCords);

    public int getHod() {
        return hod;
    }
}

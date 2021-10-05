package Figures;
import Main.Board;

import java.util.ArrayList;

public class Pawn extends Figure{
    int type=6;

    public Pawn(int x, int y, Board brd, color color) {
        super(x, y, brd, color);
        super.type=this.type;
    }

    public Pawn(Pair cord, Board board, color a, int hod) {
        super(cord, board, a, hod);
        super.type=this.type;
    }

    @Override
    ArrayList<Pair> possibleMovesList() {
        int x = this.getCord().getX();
        int y = this.getCord().getY();
        int k;
        ArrayList<Pair> possibleMoves = new ArrayList<>();


        //k shows, in what direction pawn is going
        if (this.getColor()==0){
            k=1;
        } else {
            k=-1;
        }

        //If forward cell is free - add it as possible destination
        //If the second cell is free and pawn has not done any moves - add it as pos.dest.
        //true until coordinates remain in board
        if (adequateCords(x, y+k) && Board.getFigure(brd, new Pair(x, y+k))==null){
            possibleMoves.add(new Pair(x, y+k));

            if (adequateCords(x, y+2*k) && Board.getFigure(brd, new Pair(x, y+2*k))==null && this.hod==0){
                possibleMoves.add(new Pair(x, y+2*k));
            }
        }

        //adversary frwd-right
        if (adequateCords(x+1, y+k) && Board.getFigure(brd, new Pair(x+1, y+k))!=null && Board.getFigure(brd, new Pair(x+1, y+k)).getColor()!=this.getColor()){
            possibleMoves.add(new Pair(x+1, y+k));
        }

        //adversary frwd-left
        if (adequateCords(x-1, y+k) && Board.getFigure(brd, new Pair(x-1, y+k))!=null && Board.getFigure(brd, new Pair(x-1, y+k)).getColor()!=this.getColor()){
            possibleMoves.add(new Pair(x-1, y+k));
        }

        //enPassant
        if (adequateCords(x+1, y+k) && brd.getEnPassant().equals(new Pair(x+1, y+k)) && brd.getEnPassant().getHod()==brd.getHod()-1){
            possibleMoves.add(new Pair(x+1, y+k));
        }

        if (adequateCords(x-1, y+k) && brd.getEnPassant().equals(new Pair(x-1, y+k)) && brd.getEnPassant().getHod()==brd.getHod()-1){
            possibleMoves.add(new Pair(x-1, y+k));
        }

        return possibleMoves;
    }

    @Override
    public void makeMove(Pair chosenPlaceCords){
        if (Board.getFigure(this.brd, chosenPlaceCords.getX(), chosenPlaceCords.getY())!=null){
            brd.getFiguresList().remove(Board.getFigure(this.brd, chosenPlaceCords.getX(), chosenPlaceCords.getY()));
        } else {
            if (chosenPlaceCords.getX()!=this.getCord().getX() && chosenPlaceCords.equals(brd.getEnPassant()) && brd.getEnPassant().getHod()==brd.getHod()-1){
                brd.getFiguresList().remove(Board.getFigure(this.brd, chosenPlaceCords.getX(), chosenPlaceCords.getY()+(2*this.getColor()-1)));
            }
        }

        if (Math.abs(chosenPlaceCords.getY()-this.getCord().getY())==2){
            brd.setEnPassant(chosenPlaceCords, brd.getHod());
        }

        this.setCord(chosenPlaceCords.getX(), chosenPlaceCords.getY());

        if (this.getCord().getY()==this.getColor()*(-7)+8 && !this.brd.getTest()){
            this.brd.pawnHasReachedEndOfTheDesc();
            System.out.println("END OF THE DESC");
        }

        this.hod++;
    }

}
package Figures;

import Main.Board;

import java.util.ArrayList;

public class Knight extends Figure{
    int type = 5;

    public Knight(int x, int y, Board brd, color color) {
        super(x, y, brd, color);
        super.type=this.type;
    }

    public Knight(Pair cord, Board board, color a, int hod) {
        super(cord, board, a, hod);
        super.type=this.type;
    }

    @Override
    ArrayList<Pair> possibleMovesList() {
        int x = this.getCord().getX();
        int y = this.getCord().getY();
        int kx, ky;
        ArrayList<Pair> possibleMoves = new ArrayList<>();

        for (kx=-2; kx<=2; kx++){
            for (ky=-2; ky<=2; ky++){
                if (!(kx==0 || ky==0)){
                    if (!(Math.abs(kx)==Math.abs(ky))){
                        if (adequateCords(x+kx, y+ky)){
                            if (Board.getFigure(brd, new Pair(x+kx, y+ky))==null || Board.getFigure(brd, new Pair(x+kx, y+ky)).getColor()!=this.getColor()){
                                possibleMoves.add(new Pair(x+kx, y+ky));
                            }
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }

    @Override
    public void makeMove(Pair chosenPlaceCords) {
        if (Board.getFigure(this.brd, chosenPlaceCords.getX(), chosenPlaceCords.getY())!=null){
            brd.getFiguresList().remove(Board.getFigure(this.brd, chosenPlaceCords.getX(), chosenPlaceCords.getY()));
        }

        this.setCord(chosenPlaceCords.getX(), chosenPlaceCords.getY());
        this.hod++;
    }
}

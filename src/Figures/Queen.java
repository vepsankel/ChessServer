package Figures;

import Main.Board;

import java.util.ArrayList;

public class Queen extends Figure{
    int type = 2;

    public Queen(int x, int y, Board brd, color color) {
        super(x, y, brd, color);
        super.type=this.type;
    }

    public Queen(Pair cord, Board board, color a, int hod) {
        super(cord, board, a, hod);
        super.type=this.type;
    }

    @Override
    ArrayList<Pair> possibleMovesList() {
        int x = this.getCord().getX();
        int y = this.getCord().getY();
        int kx, ky;
        ArrayList<Pair> possibleMoves = new ArrayList<>();

        for (kx=-1; kx<=1; kx+=2){
            for (ky=-1; ky<=1; ky+=2){
                for (int d=1; d<=7; d++){
                    int xdx=x+kx*d;
                    int ydy=y+ky*d;

                    if (adequateCords(xdx, ydy) && Board.getFigure(brd, new Pair(xdx, ydy))==null){
                        possibleMoves.add(new Pair(xdx, ydy));
                    } else if (adequateCords(xdx, ydy) && Board.getFigure(brd, new Pair(xdx, ydy))!=null && Board.getFigure(brd, new Pair(xdx, ydy)).getColor()!=this.getColor()){
                        possibleMoves.add(new Pair(xdx, ydy));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        for (kx=1; kx<=7; kx++){
            if (adequateCords(x+kx, y)){
                if (Board.getFigure(brd, x+kx, y)==null){
                    possibleMoves.add(new Pair(x+kx,y));
                } else if (Board.getFigure(brd, x+kx, y).getColor()!=this.getColor()){
                    possibleMoves.add(new Pair(x+kx,y));
                    break;
                } else {
                    break;
                }
            }
        }

        for (kx=-1; kx>=-7; kx--){
            if (adequateCords(x+kx, y)){
                if (Board.getFigure(brd, x+kx, y)==null){
                    possibleMoves.add(new Pair(x+kx,y));
                } else if (Board.getFigure(brd, x+kx, y).getColor()!=this.getColor()){
                    possibleMoves.add(new Pair(x+kx,y));
                    break;
                } else {
                    break;
                }
            }
        }

        for (ky=1; ky<=7; ky++){
            if (adequateCords(x, y+ky)){
                if (Board.getFigure(brd, x, y+ky)==null){
                    possibleMoves.add(new Pair(x,y+ky));
                } else if (Board.getFigure(brd, x, y+ky).getColor()!=this.getColor()){
                    possibleMoves.add(new Pair(x,y+ky));
                    break;
                } else {
                    break;
                }
            }
        }

        for (ky=-1; ky>=-7; ky--){
            if (adequateCords(x, y+ky)){
                if (Board.getFigure(brd, x, y+ky)==null){
                    possibleMoves.add(new Pair(x,y+ky));
                } else if (Board.getFigure(brd, x, y+ky).getColor()!=this.getColor()){
                    possibleMoves.add(new Pair(x,y+ky));
                    break;
                } else {
                    break;
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

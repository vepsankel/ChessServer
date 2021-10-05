package Figures;

import Main.Board;

import java.util.ArrayList;

public class King extends Figure {
    int type=1;

    public King(int x, int y, Board brd, color a) {
        super(x, y, brd, a);
        super.type=this.type;
    }

    public King(Pair cord, Board board, color a, int hod) {
        super(cord, board, a, hod);
        super.type=this.type;
    }

    @Override
    ArrayList<Pair> possibleMovesList() {
        int x = this.getCord().getX();
        int y = this.getCord().getY();
        int kx, ky;
        ArrayList<Pair> possibleMoves = new ArrayList<>();

        for (kx=-1; kx<=1; kx++){
            for (ky=-1; ky<=1; ky++){
                if (!(ky==0 && kx==0)){
                    if (adequateCords(y+ky, x+kx)){
                        if (Board.getFigure(brd, new Pair(x+kx, y+ky))==null ||
                                (Board.getFigure(brd, new Pair(x+kx, y+ky))!=null && Board.getFigure(brd, new Pair(x+kx, y+ky)).getColor()!=this.getColor())){
                                possibleMoves.add(new Pair(x+kx, y+ky));
                        }
                    }
                }
            }
        }

        //Короткая рокировка
        if (this.hod==0){
            if (Board.getFigure(brd, new Pair(8,7*getColor()+1))!=null && Board.getFigure(brd, new Pair(8,7*getColor()+1)).hod==0 && Board.getFigure(brd, new Pair(6,7*getColor()+1))==null && Board.getFigure(brd, new Pair(7,7*getColor()+1))==null){

                ArrayList<Pair> enemyPossibleMoveCordForRoqueCheck = new ArrayList<>(brd.getEnemyAttackPosibilitiesForRoqueCheck(this.getColor()));
                boolean Roque = true;

                if (enemyPossibleMoveCordForRoqueCheck!=null){
                    for (Pair E:enemyPossibleMoveCordForRoqueCheck){
                        if (E.equals(new Pair(5,7*getColor()+1)) || E.equals(new Pair(6,7*getColor()+1)) || E.equals(new Pair(7,7*getColor()+1))){
                            Roque=false;
                        }
                    }
                }

                if (Roque==true){
                    possibleMoves.add(new Pair(7,7*getColor()+1));
                }
            }
        }

        //Длинная рокировка
        if (this.hod==0){
            if (Board.getFigure(brd, new Pair(1,7*getColor()+1))!=null && Board.getFigure(brd, new Pair(1,7*getColor()+1)).hod==0 && Board.getFigure(brd, new Pair(2,7*getColor()+1))==null && Board.getFigure(brd, new Pair(3,7*getColor()+1))==null && Board.getFigure(brd, new Pair(4,7*getColor()+1))==null){

                ArrayList<Pair> enemyPossibleMoveCordForRoqueCheck = new ArrayList<>(brd.getEnemyAttackPosibilitiesForRoqueCheck(this.getColor()));
                boolean Roque = true;

                if (enemyPossibleMoveCordForRoqueCheck!=null){
                    for (Pair E:enemyPossibleMoveCordForRoqueCheck){
                        if (E.equals(new Pair(3,7*getColor()+1)) || E.equals(new Pair(4,7*getColor()+1)) || E.equals(new Pair(5,7*getColor()+1))){
                            Roque=false;
                        }
                    }
                }

                if (Roque==true){
                    possibleMoves.add(new Pair(3,7*getColor()+1));
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

        //Короткая рокировка
        if (chosenPlaceCords.getX()==7 && this.hod==0){
            Board.getFigure(brd, new Pair(8, this.getColor()*7+1)).setCord(6, this.getColor()*7+1);
        }

        if (chosenPlaceCords.getX()==3 && this.hod==0){
            Board.getFigure(brd, new Pair(1, this.getColor()*7+1)).setCord(4, this.getColor()*7+1);
        }

        this.setCord(chosenPlaceCords.getX(), chosenPlaceCords.getY());
        this.hod++;
    }
}

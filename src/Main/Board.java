package Main;

import Figures.*;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private ArrayList<Figure> figuresList = new ArrayList<>();
    private Passant enPassant = new Passant(0,0,0);
    private Match match;
    private boolean testClient=false;

    Board(Match match){
        putFigures();
        this.match=match;
    }

    Board(ArrayList<Figure> figuresList, Match match) {
        this.match = match;
        this.testClient=true;

        for (Figure E:figuresList){
            switch (E.type){
                case 1: this.figuresList.add(new King(E.getCord(), this, E.a, E.getHod())); break;
                case 2: this.figuresList.add(new Queen(E.getCord(), this, E.a, E.getHod())); break;
                case 3: this.figuresList.add(new Rook(E.getCord(), this, E.a, E.getHod())); break;
                case 4: this.figuresList.add(new Bishop(E.getCord(), this, E.a, E.getHod())); break;
                case 5: this.figuresList.add(new Knight(E.getCord(), this, E.a, E.getHod())); break;
                case 6: this.figuresList.add(new Pawn(E.getCord(), this, E.a, E.getHod())); break;
            }
        }
    }

    public ArrayList<Pair> getEnemyAttackPosibilitiesForRoqueCheck(int type) {
        ArrayList<Pair> enemyAttackPosibilities = new ArrayList<>();
        int currentPlayerColor = type;

        for (Figure E:figuresList ){
            if (E.getColor()!=currentPlayerColor){
                if (E.getPossibleMoves()!=null && !(E.type==1 && E.hod==0)){
                    enemyAttackPosibilities.addAll(E.getPossibleMoves());
                }
            }
        }

        return enemyAttackPosibilities;
    }

    public ArrayList<Pair> getEnemyAttackPosibilities(int currentPlayerColor){
        ArrayList<Pair> enemyAttackPosibilities = new ArrayList<>();

        for (Figure E:figuresList ){
            if (E.getColor()!=currentPlayerColor){
                if (E.getPossibleMoves()!=null){
                    enemyAttackPosibilities.addAll(E.getPossibleMoves());
                }
            }
        }

        return enemyAttackPosibilities;
    }

    //pieces initialization
    private void putFigures(){



        //ПStalemate check 3
        /*{figuresList.add(new Pawn(1,3,this, Figure.color.WHITE));
        figuresList.add(new Pawn(1,4,this, Figure.color.BLACK));
        figuresList.add(new King(6,7,this, Figure.color.WHITE));
        figuresList.add(new King(new Pair(8,7),this, Figure.color.BLACK, 1));
        figuresList.add(new Bishop(3,3,this, Figure.color.WHITE));}*/

        //Stalemate check 2
        /*
        {figuresList.add(new Queen(3,1,this, Figure.color.WHITE));
        figuresList.add(new King(6,6,this, Figure.color.WHITE));
        figuresList.add(new King(new Pair(4, 8), this, Figure.color.BLACK, 1));}*/

        //Stalemate check 1
        /*{figuresList.add(new Pawn(1,4,this, Figure.color.WHITE));
        figuresList.add(new Pawn(2,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(3,3,this, Figure.color.WHITE));
        figuresList.add(new Pawn(4,5,this, Figure.color.WHITE));
        figuresList.add(new Pawn(5,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(6,3,this, Figure.color.WHITE));
        figuresList.add(new Pawn(7,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(8,3,this, Figure.color.WHITE));
        figuresList.add(new Pawn(1,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(2,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(3,5,this, Figure.color.BLACK));
        figuresList.add(new Pawn(4,6,this, Figure.color.BLACK));
        figuresList.add(new Pawn(5,4,this, Figure.color.BLACK));
        figuresList.add(new Pawn(6,4,this, Figure.color.BLACK));
        figuresList.add(new Pawn(7,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(8,7,this, Figure.color.BLACK));

        figuresList.add(new Rook(7,3,this, Figure.color.WHITE));
        figuresList.add(new Knight(4,2,this, Figure.color.WHITE));
        figuresList.add(new Bishop(3,1,this, Figure.color.WHITE));
        figuresList.add(new Queen(8,2,this, Figure.color.WHITE));
        figuresList.add(new King(5,1,this, Figure.color.WHITE));
        figuresList.add(new Bishop(6,1,this, Figure.color.WHITE));
        figuresList.add(new Knight(7,1,this, Figure.color.WHITE));
        figuresList.add(new Rook(8,1,this, Figure.color.WHITE));

        figuresList.add(new Rook(1,8,this, Figure.color.BLACK));
        figuresList.add(new Knight(2,8,this, Figure.color.BLACK));
        figuresList.add(new Bishop(2,3,this, Figure.color.BLACK));
        figuresList.add(new Queen(1,5,this, Figure.color.BLACK));
        figuresList.add(new King(5,8,this, Figure.color.BLACK));
        figuresList.add(new Bishop(8,4,this, Figure.color.BLACK));
        figuresList.add(new Knight(7,8,this, Figure.color.BLACK));
        figuresList.add(new Rook(8,8,this, Figure.color.BLACK));}*/

        //Standard position
        {figuresList.add(new Pawn(1,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(2,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(3,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(4,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(5,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(6,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(7,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(8,2,this, Figure.color.WHITE));
        figuresList.add(new Pawn(1,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(2,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(3,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(4,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(5,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(6,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(7,7,this, Figure.color.BLACK));
        figuresList.add(new Pawn(8,7,this, Figure.color.BLACK));

        figuresList.add(new Rook(1,1,this, Figure.color.WHITE));
        figuresList.add(new Knight(2,1,this, Figure.color.WHITE));
        figuresList.add(new Bishop(3,1,this, Figure.color.WHITE));
        figuresList.add(new Queen(4,1,this, Figure.color.WHITE));
        figuresList.add(new King(5,1,this, Figure.color.WHITE));
        figuresList.add(new Bishop(6,1,this, Figure.color.WHITE));
        figuresList.add(new Knight(7,1,this, Figure.color.WHITE));
        figuresList.add(new Rook(8,1,this, Figure.color.WHITE));

        figuresList.add(new Rook(1,8,this, Figure.color.BLACK));
        figuresList.add(new Knight(2,8,this, Figure.color.BLACK));
        figuresList.add(new Bishop(3,8,this, Figure.color.BLACK));
        figuresList.add(new Queen(4,8,this, Figure.color.BLACK));
        figuresList.add(new King(5,8,this, Figure.color.BLACK));
        figuresList.add(new Bishop(6,8,this, Figure.color.BLACK));
        figuresList.add(new Knight(7,8,this, Figure.color.BLACK));
        figuresList.add(new Rook(8,8,this, Figure.color.BLACK));}

    }

    //String representation of Board
    public String getBoard() {
        int numOfElements=0;
        StringBuilder result = new StringBuilder();

        for (Figure E : figuresList) {
            result.append(String.valueOf(E.type)).append(E.getColor()).append(E.getCord().getX()).append(E.getCord().getY());
            numOfElements++;
        }

        result.insert(0, "Field" + numOfElements + ":");

        System.out.println(result);
        return result.toString();
    }

    //returns Piece with given coordinates
    public static Figure getFigure(Board brd, Pair pairIn){
        Figure figure = null;

        for (Figure E : brd.getFiguresList()){
            if (E.getCord().equals(pairIn)){
                figure=E;
            }
        }
        return figure;
    }

    public static Figure getFigure(Board brd, int x, int y){
        return getFigure(brd, new Pair(x,y));
    }


    public ArrayList<Figure> getFiguresList() {
        return figuresList;
    }

    //establishes enPassant cell
    public void setEnPassant(Pair chosenCords, int hod) {
        int x, y;

        if (chosenCords.getY()==4){
            y=3;
        } else {
            y=6;
        }

        x=chosenCords.getX();

        System.out.println("Was created passant cell on "+x+y+" on move #"+hod);
        this.enPassant = new Passant(x,y,hod);
    }

    //returns cell on which is possible enPassant
    public Passant getEnPassant() {
        return enPassant;
    }

    //returns last turn number
    public int getHod(){
        return match.getHod();
    }

    public Match getMatch() {
        return match;
    }

    public void pawnHasReachedEndOfTheDesc(){
        match.pawnHasReachedEndOfTheDesc();
    }

    public boolean getTest(){
        return this.testClient;
    }

    public void setReplacement(int typeOfReplacement, int hod) {
        hod = hod % 2 == 0 ? 0 : 1;
        System.out.println("hod = "+hod);
        int x=0,y=0;
        Figure.color playingColor;
        int index=0;
        if (hod==0){
            playingColor= Figure.color.WHITE;
        } else {
            playingColor= Figure.color.BLACK;
        }

        for (Figure E:this.figuresList){
            if (E.type==6 && E.getCord().getY()==8-7*hod && E.getColor()==hod){
                System.out.println("Found a figure to be replaced with index "+figuresList.indexOf(E));
                index = figuresList.indexOf(E);

                x=E.getCord().getX();
                y=E.getCord().getY();
            }
        }

        figuresList.remove(index);

        switch (typeOfReplacement){
            case 2: figuresList.add(new Queen(x,y,this, playingColor)); break;
            case 3: figuresList.add(new Rook(x,y,this, playingColor)); break;
            case 4: figuresList.add(new Bishop(x,y,this, playingColor)); break;
            case 5: figuresList.add(new Knight(x,y,this, playingColor)); break;
        }
    }

}

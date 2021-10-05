package Main;

import Figures.Figure;
import Figures.Pair;

import java.util.ArrayList;

//static methods for turn evaluation
final class MovesLogic {

    public static boolean moveIsPosible(String acceptedCords, Board brd, int clientType, Match match){

        boolean moveIsGood=false;
        Board testBoard = new Board(match);
        Pair chosenFigureCords = new Pair(0,0);
        Pair chosenPlaceCords = new Pair(0,0);
        setChosenFigureAndPlaceCords(acceptedCords, chosenFigureCords, chosenPlaceCords);
        Figure chosenFigure = getFigure(brd, chosenFigureCords);

        //evaluation, if move is possible
        /*
        Is piece color good?
        If yes - create test board with this piece turn
        If there's no check - move is good
         */
        if (goodColor(chosenFigure, clientType)){
            if (chosenFigure.isMovePisible(chosenPlaceCords)){
                moveIsGood=true;
                testBoard = new Board(brd.getFiguresList(), match);
                testBoard.setEnPassant(brd.getEnPassant(), brd.getEnPassant().getHod());
                getFigure(testBoard, chosenFigureCords).makeMove(chosenPlaceCords);
                //makeTestMove(brd, match, testBoard, chosenFigureCords, chosenPlaceCords);
                if (!check(testBoard, clientType)){
                    moveIsGood=true;
                } else {
                    moveIsGood=false;
                }
            } else {
                System.out.println("This figure cannot move that way!");
            }
        } else {
            System.out.println("Wrong color chosen!");
        }

        //if move is possible - move piece, update coordinates
        if (moveIsGood){
            chosenFigure.makeMove(chosenPlaceCords);
        }

        return moveIsGood;
    }

    public static boolean check(Board brd, int clientType) {
        boolean result = false;
        Figure King=null;

        for (Figure E:brd.getFiguresList()){
            if (E.type==1 && E.getColor()==clientType){
                King = E;
            }
        }

        ArrayList<Pair> enemyAttackPossibilities = new ArrayList<>(brd.getEnemyAttackPosibilities(clientType));
        for (Pair E:enemyAttackPossibilities){
            if (King!=null && King.getCord().equals(E)){
                System.out.println("Koroli pod shahom!");
                result=true;
            }
        }

        return result;
    }

    private static boolean goodColor(Figure chosenFigure, int clientType) {
        boolean result=false;

        if (chosenFigure!=null){
            if (chosenFigure.getColor()==clientType){
                result = true;
            }
        } else result = false;

        System.out.println("Void goodColor, result="+result);
        return result;
    }

    private static void setChosenFigureAndPlaceCords(String acceptedCords, Pair chosenFigureCords, Pair chosenPlaceCords) {
        chosenFigureCords.setCords(Character.getNumericValue(acceptedCords.charAt(0)), Character.getNumericValue(acceptedCords.charAt(1)));
        chosenPlaceCords.setCords(Character.getNumericValue(acceptedCords.charAt(2)), Character.getNumericValue(acceptedCords.charAt(3)));
    }

    private static Figure getFigure(Board brd, Pair pairIn){
        Figure figure = null;

        for (Figure E : brd.getFiguresList()){
            if (E.getCord().equals(pairIn)){
                figure=E;
            }
        }
        return figure;
    }

    public static boolean mat(Board brd, int type, Match match) {
        boolean result = true;

        outerloop:{
        for (Figure E:brd.getFiguresList()){
            for (Pair J:E.getPossibleMoves()){

                Board testBrd = new Board(brd.getFiguresList(), match);
                testBrd.setEnPassant(brd.getEnPassant(), brd.getEnPassant().getHod());

                Figure chosenFigure = Board.getFigure(testBrd, E.getCord());
                if (goodColor(chosenFigure, type)){
                    chosenFigure.makeMove(J);
                    if (!check(testBrd, type)){
                        System.out.println("Shah was avoided, thanks to figure on "+E.getCord().getX()+E.getCord().getY()+" and move "+J.getX()+J.getY());
                        result=false;
                        break outerloop;
                    }
                }

            }
        }}

        return result;
    }
}



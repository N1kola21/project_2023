package com.example.rocnikovka_druhak_version2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import static com.example.rocnikovka_druhak_version2.constants.*;

public class GameEngine extends Application {
    Tile[][] board = new Tile[width][height];
    private Group pieceGroup = new Group();
    private Group tileGroup = new Group();
    private int[][] gameLayout = constantsLayout;

    private void placeTiles(int[][] gameLayout) {

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Queen queen = null;
                King king = null;
                Rook rook = null;
                Bishop bishop = null;
                Knight knight = null;
                Pawn pawn = null;
                Tile tile = new Tile(x, y);

                //kralove setup
                if (gameLayout[y][x] == 9) king = pohybKrale(x, y, "white");
                if (gameLayout[y][x] == 2) king = pohybKrale(x, y, "black");

                //kralovny setup
                if (gameLayout[y][x] == 8) queen = pohybKralovny(x, y, "white");
                if (gameLayout[y][x] == 1) queen = pohybKralovny(x, y, "black");

                //věže setup
                if (gameLayout[y][x] == 12) rook = pohybVeze(x, y, "white");
                if (gameLayout[y][x] == 5) rook = pohybVeze(x, y, "black");

                //střelci setup
                if (gameLayout[y][x] == 10) bishop = pohybStrelce(x, y, "white");
                if (gameLayout[y][x] == 3) bishop = pohybStrelce(x, y, "black");

                //knight setup
                if (gameLayout[y][x] == 11) knight = pohybJezdce(x, y, "white");
                if (gameLayout[y][x] == 4) knight = pohybJezdce(x, y, "black");

                //setup pěsaků
                if (gameLayout[y][x] == 7) pawn = pohybPesaku(x, y, "white");
                if (gameLayout[y][x] == 6) pawn = pohybPesaku(x, y, "black");

                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                //umístí jednotlivý pieces na šachovnici podle předešlých setupů
                if (king != null) {
                    tile.setPiece(king);
                    pieceGroup.getChildren().add(king);
                }
                if (queen != null) {
                    tile.setPiece(queen);
                    pieceGroup.getChildren().add(queen);
                }
                if (rook != null) {
                    tile.setPiece(rook);
                    pieceGroup.getChildren().add(rook);
                }
                if (bishop != null) {
                    tile.setPiece(bishop);
                    pieceGroup.getChildren().add(bishop);
                }
                if (knight != null) {
                    tile.setPiece(knight);
                    pieceGroup.getChildren().add(knight);
                }
                if (pawn != null) {
                    tile.setPiece(pawn);
                    pieceGroup.getChildren().add(pawn);
                }
            }
        }

    }

    private boolean OB(int x, int y) {
        return x < 0 || x > height - 1 || y < 0 || y > width - 1;
    }

    private boolean occupiedByMyPiece(int startX, int startY, int endX, int endY){
        if (board[endX][endY].hasPiece()) {
            return board[startX][startY].getPiece().getColor().equals(board[endX][endY].getPiece().getColor());
        }
        return false;
    }

    private boolean commonRules(int startX, int startY, int endX, int endY){
        if (OB(endX, endY)) {
            System.out.println("OB");
            return false;
        }
        if (occupiedByMyPiece(startX, startY, endX, endY)) {
            System.out.println("nemůže brát vlastní pieces");
            return false;
        }
        return true;
    }

    private void captures(int startX, int startY, int endX, int endY) {
        if (board[endX][endY].hasPiece()){
            if (!board[startX][startY].getPiece().getColor().equals(board[endX][endY].getPiece().getColor())){
                System.out.println("capture");
                Piece killedPiece = board[endX][endY].getPiece();
                board[endX][endY].setPiece(null);
                pieceGroup.getChildren().remove(killedPiece);
            }
        }
    }

    private boolean lineIsEmpty(int startX, int startY, int endX, int endY){
        int xDir = Integer.compare(endX, startX);
        int yDir = Integer.compare(endY, startY);
        int tempX = startX + xDir;
        int tempY = startY + yDir;
        while (tempX != endX || tempY != endY) {
            if (board[tempX][tempY].hasPiece()) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            tempX += xDir;
            tempY += yDir;
        }
        return true;
    }

    private boolean kingLegalMove(int startX, int startY, int endX, int endY){;

        if (Math.abs(startX - endX) > 1 || Math.abs(startY - endY) > 1) {
            System.out.println("Moc daleko");
            return false;
        }
        return commonRules(startX, startY, endX, endY);
    }

    private boolean queenLegalMove(int startX, int startY, int endX, int endY){

        //spojení pravidel pro věž a střelce (diagonílní, horizontální nebo vertikální pohyb
        if (startX != endX && startY != endY && Math.abs(startX - endX) != Math.abs(startY - endY)) {
            System.out.println("pohyb nesplňuje podmínku směru ");
            return false;
        }

        //vrací true pouze když jsou splňeny funkce lineIsEmpty() a commonRules()
        return lineIsEmpty(startX, startY, endX, endY) && commonRules(startX, startY, endX, endY);
    }

    private boolean rookLegalMove(int startX, int startY, int endX, int endY){

        //kontroluje, že je pohyb vertikální nebo horizontální
        if (startX != endX && startY != endY){
            System.out.println("nesplňuje směr");
            return false;
        }

        //vrací true pouze když jsou splňeny funkce lineIsEmpty() a commonRules()
        return lineIsEmpty(startX, startY, endX, endY) && commonRules(startX, startY, endX, endY);
    }

    private boolean bishopLegalMove (int startX, int startY, int endX, int endY){
        if (Math.abs(startX - endX) != Math.abs(startY - endY)){
            System.out.println("Pohyb není diagonální");
            return false;
        }
        //vrací true pouze když jsou splňeny funkce lineIsEmpty() a commonRules()
        return lineIsEmpty(startX, startY, endX, endY) && commonRules(startX, startY, endX, endY);
    }

    private boolean knightLegalMove(int startX, int startY, int endX, int endY) {
        if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2  || Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1){
            return commonRules(startX, startY, endX, endY);
        }
        System.out.println("invalid knight move");
        return false;
    }

    private boolean pawnLegalMove(int startX, int startY, int endX, int endY){
        String pawnColor = board[startX][startY].getPiece().color;

        if (startX == endX && board[endX][endY].hasPiece()){
            System.out.println("pawn can't move foward if there is a piece in front");
            return false;
        }

        if (startX != endX && Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1 && !board[endX][endY].hasPiece()){
            System.out.println("pawn can't move to a different file without a capture");
            return false;
        }

        if (pawnColor.equals("white")){
            if (startY <= endY) {
                System.out.println("pawns can't move back or to the sides");
                return false;
            }
            if (startY != 6 && Math.abs(startY - endY) > 1){
                System.out.println("moc daleko, not starting pos");
                return false;
            }
            if (startY == 6 && Math.abs(startY - endY) > 2){
                System.out.println("moc daleko, starting pos");
                return false;
            }
        }
        if (pawnColor.equals("black")){
            if (startY >= endY) {
                System.out.println("pawns can't move back or to the sides");
                return false;
            }
            if (startY != 1 && Math.abs(startY - endY) > 1){
                System.out.println("moc daleko, not starting pos");
                return false;
            }
            if (startY == 1 && Math.abs(startY - endY) > 2){
                System.out.println("moc daleko, starting pos");
                return false;
            }
        }



        return commonRules(startX, startY, endX, endY);
    }


    private int toBoard(double pixel) {
        return (int) (pixel + tileSize / 2) / tileSize;
    }

    private King pohybKrale(int x, int y, String player) {
        King king = new King(x, y, player);

        king.setOnMouseReleased(e -> {
            int endX = toBoard(king.getLayoutX());
            int endY = toBoard(king.getLayoutY());

            int startX = toBoard(king.getOldX());
            int startY = toBoard(king.getOldY());

            boolean legalMove = kingLegalMove(startX, startY, endX, endY);
            System.out.println("Pohyb " + player +" king ze souřadnic: " + startX + " " + startY + " na souřadnice: " + endX + " " + endY);
            System.out.println("Pohyb " + legalMove);
            if (legalMove) {
                captures(startX, startY, endX, endY);
                king.move(endX, endY);

                board[startX][startY].setPiece(null);
                board[endX][endY].setPiece(king);
            } else {
                king.abortMove();
            }
        });
        return king;
    }

    private Queen pohybKralovny (int x, int y, String player){
        Queen queen = new Queen(x, y, player);

        queen.setOnMouseReleased(e -> {
            int endX = toBoard(queen.getLayoutX());
            int endY = toBoard(queen.getLayoutY());

            int startX = toBoard(queen.getOldX());
            int startY = toBoard(queen.getOldY());

            boolean legalMove = queenLegalMove(startX, startY, endX, endY);
            System.out.println("Pohyb " + player +" queen ze souřadnic: " + startX + " " + startY + " na souřadnice: " + endX + " " + endY);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                captures(startX, startY, endX, endY);

                queen.move(endX, endY);

                board[startX][startY].setPiece(null);
                board[endX][endY].setPiece(queen);
            } else {
                queen.abortMove();
            }
        });
        return queen;
    }

    private Rook pohybVeze (int x, int y, String player) {
        Rook rook = new Rook(x, y, player);

        rook.setOnMouseReleased(e -> {
            int endX = toBoard(rook.getLayoutX());
            int endY = toBoard(rook.getLayoutY());

            int startX = toBoard(rook.getOldX());
            int startY = toBoard(rook.getOldY());

            boolean legalMove = rookLegalMove(startX, startY, endX, endY);
            System.out.println("Pohyb " + player +" rook ze souřadnic: " + startX + " " + startY + " na souřadnice: " + endX + " " + endY);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                captures(startX, startY, endX, endY);

                rook.move(endX, endY);

                board[startX][startY].setPiece(null);
                board[endX][endY].setPiece(rook);
            } else {
                rook.abortMove();
            }
        });
        return rook;
    }

    private Bishop pohybStrelce (int x, int y, String player){
        Bishop bishop = new Bishop(x, y, player);

        bishop.setOnMouseReleased(e -> {
            int endX = toBoard(bishop.getLayoutX());
            int endY = toBoard(bishop.getLayoutY());

            int startX = toBoard(bishop.getOldX());
            int startY = toBoard(bishop.getOldY());

            boolean legalMove = bishopLegalMove(startX, startY, endX, endY);
            System.out.println("Pohyb " + player +" bishop ze souřadnic: " + startX + " " + startY + " na souřadnice: " + endX + " " + endY);
            System.out.println("Pohyb " + legalMove);


            if (legalMove) {
                captures(startX, startY, endX, endY);

                bishop.move(endX, endY);

                board[startX][startY].setPiece(null);
                board[endX][endY].setPiece(bishop);
            } else {
                bishop.abortMove();
            }
        });
        return bishop;
    }

    private Knight pohybJezdce(int x, int y, String player) {
        Knight knight = new Knight(x, y, player);

        knight.setOnMouseReleased(e -> {
            int endX = toBoard(knight.getLayoutX());
            int endY = toBoard(knight.getLayoutY());

            int startX = toBoard(knight.getOldX());
            int startY = toBoard(knight.getOldY());

            boolean legalMove = knightLegalMove(startX, startY, endX, endY);
            System.out.println("Pohyb " + player +" knight ze souřadnic: " + startX + " " + startY + " na souřadnice: " + endX + " " + endY);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                captures(startX, startY, endX, endY);

                knight.move(endX, endY);

                board[startX][startY].setPiece(null);
                board[endX][endY].setPiece(knight);
            } else {
                knight.abortMove();
            }
        });
        return knight;
    }
    private Pawn pohybPesaku (int x, int y, String player) {
        Pawn pawn = new Pawn(x, y, player);

        pawn.setOnMouseReleased(e -> {
            int endX = toBoard(pawn.getLayoutX());
            int endY = toBoard(pawn.getLayoutY());

            int startX = toBoard(pawn.getOldX());
            int startY = toBoard(pawn.getOldY());

            boolean legalMove = pawnLegalMove(startX, startY, endX, endY);
            System.out.println("Pohyb " + player +" pawn ze souřadnic: " + startX + " " + startY + " na souřadnice: " + endX + " " + endY);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                captures(startX, startY, endX, endY);

                pawn.move(endX, endY);

                board[startX][startY].setPiece(null);
                board[endX][endY].setPiece(pawn);
            } else {
                pawn.abortMove();
            }
        });
        return pawn;
    }


     private Parent setupGame() {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize);
        root.getChildren().addAll(tileGroup, pieceGroup);
        placeTiles(gameLayout);
        return root;
     }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(setupGame());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
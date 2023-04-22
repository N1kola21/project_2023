package com.example.rocnikovka_druhak_version2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import static com.example.rocnikovka_druhak_version2.constants.*;

public class HelloApplication extends Application {
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
                if (gameLayout[y][x] == 9) {
                    king = pohybKrale(x, y, "white");
                }
                if (gameLayout[y][x] == 2) {
                    king = pohybKrale(x, y, "black");
                }

                //kralovny setup
                if (gameLayout[y][x] == 8){
                    queen = pohybKralovny(x, y, "white");
                }
                if (gameLayout[y][x] == 1){
                    queen = pohybKralovny(x, y, "black");
                }

                //věže setup
                if (gameLayout[y][x] == 12){
                    rook = pohybVeze(x, y, "white");
                }
                if (gameLayout[y][x] == 5){
                    rook = pohybVeze(x, y, "black");
                }

                //střelci setup
                if (gameLayout[y][x] == 10){
                    bishop = pohybStrelce(x, y, "white");
                }
                if (gameLayout[y][x] == 3){
                    bishop = pohybStrelce(x, y, "black");
                }

                //knight setup
                if (gameLayout[y][x] == 11){
                    knight = pohybKnight(x, y, "white");
                }
                if (gameLayout[y][x] == 4){
                    knight = pohybKnight(x, y, "black");
                }

                //setup pěsaků
                if (gameLayout[y][x] == 7){
                    pawn = pohybPesaku(x, y, "white");
                }
                if (gameLayout[y][x] == 6){
                    pawn = pohybPesaku(x, y, "black");
                }

                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                //umístí jednotlivý pieces na šachovnici podle předešlých setupů
                if (king != null) {
                    tile.setKing(king);
                    pieceGroup.getChildren().add(king);
                }
                if (queen != null) {
                    tile.setQueen(queen);
                    pieceGroup.getChildren().add(queen);
                }
                if (rook != null) {
                    tile.setRook(rook);
                    pieceGroup.getChildren().add(rook);
                }
                if (bishop != null) {
                    tile.setBishop(bishop);
                    pieceGroup.getChildren().add(bishop);
                }
                if (knight != null) {
                    tile.setKnight(knight);
                    pieceGroup.getChildren().add(knight);
                }
                if (pawn != null) {
                    tile.setPawn(pawn);
                    pieceGroup.getChildren().add(pawn);
                }
            }
        }

    }

    private boolean OB(int x, int y) {
        if (x < 0 || x > height -1 || y < 0 || y > width - 1){
            return true;
        }
        return false;
    }

    private boolean kingLegalMove(int x0, int y0, int x1, int y1){
        if (OB(x1, y1)) {
            System.out.println("OB");
            return false;
        }
        if (Math.abs(x0 - x1) > 1 || Math.abs(y0 - y1) > 1) {
            System.out.println("Moc daleko");
            return false;
        }
        return true;
    }

    private boolean queenLegalMove(int x0, int y0, int x1, int y1){
        if (OB(x1, y1)) {
            System.out.println("OB");
            return false;
        }
        //spojení pravidel pro věž a střelce (diagonílní, horizontální nebo vertikální pohyb
        if (x0 != x1 && y0 != y1 && Math.abs(x0 - x1) != Math.abs(y0 - y1)) {
            System.out.println("pohyb nesplňuje podmínku směru ");
            return false;
        }

            //kontroluje, že nejsou žádný pieces v cestě
            int xDir = Integer.compare(x1, x0);
            int yDir = Integer.compare(y1, y0);
            int tempX = x0 + xDir;
            int tempY = y0 + yDir;
            while (tempX != x1 || tempY != y1) {
                if (board[tempX][tempY].getPawn() != null) {
                    System.out.println("v cestě překáží piece");
                    return false;
                }
                if (board[tempX][tempY].getKnight() != null) {
                    System.out.println("v cestě překáží piece");
                    return false;
                }
                if (board[tempX][tempY].getBishop() != null) {
                    System.out.println("v cestě překáží piece");
                    return false;
                }
                if (board[tempX][tempY].getRook() != null) {
                    System.out.println("v cestě překáží piece");
                    return false;
                }
                if (board[tempX][tempY].getQueen() != null) {
                    System.out.println("v cestě překáží piece");
                    return false;
                }
                if (board[tempX][tempY].getKing() != null) {
                    System.out.println("v cestě překáží piece");
                    return false;
                }

                tempX += xDir;
                tempY += yDir;
            }


        return true;
    }

    private boolean rookLegalMove(int x0, int y0, int x1, int y1){
        if (OB(x1, y1)) {
            System.out.println("OB");
            return false;
        }
        //kontroluje, že je pohyb vertikální nebo horizontální
        if (x0 != x1 && y0 != y1){
            System.out.println("nesplňuje směr");
            return false;
        }
        //kontroluje, že nejsou žádný pieces v cestě
        int xDir = Integer.compare(x1, x0);
        int yDir = Integer.compare(y1, y0);
        int tempX = x0 + xDir;
        int tempY = y0 + yDir;
        while (tempX != x1 || tempY != y1) {
            if (board[tempX][tempY].getPawn() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getKnight() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getBishop() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getRook() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getQueen() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getKing() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            tempX += xDir;
            tempY += yDir;
        }


        return true;
    }

    private boolean bishopLegalMove (int x0, int y0, int x1, int y1){
        if (OB(x1, y1)) {
            System.out.println("OB");
            return false;
        }
        if (Math.abs(x0 - x1) != Math.abs(y0 - y1)){
            System.out.println("Pohyb není diagonální");
            return false;
        }
        //kontroluje, že nejsou žádný pieces v cestě
        int xDir = (x1 > x0) ? 1 : -1;
        int yDir = (y1 > y0) ? 1 : -1;
        int tempX = x0 + xDir;
        int tempY = y0 + yDir;
        while (tempX != x1 && tempY != y1) {
            if (board[tempX][tempY].getPawn() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getKnight() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getBishop() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getRook() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getQueen() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            if (board[tempX][tempY].getKing() != null) {
                System.out.println("v cestě překáží piece");
                return false;
            }
            tempX += xDir;
            tempY += yDir;
        }

        return true;
    }

    private boolean knightLegalMove(int x0, int y0, int x1, int y1) {
        if (OB(x1, y1)) {
            System.out.println("OB");
            return false;
        }
        if (Math.abs(x0 - x1) == 1 && Math.abs(y0 - y1) == 2  || Math.abs(x0 - x1) == 2 && Math.abs(y0 - y1) == 1){
            return true;
        }
        return false;
    }


    private int toBoard(double pixel) {
        return (int) (pixel + tileSize / 2) / tileSize;
    }

    private King pohybKrale(int x, int y, String player) {
        King king = new King(x, y, player);

        king.setOnMouseReleased(e -> {
            int x1 = toBoard(king.getLayoutX());
            int y1 = toBoard(king.getLayoutY());

            int x0 = toBoard(king.getOldX());
            int y0 = toBoard(king.getOldY());

            boolean legalMove = kingLegalMove(x0, y0, x1, y1);
            System.out.println("Pohyb " + player +" king ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);
            System.out.println("Pohyb " + legalMove);
            if (legalMove) {
                king.move(x1, y1);

                board[x0][y0].setKing(null);
                board[x1][y1].setKing(king);
            } else {
                king.abortMove();
            }
        });
        return king;
    }

    private Queen pohybKralovny (int x, int y, String player){
        Queen queen = new Queen(x, y, player);

        queen.setOnMouseReleased(e -> {
            int x1 = toBoard(queen.getLayoutX());
            int y1 = toBoard(queen.getLayoutY());

            int x0 = toBoard(queen.getOldX());
            int y0 = toBoard(queen.getOldY());

            boolean legalMove = queenLegalMove(x0, y0, x1, y1);
            System.out.println("Pohyb " + player +" queen ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                queen.move(x1, y1);

                board[x0][y0].setQueen(null);
                board[x1][y1].setQueen(queen);
            } else {
                queen.abortMove();
            }
        });
        return queen;
    }

    private Rook pohybVeze (int x, int y, String player) {
        Rook rook = new Rook(x, y, player);

        rook.setOnMouseReleased(e -> {
            int x1 = toBoard(rook.getLayoutX());
            int y1 = toBoard(rook.getLayoutY());

            int x0 = toBoard(rook.getOldX());
            int y0 = toBoard(rook.getOldY());

            boolean legalMove = rookLegalMove(x0, y0, x1, y1);
            System.out.println("Pohyb " + player +" rook ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                rook.move(x1, y1);

                board[x0][y0].setRook(null);
                board[x1][y1].setRook(rook);
            } else {
                rook.abortMove();
            }
        });
        return rook;
    }

    private Bishop pohybStrelce (int x, int y, String player){
        Bishop bishop = new Bishop(x, y, player);

        bishop.setOnMouseReleased(e -> {
            int x1 = toBoard(bishop.getLayoutX());
            int y1 = toBoard(bishop.getLayoutY());

            int x0 = toBoard(bishop.getOldX());
            int y0 = toBoard(bishop.getOldY());

            boolean legalMove = bishopLegalMove(x0, y0, x1, y1);
            System.out.println("Pohyb " + player +" bishop ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);
            System.out.println("Pohyb " + legalMove);


            if (legalMove) {
                bishop.move(x1, y1);

                board[x0][y0].setBishop(null);
                board[x1][y1].setBishop(bishop);
            } else {
                bishop.abortMove();
            }
        });
        return bishop;
    }

    private Knight pohybKnight (int x, int y, String player) {
        Knight knight = new Knight(x, y, player);

        knight.setOnMouseReleased(e -> {
            int x1 = toBoard(knight.getLayoutX());
            int y1 = toBoard(knight.getLayoutY());

            int x0 = toBoard(knight.getOldX());
            int y0 = toBoard(knight.getOldY());

            boolean legalMove = knightLegalMove(x0, y0, x1, y1);
            System.out.println("Pohyb " + player +" knight ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);
            System.out.println("Pohyb " + legalMove);

            if (legalMove) {
                knight.move(x1, y1);

                board[x0][y0].setKnight(null);
                board[x1][y1].setKnight(knight);
            } else {
                knight.abortMove();
            }
        });
        return knight;
    }
    private Pawn pohybPesaku (int x, int y, String player) {
        Pawn pawn = new Pawn(x, y, player);

        pawn.setOnMouseReleased(e -> {
            int x1 = toBoard(pawn.getLayoutX());
            int y1 = toBoard(pawn.getLayoutY());

            int x0 = toBoard(pawn.getOldX());
            int y0 = toBoard(pawn.getOldY());

            System.out.println("Pohyb " + player +" pawn ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);

            pawn.move(x1, y1);
            board[x0][y0].setPawn(null);
            board[x1][y1].setPawn(pawn);

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
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

    private void placeTiles(int[][] gameLayout) {

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Tile tile = null;
                Piece piece = null;
                King king = null;
                String player;

                player = "white";

                if (gameLayout[y][x] == 0)
                    tile = new Tile(x, y);




                if (gameLayout[y][x] > 0) {
                    tile = new Tile(x, y);
                    king = pohybKrale(x, y);
                }


               // piece = pohyb(x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }

                if (king != null) {
                    tile.setKing(king);
                    pieceGroup.getChildren().add(king);
                }

            }
        }

    }

    private int toBoard(double pixel) {
        return (int) (pixel + tileSize / 2) / tileSize;
    }

    private King pohybKrale(int x, int y) {
        King king = new King(x, y, "white");

        king.setOnMouseReleased(e -> {
            int x1 = toBoard(king.getLayoutX());
            int y1 = toBoard(king.getLayoutY());

            int x0 = toBoard(king.getOldX());
            int y0 = toBoard(king.getOldY());

            System.out.println("Pohyb ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);

            king.move(x1, y1);
        });
        return king;
    }



    private Piece pohyb(int x, int y) {
        Piece piece = new Piece(x, y, "white");

        piece.setOnMouseReleased(e -> {
            int x1 = toBoard(piece.getLayoutX());
            int y1 = toBoard(piece.getLayoutY());

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            System.out.println("Pohyb ze souřadnic: " + x0 + " " + y0 + " na souřadnice: " + x1 + " " + y1);

            piece.move(x1, y1);
        });
        return piece;
    }


     private Parent setupGame() {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize);
        root.getChildren().addAll(tileGroup, pieceGroup);
        placeTiles(constantsLayout);
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
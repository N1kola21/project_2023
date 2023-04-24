package com.example.rocnikovka_druhak;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    //probably isnt the right path, i thought when you enter it like this it just searches within the project, but the path seems to be incorrect
    private static final String WHITE_ROOK_IMAGE_PATH = "white_rook.png";
    private static final String BLACK_ROOK_IMAGE_PATH = "black_rook.png";


    //získá proměnné z třídy piece
    public Rook(Color color, Square position, String playerName) {
        super(color, position, playerName);
    }

    @Override
    public List<Square> getMovementOptions(Board b) {
        List<Square> moves = new ArrayList<>();

        Square currentSquare = this.getPosition();
        int currentRow = currentSquare.getRow();
        int currentCol = currentSquare.getColumn();

        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {2, 0}, {-2, 0}, {0, 2}, {0, -2},
                {3, 0}, {-3, 0}, {0, 3}, {0, -3},
                {4, 0}, {-4, 0}, {0, 4}, {0, -4},
                {5, 0}, {-5, 0}, {0, 5}, {0, -5},
                {6, 0}, {-6, 0}, {0, 6}, {0, -6},
                {7, 0}, {-7, 0}, {0, 7}, {0, -7}
        };


        for (int[] offset : directions) {
            int newRow = currentRow + offset[0];
            int newColumn = currentCol + offset[1];
            Square square = b.getSquare(newRow, newColumn);
            Piece piece = square.getPiece();
            if (piece == null || piece.getColor() != this.getColor()) {
                // Add the square to the list of possible moves
                moves.add(square);
            }
        }
        return moves;
    }


    public Node createNode() {

        /*
        Image image = new Image(getColor() == Barva.WHITE ? WHITE_PAWN_IMAGE_PATH : BLACK_PAWN_IMAGE_PATH);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        return imageView;

         */
        StackPane pane = new StackPane();
        Rectangle rectangle = new Rectangle(50, 50);
        Text text = new Text("R");

        if (getColor() == Color.WHITE) {
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(2);
            text.setFill(Color.BLACK);
        }
        if (getColor() == Color.BLACK) {
            rectangle.setFill(Color.BLACK);
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(2);
            text.setFill(Color.WHITE);
        }

        pane.getChildren().addAll(rectangle, text);

        return pane;
    }
}

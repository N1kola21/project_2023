package com.example.rocnikovka_druhak;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    //probably isnt the right path, i thought when you enter it like this it just searches within the project, but the path seems to be incorrect
    private static final String WHITE_ROOK_IMAGE_PATH = "white_king.png";
    private static final String BLACK_ROOK_IMAGE_PATH = "black_king.png";


    //získá proměnné z třídy piece
    public King(Color color, Square position, String playerName) {
        super(color, position, playerName);
    }

    boolean inRange(int row0, int row1, int column0, int column1) {
        if (Math.abs(row0 - row1) > 1 || Math.abs (column0 - column1) > 1) {
            return false;
        }
        return true;
    }

    @Override
    public List<Square> getMovementOptions(Board b) {


        List<Square> moves = new ArrayList<>();

        // Get current position of the king
        Square currentSquare = this.getPosition();
        int currentRow = currentSquare.getRow();
        int currentCol = currentSquare.getColumn();

        // Check all 8 possible moves for the king
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
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
        Text text = new Text("K");

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

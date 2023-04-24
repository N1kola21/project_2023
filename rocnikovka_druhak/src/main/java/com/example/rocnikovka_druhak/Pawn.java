package com.example.rocnikovka_druhak;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

public class Pawn extends Piece{
    //probably isnt the right path, i thought when you enter it like this it just searches within the project, but the path seems to be incorrect
    private static final String WHITE_PAWN_IMAGE_PATH = "white_pawn.png";
    private static final String BLACK_PAWN_IMAGE_PATH = "black_pawn.png";


    //získá proměnné z třídy piece
    public Pawn(Color color, Square position, String playerName) {

        super(color, position, playerName);
    }

    @Override
    public List<Square> getMovementOptions(Board board) {
        // Implement the movement options for the pawn
        return null;
    }

    public Node createNode() {
        /*

        File imageFile = new File("rocnikovka_druhak");
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        return imageView;

         */


        StackPane pane = new StackPane();
        Rectangle rectangle = new Rectangle(50, 50);
        Text text = new Text("P");

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

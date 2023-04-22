package com.example.rocnikovka_druhak_version2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import static com.example.rocnikovka_druhak_version2.constants.viewSize;

public class King extends Piece{
    public King(int x, int y, String player) {
        super(x, y, player);

        move(x, y);

        Circle circle = new Circle(x, y, viewSize);
        Text text = new Text("K");

        if (player == "white") {
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            text.setFill(Color.BLACK);
        } else  {
            circle.setFill(Color.BLACK);
            circle.setStroke(Color.WHITE);
            text.setFill(Color.WHITE);
        }


        getChildren().addAll(circle, text);

    }
}

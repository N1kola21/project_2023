package com.example.rocnikovka_druhak;


import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Piece {
    private Color color;
    private Square position;
    private String playerName;

    public Piece(Color color, Square position, String playerName) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public abstract List<Square> getMovementOptions(Board board);

    public String getImagePath() {
        String colorPrefix = getColor() == Color.WHITE ? "white_" : "black_";
        String className = getClass().getSimpleName().toLowerCase();
        return  "data/" + colorPrefix + className + ".png";
    }

    public abstract Node createNode();

}

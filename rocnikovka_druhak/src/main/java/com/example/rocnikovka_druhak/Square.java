package com.example.rocnikovka_druhak;

import javafx.scene.paint.Color;

public class Square {
    private int column;
    private int row;
    private Piece piece;

    public Square(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

}

package com.example.rocnikovka_druhak_version2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Piece piece;
    private King king;

    public void setKing(King king) {
        this.king = king;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(int x, int y) {
        setWidth(constants.tileSize);
        setHeight(constants.tileSize);

        relocate(x * constants.tileSize, y * constants.tileSize);

            setFill((x + y) % 2 == 0 ? Color.valueOf("#769656") : Color.valueOf("#eeeed2"));

    }
}

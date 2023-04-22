package com.example.rocnikovka_druhak_version2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Piece piece;
    private King king;
    private Queen queen;
    private Rook rook;
    private Bishop bishop;
    private Knight knight;
    private Pawn pawn;


    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public boolean hasPiece() {
        return piece != null;
    }
    public Piece getPiece() {
        return piece;
    }


    public void setKing(King king) {
        this.king = king;
    }
    public boolean hasKing() {
        return king != null;
    }
    public King getKing() {
        return king;
    }


    public void setQueen(Queen queen) {
        this.queen = queen;
    }
    public boolean hasQueen() {
        return queen != null;
    }
    public Queen getQueen() {
        return queen;
    }


    public void setRook(Rook rook) {
        this.rook = rook;
    }
    public boolean hasRook() {
        return rook != null;
    }
    public Rook getRook() {
        return rook;
    }


    public void setBishop(Bishop bishop) {
        this.bishop = bishop;
    }
    public boolean hasBishop() {
        return bishop != null;
    }
    public Bishop getBishop() {
        return bishop;
    }


    public void setKnight(Knight knight) {
        this.knight = knight;
    }
    public boolean hasKnight() {
        return knight != null;
    }
    public Knight getKnight() {
        return knight;
    }


    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }
    public boolean hasPawn() {
        return king != null;
    }
    public Pawn getPawn() {
        return pawn;
    }


    public Tile(int x, int y) {
        setWidth(constants.tileSize);
        setHeight(constants.tileSize);

        relocate(x * constants.tileSize, y * constants.tileSize);

            setFill((x + y) % 2 == 0 ? Color.valueOf("#769656") : Color.valueOf("#eeeed2"));

    }
}

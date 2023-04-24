package com.example.rocnikovka_druhak;

import javafx.scene.paint.Color;

public class Board {
    private Square[][] squares;

    public Board() {
        squares = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new Square(row, col);
            }
        }
    }

    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    public void setSquare(int x, int y, Square square) {
        squares[x][y] = square;
    }

    public void setPiece(int col, int row, Piece piece) {
        squares[col][row].setPiece(piece);
    }

    public void movePiece (Square start, Square end) {
        Piece piece = start.getPiece();
        end.setPiece(piece);
        start.setPiece(null);
    }


    public void initialize() {
        for (int row = 0; row < 8; row++) {
            setPiece(1, row, new Pawn(Color.BLACK, new Square(1, row), "white"));
            setPiece(6, row, new Pawn(Color.BLACK, new Square(1, row), "black"));
        }
        setPiece(0, 0, new Rook(Color.BLACK, new Square(0, 0), "black"));
        setPiece(7, 0, new Rook(Color.BLACK, new Square(7, 0), "black"));
        setPiece(0, 7, new Rook(Color.WHITE, new Square(0, 7), "white"));
        setPiece(7, 7, new Rook(Color.WHITE, new Square(0, 7), "white"));
        setPiece(1, 0, new Knight(Color.BLACK, new Square(1, 0), "black"));
        setPiece(6, 0, new Knight(Color.BLACK, new Square(6, 0), "black"));
        setPiece(1, 7, new Knight(Color.WHITE, new Square(1, 7), "white"));
        setPiece(6, 7, new Knight(Color.WHITE, new Square(6, 7), "white"));
        setPiece(2, 0, new Bishop(Color.BLACK, new Square(2, 0), "black"));
        setPiece(5, 0, new Bishop(Color.BLACK, new Square(5, 0), "black"));
        setPiece(2, 7, new Bishop(Color.WHITE, new Square(2, 7), "white"));
        setPiece(5, 7, new Bishop(Color.WHITE, new Square(5, 7), "white"));
        setPiece(3, 0, new Queen(Color.BLACK, new Square(3, 0), "black"));
        setPiece(3, 7, new Queen(Color.WHITE, new Square(3, 7), "white"));
        setPiece(4, 0, new King(Color.BLACK, new Square(4, 0), "black"));
        setPiece(4, 7, new King(Color.WHITE, new Square(4, 7), "white"));

    }
}


package com.example.rocnikovka_druhak;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Chessboard extends GridPane{
    public static final int SIZE = 8;
    public static final int SQUARE_SIZE = 100;
    private GridPane gridPane;
    private Board board;

    public Chessboard() {

        gridPane = new GridPane();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                square.setFill((row + col) % 2 == 0 ? Color.valueOf("#769656") : Color.valueOf("#eeeed2"));
                gridPane.add(square, col, row);

            }
        }
        initializeBoard();

        /*
        this.board = board;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = board.getSquare(row, col);
                StackPane tile = new StackPane();
                tile.setPrefSize(SQUARE_SIZE, SQUARE_SIZE);
                tile.setAlignment(Pos.CENTER);
                Rectangle rectangle = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                rectangle.setFill(((row + col) % 2 == 0 ? Color.valueOf("#769656") : Color.valueOf("#eeeed2")));
                tile.getChildren().add(rectangle);
                if (!square.isEmpty()) {
                    Piece piece = square.getPiece();
                    ImageView imageView = new ImageView(piece.getImagePath());
                    tile.getChildren().add(imageView);
                }
                add(tile, col, row);
            }
        }

         */
    }

    public void addPiece(Piece piece) {
        Node node = piece.createNode();
        gridPane.add(node, piece.getPosition().getColumn(), piece.getPosition().getRow());
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void initializeBoard() {

        // White pieces
        addPiece(new Rook(Color.WHITE, new Square(0,7), "white"));
        addPiece(new Rook(Color.WHITE, new Square(7,7), "white"));
        addPiece(new Knight(Color.WHITE, new Square(6,7), "white"));
        addPiece(new Knight(Color.WHITE, new Square(1,7), "white"));
        addPiece(new Bishop(Color.WHITE, new Square(5,7), "white"));
        addPiece(new Bishop(Color.WHITE, new Square(2,7), "white"));
        addPiece(new Queen(Color.WHITE, new Square(4,7), "white"));
        addPiece(new King(Color.WHITE, new Square(3,7), "white"));
        for (int whitePawnX = 0; whitePawnX < 8; whitePawnX++) {
            addPiece(new Pawn(Color.WHITE, new Square(whitePawnX, 6), "white"));
        }

        //Black pieces
        addPiece(new Rook(Color.BLACK, new Square(0,0), "black"));
        addPiece(new Rook(Color.BLACK, new Square(7,0), "black"));
        addPiece(new Knight(Color.BLACK, new Square(6,0), "black"));
        addPiece(new Knight(Color.BLACK, new Square(1,0), "black"));
        addPiece(new Bishop(Color.BLACK, new Square(5,0), "black"));
        addPiece(new Bishop(Color.BLACK, new Square(2,0), "black"));
        addPiece(new Queen(Color.BLACK, new Square(4,0), "black"));
        addPiece(new King(Color.BLACK, new Square(3,0), "black"));
        for (int blackPawnX = 0; blackPawnX < 8; blackPawnX++) {
            addPiece(new Pawn(Color.BLACK, new Square(blackPawnX, 1), "black"));
        }

/*
        gridPane.setOnMousePressed(e -> {
            int row = GridPane.getRowIndex((Node) e.getTarget());
            int col = GridPane.getColumnIndex((Node) e.getTarget());
            System.out.println("Mouse pressed on row: " + row + ", column: " + col);
        });

        gridPane.setOnMouseReleased(e -> {
            int row = GridPane.getRowIndex((Node) e.getTarget());
            int col = GridPane.getColumnIndex((Node) e.getTarget());
            System.out.println("Mouse released on row: " + row + ", column: " + col);
        });

 */



    }

    private void handleMousePressed(MouseEvent event) {
        Node source = (Node) event.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        if (colIndex == null) {
            colIndex = 0;
        }
        if (rowIndex == null) {
            rowIndex = 0;
        }
        System.out.println("Mouse pressed at row: " + rowIndex + ", column: " + colIndex);
    }
    private void handleMouseDragged(Piece piece, MouseEvent e) {

    }

}


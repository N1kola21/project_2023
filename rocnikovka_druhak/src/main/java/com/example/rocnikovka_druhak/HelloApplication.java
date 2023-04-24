package com.example.rocnikovka_druhak;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.rocnikovka_druhak.Chessboard.SIZE;
import static com.example.rocnikovka_druhak.Chessboard.SQUARE_SIZE;


public class HelloApplication extends Application {

    private Parent setupGame() {
        Pane root = new Pane();
        root.setPrefSize(SIZE * SQUARE_SIZE, SIZE * SQUARE_SIZE);
        Chessboard chessboard = new Chessboard();
        root.getChildren().add(chessboard.getGridPane());
        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {


        Scene scene = new Scene(setupGame());
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
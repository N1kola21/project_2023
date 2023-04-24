module com.example.rocnikovka_druhak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rocnikovka_druhak to javafx.fxml;
    exports com.example.rocnikovka_druhak;
}
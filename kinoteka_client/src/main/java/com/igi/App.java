package com.igi;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main( String[] args ) {
       launch(args);
    }

    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                        .getClassLoader()
                        .getResource("fxml/window.fxml"));
        primaryStage.setTitle("Filmoteka");
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("css/style.css");
        primaryStage.setScene(scene);
       
        primaryStage.show();
        window = primaryStage;
        primaryStage.setOnCloseRequest(e -> close());
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
    }

    private void close() {
        window.close();
    }
}

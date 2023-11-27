package client;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @FXML
    private AnchorPane container;


    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dictionary/EXBackground.fxml"));
                                                                    //"/dictionary/hello-view.fxml"F:\Dictionary\src\main\resources\dictionary\Background.fxml
            Scene scene = new Scene(root, 1000, 800);
//            scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }





}



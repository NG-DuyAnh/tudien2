package client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BackgroundController  implements Initializable {

    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane hangmanPane;
    @FXML
    private AnchorPane databasePane;



    // tọa tối tượng class để còn sử dụng và liên kêt .............

    private SearchController searchController;
    private TranslateController translateController;
    private HangmanController hangmanController;
    private DBController dbController;



    // TODO: so sánh với showcompoent của bạn

    @FXML
    private Button translateButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button gameButton;

    @FXML
    private Button databaseButton;





    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }




    public void resetStyleNav() {
        SearchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        gameButton.getStyleClass().removeAll("active");
        databaseButton.getStyleClass().removeAll("active");
    }




    @FXML
    public void showSearchPane() {
        resetStyleNav();   //xóa đậm tất cả button
        SearchButton.getStyleClass().add("active"); // giữ button đậm
        searchController.initSearchListView(); //reset lại pane khi quay trở lại
        setMainContent(searchPane);
    }

    @FXML
    public void showTranslatePane() {
        resetStyleNav();   //xóa đậm tất cả button
        translateButton.getStyleClass().add("active"); // giữ button đậm
        translateController.initSearchListView(); //reset lại pane khi quay trở lại
        setMainContent(translatePane);
    }
    public void showGamePane() {
        resetStyleNav();   //xóa đậm tất cả button
        gameButton.getStyleClass().add("active"); // giữ button đậm
        //translateButton.initSearchListView(); //reset lại pane khi quay trở lại
        setMainContent(hangmanPane);
    }

    public void showDatabasePane() {
        resetStyleNav();;
        databaseButton.getStyleClass().add("active");

        setMainContent(databasePane);
    }





    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/Translate.fxml"));
            translatePane = loader.load();
            translateController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/Search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/Hangman.fxml"));
            hangmanPane = loader.load();
            hangmanController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/Database.fxml"));
            databasePane = loader.load();
            dbController = loader.getController();
        }catch (Exception e) {
            e.printStackTrace();
        }

        SearchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }


}
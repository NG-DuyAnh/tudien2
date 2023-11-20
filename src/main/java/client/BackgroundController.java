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

public class BackgroundController   implements Initializable {



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
    @FXML
    private AnchorPane settingPane;



    // tọa tối tượng class để còn sử dụng và liên kêt .............

    private SearchController searchController;
    private TranslateController translateController;
    private HangmanController hangmanController;
    private DBController dbController;
    private SettingController settingController;



    // TODO: so sánh với showcompoent của bạn

    @FXML
    private Button translateButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button gameButton;

    @FXML
    private Button databaseButton;

    @FXML
    private Button settingButton;





    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }




    public void resetStyleNav() {
        SearchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        gameButton.getStyleClass().removeAll("active");
        databaseButton.getStyleClass().removeAll("active");
        settingButton.getStyleClass().removeAll("active");
    }




    @FXML
    public void showSearchPane() {
        resetStyleNav();   //xóa đậm tất cả button
        SearchButton.getStyleClass().add("active"); // giữ button đậm
        //searchController.initSearchListView(); //reset lại pane khi quay trở lại
        searchController.updateButtonsVisibility() ;
        setMainContent(searchPane);
    }

    @FXML
    public void showTranslatePane() {
        resetStyleNav();   //xóa đậm tất cả button
        translateButton.getStyleClass().add("active"); // giữ button đậm
        //translateController.initSearchListView(); //reset lại pane khi quay trở lại
        setMainContent(translatePane);
    }
    public void showGamePane() {
        resetStyleNav();   //xóa đậm tất cả button
        gameButton.getStyleClass().add("active"); // giữ button đậm
        //Game.initSearchListView(); //reset lại pane khi quay trở lại
        setMainContent(hangmanPane);
    }

    public void showDatabasePane() {
        resetStyleNav();
        databaseButton.getStyleClass().add("active");
        dbController.updateButtonsVisibility() ;
        setMainContent(databasePane);
    }

    public void showSettingPane() {
        resetStyleNav();
        settingButton.getStyleClass().add("active");

        setMainContent(settingPane);
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
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/Setting.fxml"));
            settingPane = loader.load();
            settingController = loader.getController();
        }catch (Exception e){
            e.printStackTrace();
        }

        SearchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }


}
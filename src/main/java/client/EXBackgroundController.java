package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EXBackgroundController extends EX implements Initializable {


    public AnchorPane mainContent;
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


    public void showSearchPane() {
        showThePane(mainContent, searchPane);
        searchController.updateButtonsVisibility() ;
    }
    public void showTranslatePane() {
        showThePane(mainContent, translatePane);
    }

    public void showGamePane() {
        showThePane(mainContent, hangmanPane);
    }

    public void showDatabasePane() {
        dbController.updateButtonsVisibility() ;
        showThePane(mainContent, databasePane);
    }

    public void showSettingPane() {
        showThePane(mainContent, settingPane);
    }



    public void initialize(URL location, ResourceBundle resources) {

        FXMLLoader translateLoader = initializeController("/dictionary/Translate.fxml");
        translatePane = translateLoader.getRoot();
        translateController = translateLoader.getController();

        FXMLLoader searchLoader = initializeController("/dictionary/Search.fxml");
        searchPane = searchLoader.getRoot();
        searchController = searchLoader.getController();

        FXMLLoader hangmanLoader = initializeController("/dictionary/Hangman.fxml");
        hangmanPane = hangmanLoader.getRoot();
        hangmanController = hangmanLoader.getController();

        FXMLLoader dbLoader = initializeController("/dictionary/Database.fxml");
        databasePane = dbLoader.getRoot();
        dbController = dbLoader.getController();

        FXMLLoader settingLoader = initializeController("/dictionary/Setting.fxml");
        settingPane = settingLoader.getRoot();
        settingController = settingLoader.getController();
        showThePane(mainContent, searchPane);
    }


}

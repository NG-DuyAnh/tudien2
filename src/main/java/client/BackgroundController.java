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

public class BackgroundController implements Initializable {

    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;


    @FXML
    private SearchController searchController;





    @FXML
    private Button translateButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button gameButton;



    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }




    public void resetStyleNav() {
        SearchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");

    }

    @FXML
    public void showSearchPane() {
        resetStyleNav();   //xóa đậm tất cả button
        SearchButton.getStyleClass().add("active"); // giữ button đậm
        searchController.initSearchListView(); //reset lại pane khi quay trở lại
        setMainContent(searchPane);
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/Search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        searchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }


}
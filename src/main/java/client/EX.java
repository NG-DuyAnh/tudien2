package client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EX  {


    //! xử lí hiện pane
    public void showThePane (AnchorPane mainContent, AnchorPane ThePane){
        mainContent.getChildren().setAll(ThePane);
    }

    //! xử lí load sẵn
    public FXMLLoader initializeController(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();
            Object controller = loader.getController();
            return loader;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


//    private void setMainContent(AnchorPane anchorPane) {
//        mainContent.getChildren().setAll(anchorPane);
//    }
//
//
//    public void showTranslatePane() {
//        resetStyleNav();   //xóa đậm tất cả button
//        translateButton.getStyleClass().add("active"); // giữ button đậm
//        //translateController.initSearchListView(); //reset lại pane khi quay trở lại
//        setMainContent(translatePane);
//    }


}

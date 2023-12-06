package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController extends UtilitiesController implements Initializable {

    @FXML
    private ChoiceBox<String> ENvoiceUS;
    private String[] ENoptionUS = {"Linda", "Amy", "Mary", "John", "Mike"};

    @FXML
    private ChoiceBox<String> ENvoiceUK;
    private String[] ENoptionUK = {"Alice", "Nancy", "Lily", "Harry"};

    @FXML
    private ChoiceBox<String> RUvoice;
    private String[] RUoption = {"Olga", "Marina", "Peter"};

    @FXML
    private ChoiceBox<String> VIvoice;
    private String[] VIoption = {"Chi"};

    @FXML
    private ChoiceBox<String> ZHvoice;
    private String[] ZHoption = {"Luli", "Shu", "Chow", "Wang"};


    // !xử lí chọn từ cho các tts
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("SettingController initialized");
        // Set options for each ChoiceBox
        ENvoiceUS.getItems().addAll(ENoptionUS);
        ENvoiceUK.getItems().addAll(ENoptionUK);
        RUvoice.getItems().addAll(RUoption);
        VIvoice.getItems().addAll(VIoption);
        ZHvoice.getItems().addAll(ZHoption);

        ENvoiceUK.setValue("Harry");
        ENvoiceUS.setValue("Linda");
        RUvoice.setValue("Olga");
        VIvoice.setValue("Chi");
        ZHvoice.setValue("Luli");

        ENvoiceUS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            voiceUS = newValue;
        });
        ENvoiceUK.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            voiceUK = newValue;
        });
        RUvoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            voiceRU = newValue;
        });
        VIvoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            voiceVI = newValue;
        });
        ZHvoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            voiceZH = newValue;
        });

        System.out.println("SettingController initialization complete");
    }


}

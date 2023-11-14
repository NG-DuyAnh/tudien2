package client;

import API.GoogleTranlate;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class TranslateController {


    // ?ô ghi từ và hiện từ
    @FXML
    private TextArea BeTranslated;
    @FXML
    private TextField TranslatedFrom;


    @FXML
    private ChoiceBox<String> LanguageBeTranslated;
    private String[] LanguageBeTranslatedChoice = {"vi","en","zh","ru"};  // *ngôn ngữ được dịch
    @FXML
    private ChoiceBox<String> LanguageTranslateFrom;
    private String[] LanguageTranslateFromChoice = {"vi","en","zh","ru"}; // *ngôn ngữ dịch ra (output)



    @FXML
    private Button TranslateButton; // !nút dịch




    @FXML
    public void initialize() {
        // Set default values for the ChoiceBoxes
        LanguageBeTranslated.setValue("vi");
        LanguageTranslateFrom.setValue("en");
    }

    public void translateButtonClicked() {
        String langFrom = LanguageBeTranslated.getValue(); // Ngôn ngữ nguồn
        String langTo = LanguageTranslateFrom.getValue(); // Ngôn ngữ đích
        String textToTranslate = BeTranslated.getText(); // Văn bản cần dịch

        try {
            String translatedText = GoogleTranlate.googleTranslate(langFrom, langTo, textToTranslate);
            // Hiển thị kết quả dịch ở TranslatedFrom
            TranslatedFrom.setText(translatedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void initSearchListView() {
        BeTranslated.clear();
        TranslatedFrom.clear();
    }


}

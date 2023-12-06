package client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import API.GoogleTranlate;
import API.VoiceRSS;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class TranslateController extends UtilitiesController implements Initializable {


    // ?ô ghi từ và hiện từ
    @FXML
    private TextArea BeTranslated;

    @FXML
    private TextArea TranslatedFrom;


    @FXML
    private ChoiceBox<String> LanguageBeTranslated;
    private String[] LanguageBeTranslatedChoice = {"vi","en","zh","ru"};  // *ngôn ngữ được dịch
    @FXML
    private ChoiceBox<String> LanguageTranslateFrom;
    private String[] LanguageTranslateFromChoice = {"vi","en","zh","ru"}; // *ngôn ngữ dịch ra (output)

    private String LanguageBeTranslated_Voice;

    private String LanguageTranslateFrom_Voice;

    @FXML
    private Button TranslateButton; // !nút dịch

    @FXML
    private Button SpeakBeTranslated;

    @FXML
    private Button SpeakTranslatedFrom;



    // !xử lí chọn NGÔN NGỮ SẼ ĐƯỢC DỊCH
    public void HandleBeTranslatedspeakButton() throws Exception{
        //VoiceRSS.Voice = UKVoice;
        String BeTranslatedText = BeTranslated.getText();
        LanguageBeTranslated_Voice = LanguageBeTranslated.getValue();
        switch (LanguageBeTranslated_Voice) {
            case "vi":
                GeneralhandleSpeakButton("vi-vn", BeTranslatedText,voiceVI );
                break;
            case "en":
                if (voiceUK == null){
                    voiceUK = "Harry";
                }
                GeneralhandleSpeakButton("en-gb", BeTranslatedText,voiceUK );
                break;
            case "zh" :
                if (voiceZH == null){
                    voiceZH = "Luli";
                }
                GeneralhandleSpeakButton("zh-cn", BeTranslatedText,voiceZH );
                break;
            case "ru" :
                if (voiceRU == null){
                    voiceRU = "Marina";
                }
                GeneralhandleSpeakButton("ru-ru", BeTranslatedText,voiceRU );
                break;
        }
    }

    // !xử lí chọn NGÔN NGỮ ĐƯỢC DỊCH RA
    public void HandleTranslatedFromspeakButton() throws Exception{
        //VoiceRSS.Voice = UKVoice;
        String TranslatedFromText = TranslatedFrom.getText();
        LanguageTranslateFrom_Voice = LanguageTranslateFrom.getValue();
        switch (LanguageTranslateFrom_Voice) {
            case "vi":

                GeneralhandleSpeakButton("vi-vn", TranslatedFromText,voiceVI);
                break;
            case "en":
                if (voiceUK == null){
                    voiceUK = "Harry";
                }
                GeneralhandleSpeakButton("en-gb", TranslatedFromText,voiceUK );
                break;
            case "zh" :
                if (voiceZH == null){
                    voiceZH = "Luli";
                }
                GeneralhandleSpeakButton("zh-cn", TranslatedFromText,voiceZH );
                break;
            case "ru" :
                if (voiceRU == null){
                    voiceRU = "Marina";
                }
                GeneralhandleSpeakButton("ru-ru", TranslatedFromText,voiceRU);
                break;
        }
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Set default values for the ChoiceBoxes
        System.out.println("ini run");
        LanguageBeTranslated.setValue("vi");
        LanguageTranslateFrom.setValue("en");
        LanguageBeTranslated.getItems().addAll(LanguageBeTranslatedChoice);
        LanguageTranslateFrom.getItems().addAll(LanguageTranslateFromChoice);
    }

//    public void initialize() {
//        // Set default values for the ChoiceBoxes
//        LanguageBeTranslated.setValue(LanguageBeTranslatedChoice[0]); // Chọn giá trị mặc định từ mảng
//        LanguageBeTranslated.getItems().addAll(LanguageBeTranslatedChoice);
//
//        LanguageTranslateFrom.setValue(LanguageTranslateFromChoice[1]); // Chọn giá trị mặc định từ mảng
//        LanguageTranslateFrom.getItems().addAll(LanguageTranslateFromChoice);
//    }




    // ! XỬ LÍ DỊCH
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


    //!KHÔNG CẦN THIẾT
//    public void initSearchListView() {
//        BeTranslated.clear();
//        TranslatedFrom.clear();
//    }


}

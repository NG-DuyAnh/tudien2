package client;

import API.VoiceRSS;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends UtilitiesController implements Initializable {

    @FXML
    private Button UKspeakingButton;

    @FXML
    private Button USspeakingButton;

    private static String UKVoice = "Harry" ;

    private static String USVoice = "Linda" ;

    @FXML
    protected WebView definitionView;

    public void handleClickListView() {
        selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            // Xử lý từ đã được chọn, ví dụ: hiển thị nghĩa của từ
            String meaning = htmlDictionary.getWordList().get(selectedWord);
            definitionView.getEngine().loadContent(meaning);
        }
    }




    public void showDefinitionInWebView() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            String meaning = htmlDictionary.getWordList().get(selectedWord);
            if (meaning != null) {
                definitionView.getEngine().loadContent(meaning, "text/html");
            }
        }
    }



    // ! CAY LẮM, CAY ƠI là CAY. trình độ không đủ để tạo code convert DB gốc sang DB HTML theo ý muốn riêng. VÌ thế, method này là method chữa cháy cho DB HTML của project này.
    // ! Method sau chuyển các từ chưa được chuyển màu trong DB HTML thành màu ta yêu cầu.
    // TODO: tìm cách Overwrite toàn bộ! chắc impossible

    // ! GIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE LÀM ĐC RỒI ctrl + / trong nước mắt và tiếng cười


//public void showDefinitionInWebView() {
//    String selectedWord = listView.getSelectionModel().getSelectedItem();
//    if (selectedWord != null) {
//        String meaning = htmlDictionary.getWordList().get(selectedWord);
//        if (meaning != null) {
//            // Wrap the meaning content with a style to set the text color to blue
//            String styledContent = "<html><body>" + meaning + "</body></html>";
//
//            WebEngine webEngine = definitionView.getEngine();
//            webEngine.loadContent(styledContent, "text/html");
//
//            // Use JavaScript to change the color of all text to blue
//            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//                if (newValue == Worker.State.SUCCEEDED) {
//                    webEngine.executeScript("document.body.style.color = '#aac9da';");
//                }
//            });
//        }
//    }
//}
    public void initSearchListView() {
        listView.getItems().clear();
        searchBar.clear();
        definitionView.getEngine().loadContent("");
    }




    public void HandleUKspeakButton() throws Exception{
        VoiceRSS.Voice = UKVoice;
        GeneralhandleSpeakButton("en-gb",selectedWord);
    }

    public void HandleUSspeakButton() throws Exception{
        VoiceRSS.Voice = USVoice;
        GeneralhandleSpeakButton("en-us",selectedWord);
    }
}

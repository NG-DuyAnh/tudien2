package client;

import API.ThesaurusApi;
import API.VoiceRSS;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchController extends UtilitiesController implements Initializable {

    @FXML
    private Button UKspeakingButton;

    @FXML
    private Button USspeakingButton;



    @FXML
    protected WebView definitionView;

    @FXML
    private TextArea AntonymsTextField;

    @FXML
    private TextArea SynonymsTextField;





    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle); // Call the superclass's initialize method
        setDefaultBackground();
    }



    private void setDefaultBackground() {
        // Set màu nền mặc định cho definitionView
        String defaultBackgroundColor = pageFillProperty.get().toString().substring(2, 8);
        definitionView.getEngine().loadContent("<html><body style=\"background-color: #" + defaultBackgroundColor + ";\"></body></html>", "text/html");
    }




    public void handleClickListView() {
        selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            // Xử lý từ đã được chọn, ví dụ: hiển thị nghĩa của từ
            String meaning = htmlDictionary.getWordList().get(selectedWord);
            definitionView.getEngine().loadContent(meaning);
        }
    }


    public void ShowThesaurusinBothTField(String selectedWord) {
        new Thread(() -> {
            try {
                ThesaurusApi thesaurusApi = new ThesaurusApi();
                ArrayList<String> synonyms = thesaurusApi.getSynonyms(selectedWord);
                ArrayList<String> antonyms = thesaurusApi.getAntonyms(selectedWord);

                // Run UI updates on the JavaFX application thread
                Platform.runLater(() -> {
                    // Display synonyms in the Synonyms TextField
                    SynonymsTextField.setText(synonyms.isEmpty() ? "<none>" : String.join("\n", synonyms));

                    // Display antonyms in the Antonyms TextField
                    AntonymsTextField.setText(antonyms.isEmpty() ? "<none>" : String.join("\n", antonyms));
                });
            } catch (IOException e) {
                // Handle IOException (API errors)
                e.printStackTrace();

                // Run UI updates on the JavaFX application thread
                Platform.runLater(() -> {
                    // Display error message in both text fields
                    SynonymsTextField.setText("<none>" + "\n" + "limitedAPI" + "\n" + "(DOES NOT ACCEPT WHITE SPACE)");
                    AntonymsTextField.setText("<none>" + "\n" + "limitedAPI" + "\n" + "(DOES NOT ACCEPT WHITE SPACE)");
                });
            } catch (Exception e) {
                // Handle other exceptions
                e.printStackTrace();
            }
        }).start();
    }


    public void showDefinitionInWebView() {
        selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            // Get the meaning from the local dictionary
            String meaning = htmlDictionary.getWordList().get(selectedWord);

            ShowThesaurusinBothTField(selectedWord);

            if (meaning != null) {
                // Set the background color using inline CSS
                String backgroundColor = pageFillProperty.get().toString().substring(2, 8);

                // Load the content with inline CSS to set the background color
                definitionView.getEngine().loadContent("<html><body style=\"background-color: #" + backgroundColor + ";\">" + meaning + "</body></html>", "text/html");
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
        if (voiceUK == null){
            voiceUK = "Harry";
        }
        GeneralhandleSpeakButton("en-gb",selectedWord,voiceUK);
    }

    public void HandleUSspeakButton() throws Exception{
        if (voiceUS == null){
            voiceUS = "Linda";
        }
        GeneralhandleSpeakButton("en-us",selectedWord,voiceUS);
    }




}

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




    //!thừ kế từ ultilites chỉnh sửa chút ít để có được background của webview
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle); // Call the superclass's initialize method
        setDefaultBackground();
    }


    // !NHƯ TÊN, set màu cho webview
    private void setDefaultBackground() {
        // Set màu nền mặc định cho definitionView
        String defaultBackgroundColor = pageFillProperty.get().toString().substring(2, 8);
        definitionView.getEngine().loadContent("<html><body style=\"background-color: #" + defaultBackgroundColor + ";\"></body></html>", "text/html");
    }







    public void ShowThesaurusinBothTField(String selectedWord) {
        new Thread(() -> {
            try {
                // Convert selectedWord to lowercase and replace spaces with hyphens
                String formattedWord = selectedWord.toLowerCase().replace(" ", "-");

                ThesaurusApi thesaurusApi = new ThesaurusApi();
                ArrayList<String> synonyms = thesaurusApi.getSynonyms(formattedWord);
                ArrayList<String> antonyms = thesaurusApi.getAntonyms(formattedWord);

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




//    public void handleClickListView() {
//        selectedWord = listView.getSelectionModel().getSelectedItem();
//        if (selectedWord != null) {
//            // Xử lý từ đã được chọn, ví dụ: hiển thị nghĩa của từ
//            String meaning = htmlDictionary.getWordList().get(selectedWord);
//            definitionView.getEngine().loadContent(meaning);
//        }
//    }


    //! xử lý hiện nghĩa của từ sang webview
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




    //?phương thức bây giờ không cần thiết do đã làm chủ  !DATABASE!
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


    // !KHÔNG CẦN NỮA
//    public void initSearchListView() {
//        listView.getItems().clear();
//        searchBar.clear();
//        definitionView.getEngine().loadContent("");
//    }



    // !XỬ LÝ TTS
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

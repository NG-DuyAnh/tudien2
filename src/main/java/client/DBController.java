package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.event.ActionEvent;
import javafx.scene.web.WebEngine;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class DBController extends UtilitiesController implements Initializable {


    @FXML
    private AnchorPane DBpane;


    @FXML
    private Button RemoveWord;
    @FXML
    private Button SaveEdit;
    @FXML
    private Button addWord;




    @FXML
    private HTMLEditor Editor;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        setDefaultEditorBackground();
    }


//    private void setDefaultBackground() {
//        // Set the default background color for the HTMLEditor
//        String defaultBackgroundColor = pageFillProperty.get().toString().substring(2, 8);
//        Editor.setHtmlText("<html><body style=\"background-color: #" + defaultBackgroundColor + ";\"></body></html>");
//    }

    private void setDefaultEditorBackground() {
        // Lấy màu nền mặc định từ thuộc tính pageFillProperty
        String defaultBackgroundColor = pageFillProperty.get().toString().substring(2, 8);

        // Tạo chuỗi HTML với màu nền đã lấy
        String htmlContent = "<html><body style=\"background-color: #" + defaultBackgroundColor + ";\"></body></html>";

        // Đặt nội dung HTML cho HTMLEditor
        Editor.setHtmlText(htmlContent);
    }




    @FXML
    public void showDefinitionInEditor(MouseEvent event) {
        selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            // Xử lý từ đã được chọn, ví dụ: hiển thị nghĩa của từ
            String meaning = htmlDictionary.getWordList().get(selectedWord);

            // Lấy màu từ PageFILL
            String BackgroundColor = pageFillProperty.get().toString().substring(2,8);

            //tạo chuỗi html với màu nền đã lấy
            String EditorContent = "<html><body style=\"background-color: #" + BackgroundColor + ";\">" + meaning + "</body></html>";

            Editor.setHtmlText(EditorContent);


        }
    }

    @FXML
    public void handleAddWord(ActionEvent event){
        String newWord = searchBar.getText().trim();
        String newMeaning = Editor.getHtmlText().replace(" dir=\"ltr\"", "").trim();

        if (htmlDictionary.getWordList().containsKey(newWord)){
            showAlert("Error","Từ đã có trong database");
            return;
        }
        if (newWord.isEmpty() ){
            showAlert("Error", "Thiếu từ mới");
            return;
        }
        if ( newMeaning.isEmpty()){
            showAlert("Error", "Thiêu nghĩa mới");
            return;
        }

        //htmlDictionary.getWordList().put(newWord,newMeaning);
        htmlDictionary.updateWord(newWord,newMeaning);
        htmlDictionary.updateWordListtoHTMLfile(Path, htmlDictionary.getWordList());
        updateSearchResults();


    }

    @FXML
    public void handleSaveEdit(ActionEvent event) {
        String editedMeaning = Editor.getHtmlText().replace(" dir=\"ltr\"", "").trim();

        if (selectedWord != null && !editedMeaning.isEmpty()) {
            htmlDictionary.updateWord(selectedWord, editedMeaning);
            htmlDictionary.updateWordListtoHTMLfile(Path, htmlDictionary.getWordList());

            // Optionally, clear the editor after saving
            Editor.setHtmlText("");
        } else {
            // Show an alert indicating that no word is selected or the edited meaning is empty
            showAlert("Error", "No word selected or edited meaning is empty");
        }
    }

    public void handleRemoveWord (ActionEvent event){
        if(selectedWord == null){
            showAlert("Error", "No word selected");
            return;
        }
        htmlDictionary.removeWord(selectedWord);
        htmlDictionary.updateWordListtoHTMLfile(Path, htmlDictionary.getWordList());
        Editor.setHtmlText("");
        searchBar.clear();
        listView.getItems().clear();
    }


    private void showAlert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }





}

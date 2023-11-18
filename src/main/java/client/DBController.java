package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.event.ActionEvent;


public class DBController extends UtilitiesController implements Initializable {

    @FXML
    private Button RemoveWord;
    @FXML
    private Button SaveEdit;
    @FXML
    private Button addWord;




    @FXML
    private HTMLEditor Editor;



    @FXML
    public void showDefinitionInEditor(MouseEvent event) {
        selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            // Xử lý từ đã được chọn, ví dụ: hiển thị nghĩa của từ
            String meaning = htmlDictionary.getWordList().get(selectedWord);
            Editor.setHtmlText(meaning);
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
            showAlert("Error", "Thiêu đầu vào 1");
            return;
        }
        if ( newMeaning.isEmpty()){
            showAlert("Error", "Thiêu đầu vào 2");
            return;
        }

        htmlDictionary.getWordList().put(newWord,newMeaning);

        htmlDictionary.updateWordListtoHTMLfile(Path, htmlDictionary.getWordList());

        Editor.setHtmlText("");
        searchBar.clear();
    }

    private void showAlert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }



}

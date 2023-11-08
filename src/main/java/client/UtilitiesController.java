package client;
import Base.HTMLDictionary;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class UtilitiesController extends BackgroundController {


    protected static HTMLDictionary htmlDictionary = new HTMLDictionary();





    // !BIẾN XỬ LÍ THANH TÌM KIẾM VÀ THANH HIỆN CHỮ (SEARCH)

    @FXML
    protected ListView<String> listView;

    @FXML
    protected TextField searchBar;



    @FXML
    protected WebView definitionView;





    //TODO: tìm hiểu sao lại có intiialize ở background và các lớp con của lớp này
    public void initialize(URL location, ResourceBundle resources) {
    }

    // ! xử lí tìm kiếm (SEARCH)
//    public void updateSearchResults() {
//        listView.getItems().clear();
//        List<String> searchResults = searchList(searchBar.getText(), htmlDictionary.getWordList());
//        listView.getItems().addAll(searchResults);
//    }
    public void updateSearchResults() {
        String searchInput = searchBar.getText();
        if (!searchInput.isEmpty()) {
            listView.getItems().clear();
            List<String> searchResults = searchList(searchInput, htmlDictionary.getWordList());
            listView.getItems().addAll(searchResults);
        }
    }
    public List<String> searchList(String searchWords, TreeMap<String, String> wordMap) {
        return wordMap.entrySet().stream()
                .filter(entry -> entry.getKey().toLowerCase().startsWith(searchWords.toLowerCase()))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }


    public void handleClickListView() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            // Xử lý từ đã được chọn, ví dụ: hiển thị nghĩa của từ
            String meaning = htmlDictionary.getWordList().get(selectedWord);
            definitionView.getEngine().loadContent(meaning);
        }
    }



}

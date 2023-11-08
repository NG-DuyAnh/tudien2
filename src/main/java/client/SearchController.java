package client;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends UtilitiesController {


    public void initialize(URL url, ResourceBundle resourceBundle) {
        htmlDictionary.loadFromFile("F:\\test\\src\\eng_vie.txt");
        //listView.getItems().addAll(htmlDictionary.getWordList().values());
        // ?code này hiện tất cả các từ khi mở chương trình
        searchBar.setOnKeyReleased(event -> {
            updateSearchResults();
        });
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

    public void initSearchListView() {
        listView.getItems().clear();
        
    }


}

package client;

import Base.HTMLDictionary;
import API.VoiceRSS;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class UtilitiesController implements Initializable {



    protected static String voiceUS;

    protected static String voiceUK;

    protected static String voiceVI;

    protected static String voiceRU;

    protected static String voiceZH;



    protected static HTMLDictionary htmlDictionary = new HTMLDictionary();


    protected static final String EVPath = "F:\\Dictionary\\src\\main\\resources\\DB\\eng_vie.txt"  ;

    protected static final String VEPath = "F:\\Dictionary\\src\\main\\resources\\DB\\vie_eng.txt" ;

    protected static String Path ;

    @FXML
    protected   Button VietAnh;
    @FXML
    protected   Button AnhViet;




    // !BIẾN XỬ LÍ THANH TÌM KIẾM VÀ THANH HIỆN CHỮ (SEARCH)
    @FXML
    protected ListView<String> listView;

    @FXML
    protected TextField searchBar;



    // !Bbiến xử lí hiện meaning của word
    protected String selectedWord;



    protected final ObjectProperty<Color> pageFillProperty = new SimpleObjectProperty<>(Color.web("#171d20"));



    public ObjectProperty<Color> pageFillProperty() {
        return pageFillProperty;
    }

    public Color getPageFill() {
        return pageFillProperty.get();
    }

    public void setPageFill(Color color) {
        pageFillProperty.set(color);
    }





    //TODO: tìm hiểu sao lại có intiialize ở background và các lớp con của lớp này
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Path = EVPath;
        VietAnh.setVisible(false);
        htmlDictionary.loadFromFile(Path);
        //listView.getItems().addAll(htmlDictionary.getWordList().values());

        // ?code này hiện tất cả các từ khi mở chương trình
        // !và đã đươc chặn ở method dưới ????
        searchBar.setOnKeyReleased(event -> {
            updateSearchResults();
        });


    }







    // ! xử lí tìm kiếm (SEARCH)
//    public void updateSearchResults() {
//        listView.getItems().clear();
//        List<String> searchResults = searchList(searchBar.getText(), htmlDictionary.getWordList());
//        listView.getItems().addAll(searchResults);
//    }

    // mỗi lần nhập từ thì update
    public void updateSearchResults() {
        String searchInput = searchBar.getText();
        if (!searchInput.isEmpty()) {
            listView.getItems().clear();
            List<String> searchResults = searchList(searchInput, htmlDictionary.getWordList());
            listView.getItems().addAll(searchResults);
        }else{
            listView.getItems().clear();
            //TODO:
        }
    }


    // TODO: có vid xem lại youtube để hiểu rõ hơn, quên rồi
    public List<String> searchList(String searchWords, TreeMap<String, String> wordMap) {
        return wordMap.entrySet().stream()
                .filter(entry -> entry.getKey().toLowerCase().startsWith(searchWords.toLowerCase()))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    // !click vào từ sẽ hiện meaning của nó


    public void GeneralhandleSpeakButton(String language, String textToSpeak, String Voice) throws Exception {
        // !set người nói ở controller riêng
        //! hoặc không cần vì nếu ko set voice thì nó để mặc định khi chỉnh language
        VoiceRSS.language = language;
        VoiceRSS.Voice = Voice;
        // Start a new thread to speak the text ( un synchorize) lên web RSS
        new Thread(() -> {
            try {
                VoiceRSS.speakWord(textToSpeak);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

//    public void GeneralhandleSpeakButton (String language) throws Exception {
//
//        VoiceRSS.language = language;
//
//        new Thread(() -> {
//            try {
//                VoiceRSS.speakWord(selectedWord);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

    @FXML
    public void handleVietAnhButton() {
        VietAnh.setVisible(false);
        AnhViet.setVisible(true);
        Path = EVPath;
        htmlDictionary.clearWordList();  // Clear the entire word list
        htmlDictionary.loadFromFile(Path);
        searchBar.clear();
        listView.getItems().clear();
    }

    @FXML
    public void handleAnhVietButton() {
        AnhViet.setVisible(false);
        VietAnh.setVisible(true);
        Path = VEPath;
        htmlDictionary.clearWordList();  // Clear the entire word list
        htmlDictionary.loadFromFile(Path);
        searchBar.clear();
        listView.getItems().clear();
    }

    public void updateButtonsVisibility() {
        if (Path.equals(EVPath)) {
            VietAnh.setVisible(false);
            AnhViet.setVisible(true);
        } else if (Path.equals(VEPath)) {
            AnhViet.setVisible(false);
            VietAnh.setVisible(true);
        }
    }





}

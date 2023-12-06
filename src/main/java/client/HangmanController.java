package client;

import Game.Hangman;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class HangmanController    {

    private AnchorPane rootPane;
    public AnchorPane getRootPane() {
        return rootPane;
    }




    // !nhân vật người treo cổ
    @FXML
    private Rectangle Box;

    @FXML
    private Line Pole;

    @FXML
    private Line Pole2;

    @FXML
    private Line Pole3;

    @FXML
    private Pane base;

    @FXML
    private Pane human;

    @FXML
    private Line rope;

    // !từ đã đoán được và các nút để đoán
    @FXML
    private Text currText;

    @FXML
    private FlowPane alphabet;


    // !số lượng lần đoán đúng và sai
    private int mistakes;
    private int correct;

    // !chơi xong 1 lượt
    @FXML
    private Text correctAnswear;
    @FXML
    private Text status;


    // !độ khó trò chơi
    @FXML
    private MenuButton difficultyButton;
    @FXML
    private Button newGamebutton;

    @FXML
    private MenuItem easy;

    @FXML
    private MenuItem hard;

    private Hangman words;
    private boolean hintUsed = false;

    // TODO: KO RO VI SAO LAI CAN exception

    public HangmanController() throws FileNotFoundException {
    }
    // ?   _____

    // từ và đáp án
    private String myWord;
    private List<String> myLetters;
    private List<String> answer;

    String difficulty = "easy"; // default to easy

    private boolean hintAlertShown = false;

    public void initialize() {
        Box.setVisible(false);
        Pole.setVisible(false);
        Pole2.setVisible(false);
        Pole3.setVisible(false);
        base.setVisible(false);
        human.setVisible(false);
        rope.setVisible(false);


        mistakes=0;
        correct=0;


        try {
            words = new Hangman(difficulty);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        myWord = words.getRandomWord();
        myLetters = Arrays.asList(myWord.split(""));
        answer = Arrays.asList(new String[myLetters.size()*2]);
        for(int i=0; i<myLetters.size()*2; i++){
            if(i%2==0){
                answer.set(i, "_");
            }else{
                answer.set(i, " ");
            }
        }
        String res = String.join("", answer);
        currText.setText(res);
        status.setText("");
        correctAnswear.setText("");
        alphabet.setDisable(false);
        hintUsed = false;

    }

    @FXML
    public void selectDifficulty(ActionEvent event) {

        MenuItem selectedDifficulty = (MenuItem) event.getSource();
        difficultyButton.setText(selectedDifficulty.getText());

        difficulty = selectedDifficulty.getId(); // Assuming you set the id for menu items as the difficulty level


        try {

            words = new Hangman(difficulty);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        newGame();
    }


//    public void onClick(ActionEvent event){
//        String letter = ((Button)event.getSource()).getText();
//        ((Button) event.getSource()).setDisable(true);
//        if(myLetters.contains(letter)){
//            correct++;
//            int letterIndex = myLetters.indexOf(letter);
//            answer.set(letterIndex*2, letter);
//            String res = String.join("", answer);
//            currText.setText(res);
//            if(correct==myWord.length()){
//                status.setText("You Win!");
//                alphabet.setDisable(true);
//            }
//        }else{
//            mistakes++;
//            if(mistakes ==1) base.setVisible(true);
//            else if (mistakes ==2) Pole.setVisible(true);
//            else if (mistakes ==3) Pole2.setVisible(true);
//            else if (mistakes ==4) Pole3.setVisible(true);
//            else if (mistakes ==5) Box.setVisible(true);
//            else if (mistakes ==6) rope.setVisible(true);
//            else if (mistakes ==7){
//                human.setVisible(true);
//
//                status.setText("You Lose!");
//                correctAnswear.setText("The correct answear was " + myWord);
//                alphabet.setDisable(true);
//            }
//        }
//    }


    // !XỬ LÍ CHỨC NĂNG GAME MỚI
    public void newGame(){
        for(int i=0; i<26; i++){
            alphabet.getChildren().get(i).setDisable(false);
        }
        hintUsed = false;
        initialize();
    }





    //! xử lí CHỨC NĂNG HINT, LƯU Ý CHỈ ĐƯỢC SỬ DỤNG 1 LẦN
    public void hint(ActionEvent event) {
        // !chưa sử dụng hint, mistake < 7 và nhở hơn từ cần tìm
        if (!hintUsed && mistakes < 7 && correct < myWord.length() - 1) {

            int hiddenIndex = findFirstHiddenLetter();

            // Nếu có hidden letter, hiện ra
            if (hiddenIndex != -1) {
                answer.set(hiddenIndex, myLetters.get(hiddenIndex / 2));
                String res = String.join("", answer);
                currText.setText(res);
            } else {
                // nếu không có hidden letter hiện alert
                if (!hintAlertShown) {
                    showAlert("No Hidden Letter", "There are no hidden letters to reveal.");
                    hintAlertShown = true;
                }
                return; // Exit the method tránh xử lí thêm
            }

            // đếm mistake
            mistakes++;

            // xử lí số mạng
            updateUI();

            // Set hintUsed sang true
            hintUsed = true;
        } else {
            // nếu đã sử dụng hint thì hiện alert
            if (!hintAlertShown) {
                showAlert("Cannot Use Hint", "You only use hint once!");
                hintAlertShown = true;
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    // !hàm tìm từ đầu tiên của "từ cần đoán", DÀNH RIÊNG CHO PHƯƠNG THỨC "hint"
    private int findFirstHiddenLetter() {
        for (int i = 0; i < answer.size(); i += 2) {
            if (answer.get(i).equals("_")) {
                return i;
            }
        }
        return -1;
    }


    //! hàm xử lí số mạng tương ứng với mỗi lân true
    private void updateUI() {
        switch (mistakes) {
            case 1:
                base.setVisible(true);
                break;
            case 2:
                Pole.setVisible(true);
                break;
            case 3:
                Pole2.setVisible(true);
                break;
            case 4:
                Pole3.setVisible(true);
                break;
            case 5:
                Box.setVisible(true);
                break;
            case 6:
                rope.setVisible(true);
                break;
            case 7:
                human.setVisible(true);

                status.setText("You Lose!");
                correctAnswear.setText("The correct answer was " + myWord);
                alphabet.setDisable(true);
                break;
        }
    }


    // !hàm dành cho các nút alphabet
    public void onClick(ActionEvent event) {
        String letter = ((Button) event.getSource()).getText();
        ((Button) event.getSource()).setDisable(true);

        if (myLetters.contains(letter)) {
            for (int i = 0; i < myLetters.size(); i++) {
                if (myLetters.get(i).equals(letter)) {
                    answer.set(i * 2, letter);
                    correct++;
                }
            }

            String res = String.join("", answer);
            currText.setText(res);

            // Kiểm tra xem tất cả các chữ cái đã được đoán đúng hay chưa
            if (!res.contains("_")) {
                status.setText("You Win!");
                alphabet.setDisable(true);
            }
        } else {
            mistakes++;
            updateUI();
        }
    }


}

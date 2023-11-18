package client;// AudioPlayerController.java
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AudioPlayerController {

    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;

    @FXML
    private void initialize() {
        // Replace with the path to your MP3 file
        String audioFilePath = "F:\\Dictionary\\src\\main\\resources\\media\\audio.wav";
        try {
            audioFilePath = "file:///" + URLEncoder.encode(audioFilePath, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Media media = new Media(audioFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    @FXML
    private void playAudio() {
        mediaPlayer.play();
    }
}

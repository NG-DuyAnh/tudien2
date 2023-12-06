package API;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VoiceRSS {
    private static final String API_KEY = "64a2485a783742e99b3c49b00b268ad4";
    private static final String AUDIO_PATH = "src\\main\\resources\\media\\audio.wav";
    //F:\Dictionary\src\main\resources\media\audio.wav

    public static String Voice;

    public static String language ;

    public static double speed = 1;





    // !vì đây là static
    // !xử lí lấy thông tin từ api
    // !CÁC THUỘC TÍNH CỦA GIOỌNG NÓI Ở TRANG WEB https://www.voicerss.org/api/
    public static void speakWord(String word) throws Exception {
        VoiceProvider tts = new VoiceProvider(API_KEY);
        VoiceParameters params = new VoiceParameters(word, language);
        params.setBase64(false);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setVoice(Voice);
        params.setRate((int) Math.round(-2.9936 * speed * speed + 15.2942 * speed - 12.7612));

        byte[] voice = tts.speech(params);

        // Save the audio data to a file
        FileOutputStream fos = new FileOutputStream(AUDIO_PATH);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();

        // Play the audio using Java Sound API
        new Thread(() -> playAudio(voice)).start();
    }





    // !xử lí phát ra tiếng nói
    public static void playAudio(byte[] audioData) {
        try {
            // Chuyển đổi dữ liệu âm thanh từ byte array
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));

            // Lấy thông số của audio
            javax.sound.sampled.AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

            // Mở một đường dữ liệu âm thanh
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            // Đọc và chơi âm thanh từ input stream
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                sourceDataLine.write(buffer, 0, bytesRead);
            }

            // Đóng đường dữ liệu âm thanh khi kết thúc
            sourceDataLine.drain();
            sourceDataLine.close();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    //    private static void playAudio(byte[] audioData) throws LineUnavailableException, UnsupportedAudioFileException {
//        ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
//
//        AudioInputStream audioInputStream = null;
//        try {
//            audioInputStream = AudioSystem.getAudioInputStream(bais);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        javax.sound.sampled.AudioFormat format = audioInputStream.getFormat();
//
//        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
//
//        try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
//            line.open(format);
//            line.start();
//
//            int bufferSize = 1024;
//            byte[] buffer = new byte[bufferSize];
//
//            int bytesRead;
//            while (true) {
//                try {
//                    if (!((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1)) break;
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                line.write(buffer, 0, bytesRead);
//            }
//
//            line.drain();
//            line.stop();
//        }
//    }

    public static void main(String[] args) throws Exception {
        speakWord("xin chào");
    }
}

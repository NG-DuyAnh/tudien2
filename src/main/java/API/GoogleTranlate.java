package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranlate {

    public static String googleTranslate(String langFrom, String langTo, String text) throws IOException {

        // *APP SCRIPT  (ME(GMAIL) + everyone)
        //
        //
        // !(https://stackoverflow.com/questions/61739564/using-google-script-service-to-translate-text-return-encoded-text)
        String urlScript = "https://script.google.com/macros/s/AKfycby443DhvcZwlpZku2E8hqLm-Qd8YqdXQS_pAcSORIlyDLTC9DpTal1Ib0ZnIjz1lCKtQA/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlScript);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }




    // !TEST API
//    public static void main(String[] args) throws IOException {
//        String text = "aductor";
//        System.out.println("Translated text: \n" + googleTranslate("en", "vi", text));
//    }

}


package API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ThesaurusApi {


    public String word ;
    private static final String API_BASE_URL = "https://api.api-ninjas.com/v1/thesaurus";
    private static final String apiKey = "szRqvoNX7GWYv0xlrzDmnQ==v7GQMZ2TqDRR65wD";



    private ArrayList<String> getWordsFromJsonArray(JSONArray jsonArray) throws Exception {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            words.add(jsonArray.getString(i));
        }
        return words;
    }

    public ArrayList<String> getSynonyms(String word) throws Exception {
        JSONObject jsonResponse = getJsonResponse(word);
        JSONArray synonymsJson = jsonResponse.getJSONArray("synonyms");
        return getWordsFromJsonArray(synonymsJson);
    }

    public ArrayList<String> getAntonyms(String word) throws Exception {
        JSONObject jsonResponse = getJsonResponse(word);
        JSONArray antonymsJson = jsonResponse.getJSONArray("antonyms");
        return getWordsFromJsonArray(antonymsJson);
    }

    private JSONObject getJsonResponse(String word) throws Exception {
        String apiUrl = API_BASE_URL + "?word=" + word;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", apiKey);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return new JSONObject(response.toString());
    }

    public static void main(String[] args) throws Exception {
        // Replace YOUR_API_KEY with your actual API key


        // Create ThesaurusApi instance
        ThesaurusApi thesaurusApi = new ThesaurusApi();

        // Set the word for this instance
        thesaurusApi.word = "high church";

        // Call the getSynonyms method
        ArrayList<String> synonyms = thesaurusApi.getSynonyms(thesaurusApi.word);

        // Call the getAntonyms method
        ArrayList<String> antonyms = thesaurusApi.getAntonyms(thesaurusApi.word);

        // Print the results
        System.out.println("Synonyms: " + synonyms);
        System.out.println("Antonyms: " + antonyms);
    }
}

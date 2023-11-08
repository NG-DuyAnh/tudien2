package Base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class HTMLDictionary {

    private TreeMap<String, String> wordList = new TreeMap<>();


    public TreeMap<String, String> getWordList() {
        return wordList;
    }


    //lấy từ file anh việt
    public  void loadFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("<html>");
                if (parts.length >= 2) {
                    String word = parts[0];
                    String meaning = parts[1];
                    wordList.put(word, meaning);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void HTMLDictionary(String path){
//
//        loadFromFile(path);
//
//    }


}

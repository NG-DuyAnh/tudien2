package Base;

import java.io.*;
import java.util.Map;
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

    public void updateWordListtoHTMLfile (String path, TreeMap<String,String> tempList){
        try{
            File file = new File(path) ;
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String,String> entry : tempList.entrySet()){
                String word = entry.getKey();
                String meaning = entry.getValue();
                // Add HTML tags if not already present
                if (!meaning.startsWith("<html>")) {
                    meaning = "<html>" + meaning + "</html>";
                }

                writer.write(word + meaning + "\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateWord (String word,  String newMeaning) {
        if (wordList.containsKey(word)) {
            if (!newMeaning.startsWith("<html>")){
                newMeaning = "<html>" + newMeaning + "<html>" ;
            }
        }
        wordList.put(word, newMeaning);
    }

    public void removeWord(String word) {
        wordList.remove(word);
    }


    public void clearWordList() {
        wordList.clear();
    }


//    public void HTMLDictionary(String path){
//
//        loadFromFile(path);
//
//    }


        }


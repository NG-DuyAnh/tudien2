package Base;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class DictionaryManagement {


    // tạo 1 biến thuộc lớp Dictionary để có được wordList và đặt tên nó là dictionary


    Dictionary dictionary = new Dictionary();


    // nhập từ cmd


    public void insertFromCommandline() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("ban se nhap tu lien tuc cho toi khi input la '1'");
        try {
            while (true) {

                System.out.println("nhap tu tieng Anh");
                String target = reader.readLine();
                if (dictionary.wordList.containsKey(target)) {
                    System.out.println("da co tu nay");
                    continue;
                }
                if (target.compareTo("1") == 0) {
                    System.out.println("ngung nhap" + "\n");
                    break;
                }
                System.out.println("nhap meaning cua tu");
                String explain = reader.readLine();
                if (explain.compareTo("1") == 0) {
                    System.out.println("ngung nhap" + "\n");
                    break;
                }

                dictionary.wordList.put(target, explain);
                System.out.println("nhap tu thanh cong" + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void insertFromCommandline(String target, String explain) {
//        dictionary.wordList.(target, explain);
//    }
//    public String getWordExplain(String word_target) {
//        if (dictionary.containsKey(word_target)) {
//            return dictionary.get(word_target) + "\n";
//        } else {
//            return "There is no such word.\n";
//        }
//    }
    public void removeWord(String word) {
        if (dictionary.wordList.containsKey(word)) {
            dictionary.wordList.remove(word);
            System.out.println("xoa thanh cong");
        }else{
            System.out.println("ko co tu de xoa");
        }
    }

    public void updateWord(String word){
        if(dictionary.wordList.containsKey(word)){
            Scanner scanner = new Scanner(System.in);
            System.out.println("co tu trong danh sach! hay nhap tu can thay doi");

            String target = scanner.nextLine();
            System.out.println("hay nhap nghia can thay doi");
            String explain = scanner.nextLine();
            dictionary.wordList.remove(word);
            dictionary.wordList.put(target,explain);
        }else{
            System.out.println("khong co tu de thay doi");
        }
    }

    public String getWordExplain(String word) {
        if (dictionary.wordList.containsKey(word)) {
            return dictionary.wordList.get(word) + "\n";
        } else {
            return "There is no such word.\n";
        }
    }


    //nhập từ file







    public String dictionaryLookup(String target) {
        if (dictionary.wordList.containsKey(target)) {
            return target + " mean:" +  dictionary.wordList.get(target);
        } else {
            return "ko tim thay";
        }
    }

    public void insertFromFile(String filepath) {
        try {


            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t", 2);
                if (parts.length >= 2) {
                    String word_target = parts[0];
                    String word_explain = parts[1];
                    dictionary.wordList.put(word_target, word_explain);
                } else {
                    System.out.println("ignoring line: " + line);
                    
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dictionaryExportToFile() {
        String filename = "F:/DIC/dictionary.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, String> entry : dictionary.wordList.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("export thanh cong");


        } catch (IOException e) {
            System.out.println("loi export");

            e.printStackTrace();
        }
    }

}

package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private ArrayList<String> words;
    private String[] letters;


    public Hangman() throws FileNotFoundException {
        words = new ArrayList<>();
        Scanner sc = new Scanner(new File("C:\\Users\\Admin\\Downloads\\Hangman-main\\Hangman-main\\Words.txt"));
        while(sc.hasNextLine()) words.add(sc.nextLine());
    }

    public String getRandomWord(){
        return words.get(new Random().nextInt(words.size())).toUpperCase();
    }
}

package Base;

import Game.MultiQuiz;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DictionaryCommandLine {

    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public String showAllWords() {
        TreeMap<String, String> wordList = dictionaryManagement.dictionary.wordList;
        int i = 1;
        StringBuilder string = new StringBuilder();

        System.out.println("cac tu tron tu dien" + "\n");
        string.append("No  | English                        | Vietnamese\n");
        for (Map.Entry<String, String> entry : wordList.entrySet()) {

            String line = String.format("%-3d | %-30s | %-20s%n", i++, entry.getKey(), entry.getValue());
            string.append(line);

        }
        return string.toString();
    }

    public void dictionarySearcher(String prefix) {
        TreeMap<String, String> wordList = dictionaryManagement.dictionary.wordList;
        System.out.println("Các từ bắt đầu bằng '" + prefix + "':");
        for (Map.Entry<String, String> entry : wordList.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }


    public void dictionaryBasic() {

        System.out.println("this is a basic dictionary");
        dictionaryManagement.insertFromCommandline();
        System.out.println(this.showAllWords());


//        try {
//            while (true) {
//                System.out.println("nhap tu tieng Anh");
//                String target = reader.readLine();
//                System.out.println("nhap meaning cua tu");
//                String explain = reader.readLine();
//                if (target.compareTo("1") == 0 || explain.compareTo("1") == 0) {
//                    showAllWords();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void dictionaryAdvance() {
        while (true) {
            System.out.println("this is a advance dictionary");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");

            System.out.println("[7] Import from file");
            System.out.println("[8] Export to file");
            System.out.println("[9] Game");
            System.out.print("Your action: ");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            switch (action) {
                case "0":
                    System.out.println("exiting");
                    return;
                case "1":
                    // insert from commandline "Add"
                    dictionaryManagement.insertFromCommandline();
                    break;

                case "2":
                    // "remove"
                    System.out.println("enter word to remove");
                    String word2 = scanner.nextLine();
                    dictionaryManagement.removeWord(word2);
                    break;
                case "3":
                    System.out.println("enter word to change");
                    String word3 = scanner.nextLine();
                    dictionaryManagement.updateWord(word3);
                    break;


                case "4":
                    // Display all words
                    System.out.println(this.showAllWords());
                    break;

                case "5":
                    //lookup
                    System.out.println("enter word to lôkup");
                    String word5 = scanner.nextLine();
                    System.out.println(dictionaryManagement.dictionaryLookup(word5));
                    break;
                case "6":
                    //search
                    System.out.println("enter word to search");
                    String word6 = scanner.nextLine();
                    this.dictionarySearcher(word6);
                    break;
                case "7":
                    //import from file
                    System.out.println("name of the file");
                    String word7 = scanner.nextLine();
                    dictionaryManagement.insertFromFile("F:\\DIC\\src\\" + word7);
                    System.out.println("insert successful");
                    break;

                case "8":
                    //export to file
                    System.out.println("exporting");
                    dictionaryManagement.dictionaryExportToFile();
                    break;
                case "9":
                    // playgame
                    System.out.println("Pick your game");
                    MultiQuiz game = new MultiQuiz();
                    game.gameFromFile("F:\\Dictionary\\src\\main\\java\\Game\\MultiQuizDB.txt");
                    break;
                default:
                    System.out.println("Invalid action. Please enter a number between 0 and 9.");
                    break;
            }
        }
    }
}

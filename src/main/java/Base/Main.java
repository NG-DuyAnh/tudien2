package Base;

import Game.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DictionaryManagement dicMana = new DictionaryManagement();
        DictionaryCommandLine dicCmd = new DictionaryCommandLine();
        dicMana.insertFromFile("F:\\DIC\\src\\text.txt");
        Scanner scanner = new Scanner(System.in);


        String action = scanner.nextLine();
        if(action.equalsIgnoreCase("game")){
            MultiQuiz game = new MultiQuiz();
            game.gameFromFile("F:\\Dictionary\\src\\main\\java\\Game\\MultiQuizDB.txt");

        }
        if (action.equalsIgnoreCase("basic")) {
            dicCmd.dictionaryBasic();
        }
        if (action.equalsIgnoreCase("advance")) {
            dicCmd.dictionaryAdvance();
        }
//        while (true) {
//            System.out.println("Welcome to My Application!");
//            System.out.println("[0] Exit");
//            System.out.println("[1] Add");
//            System.out.println("[2] Remove");
//            System.out.println("[3] Update");
//            System.out.println("[4] Display");
//            System.out.println("[5] Lookup");
//            System.out.println("[6] Search");
//            System.out.println("[7] Game");
//            System.out.println("[8] Import from file");
//            System.out.println("[9] Export to file");
//            System.out.print("Your action: ");
//
//            String action = scanner.nextLine();
//            switch (action) {
//                case "0":
//                    return;
//                case "1":
//                    // insert from commandline "Add"
//                    System.out.print("Enter the English word: ");
//                    String English = scanner.nextLine();
//                    System.out.print("Enter the Vietnamese explanation: ");
//                    String explain = scanner.nextLine();
//                    System.out.println("insert succesful");
//                    dicMana.insertFromCommandline(English, explain);
//                    break;
//
//                case "2":
//                    // "remove"
//                    System.out.println("enter the word:   ");
//                    String target2 = scanner.nextLine();
//                    dicMana.removeWord(target2);
//                    System.out.println("removed succesful");
//
//                case "3":
//
//                case "4":
//                    // Display all words
//                    System.out.println(dicCmd.showAllWords(dicMana));
//                    break;
//                // TODO: handle other actions
//                default:
//                    System.out.println("Invalid action. Please enter a number between 0 and 9.");
//                    break;
//            }

    }
}
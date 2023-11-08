package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MultiQuiz {

    public void gameFromFile(String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            int score = 0;
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            while (true) {
                int randomQuestionNumber = random.nextInt(lines.size());

                String[] parts = lines.get(randomQuestionNumber).split(", ", 7);
                String question = parts[1];
                String optionA = parts[2];
                String optionB = parts[3];
                String optionC = parts[4];
                String optionD = parts[5];
                String correctAnswer = parts[6];

                System.out.println(question);
                System.out.println("a. " + optionA);
                System.out.println("b. " + optionB);
                System.out.println("c. " + optionC);
                System.out.println("d. " + optionD);

                System.out.print("Your answer (or 'q' to quit): ");
                String answer = scanner.nextLine();

                switch (answer) {
                    case "a":
                    case "b":
                    case "c":
                    case "d":
                        if (answer.equalsIgnoreCase(correctAnswer)) {
                            System.out.println("Correct!!!");
                            score++;
                        } else {
                            System.out.println("Wrong answer!!!!");
                        }
                        break;
                    case "q":
                        reader.close();
                        System.out.println("Your score: " + score);
                        return; // Kết thúc trò chơi
                    default:
                        System.out.println("Invalid choice. Please enter 'a', 'b', 'c', 'd', or 'q'.");

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//    public void gameFromFile(String filepath) {
//        try {
//
//
//            int score = 0;
//
//            while (true) {
//                BufferedReader reader = new BufferedReader(new FileReader(filepath)); // Mở lại tệp tin sau mỗi câu hỏi
//                int quizNumber = 0;
//                String line;
//                int randomQuestionNumber = (int) Math.floor(Math.random() * 100) + 1;
//                while ((line = reader.readLine()) != null) {
//                    quizNumber++;
//                    if (quizNumber == randomQuestionNumber) {
//                        String[] parts = line.split(", ", 7);
//                        String question = parts[1];
//                        String optionA = parts[2];
//                        String optionB = parts[3];
//                        String optionC = parts[4];
//                        String optionD = parts[5];
//                        String correctAnswer = parts[6];
//
//                        System.out.println(question);
//                        System.out.println("a. " + optionA);
//                        System.out.println("b. " + optionB);
//                        System.out.println("c. " + optionC);
//                        System.out.println("d. " + optionD);
//
//                        Scanner scanner = new Scanner(System.in);
//                        System.out.print("Your answer (or 'q' to quit): ");
//                        String answer = scanner.nextLine();
//
//
//                        switch (answer) {
//                            case "a":
//                            case "b":
//                            case "c":
//                            case "d":
//                                if (answer.equalsIgnoreCase(correctAnswer)) {
//                                    System.out.println("Correct!!!");
//                                    score++;
//                                } else {
//                                    System.out.println("Wrong answer!!!!");
//                                }
//                                break;
//                            case "q":
//                                reader.close();
//                                System.out.println("Your score: " + score);
//                                return; // Kết thúc trò chơi
//                            default:
//                                System.out.println("Invalid choice. Please enter 'a', 'b', 'c', 'd', or 'q'.");
//                                break;
//                        }
////                        if (answer.equalsIgnoreCase(correctAnswer)) {
////                            System.out.println("Correct!!!");
////                            score++;
////                        } else if (answer.equalsIgnoreCase("q")) {
////                            reader.close(); // Đóng tệp tin
////                            System.out.println("Your score: " + score);
////                            return; // Kết thúc trò chơi
////                        } else {
////                            System.out.println("Wrong answer!!!!");
////                        }
//
//                        break; // Để thoát sau khi xử lý câu hỏi
//                    }
//                }
//
//                reader.close(); // Đóng tệp tin
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


//    public void gameFromFile(String filepath) {
//        try {
//            int randomQuestionNumber = (int) Math.floor(Math.random() * 100) + 1;
//
//            BufferedReader reader = new BufferedReader(new FileReader(filepath));
//            int quizNumber = 0;
//            int score = 0;
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//
//                quizNumber++;
//                if (quizNumber == 2) {
//                    String[] parts = line.split(", ", 7);
//                    String question = parts[1];
//                    String optionA = parts[2];
//                    String optionB = parts[3];
//                    String optionC = parts[4];
//                    String optionD = parts[5];
//                    String correctAnswer = parts[6];
//
//                    System.out.println(question);
//                    System.out.println("a. " + optionA);
//                    System.out.println("b. " + optionB);
//                    System.out.println("c. " + optionC);
//                    System.out.println("d. " + optionD);
//
//                    Scanner scanner = new Scanner(System.in);
//                    System.out.print("Your answer (or 'q' to quit): ");
//                    String answer = scanner.nextLine();
//
//                    if (answer.equalsIgnoreCase(correctAnswer)) {
//                        System.out.println("Correct!!!");
//                        score++;
//                    } else if (answer.equalsIgnoreCase("q")) {
//                        reader.close();
//                        System.out.println("Your score: " + score);
//
//                        break; // Quit the game
//                    } else {
//                        System.out.println("Wrong answer!!!!");
//                    }
//                }
//
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

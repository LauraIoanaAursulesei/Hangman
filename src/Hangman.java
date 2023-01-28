import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(new File("src/wordList.txt "));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random rand = new Random();

        String word = words.get(rand.nextInt(words.size()));

        List<Character> playerGuesses = new ArrayList<>();


        int wrongCount = 0;
        while (true) {

            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lost!");
                System.out.println("The word was " + word);
                break;
            }

            if (printWordState(word, playerGuesses)) {
                System.out.println("You win!");
                break;
            }
            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }
        }
    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" _______");
        System.out.println(" I     I");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println();
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" I");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("_");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}

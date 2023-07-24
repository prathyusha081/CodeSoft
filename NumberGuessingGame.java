import java.util.Scanner;
import java.util.Random;

/*
 * using Scanner class for input output statements that are required
 * for the messages to be displayed to the user 
 * for the result to be displayed 
 * input from user to guess the number
 * Random is used to generate a random number as the key concept of the task
 */


 /*
  * we name our class as Number Guessing Game which clearly tells about the main idea
  * this class uses random function to create a random value
  */

public class NumberGuessingGame {
    public static int generateRandomNumber(int minRange, int maxRange) {
        Random random = new Random();
        return random.nextInt(maxRange - minRange + 1) + minRange;
    }
/*
 * This function allows user to guess the number which was generated randomly
 * we immediately verify if it is a valid input
 */
    public static int getUserGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getUserGuess();
        }
    }
    /*
     * here we will compare the user guess and generated number and provide feedback if it is correct or if its too high or low
     */

    public static String compareGuess(int actualNumber, int userGuess) {
        if (userGuess < actualNumber) {
            return "Too low";
        } else if (userGuess > actualNumber) {
            return "Too high";
        } else {
            return "Correct";
        }
    }

    public static int playGame(int minRange, int maxRange, int attempts) {
        int randomNumber = generateRandomNumber(minRange, maxRange);
        System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange + ". Can you guess what it is?");

        for (int attempt = 1; attempt <= attempts; attempt++) {
            int userGuess = getUserGuess();
            String result = compareGuess(randomNumber, userGuess);

            if (result.equals("Correct")) {
                System.out.println("Congratulations! You guessed the correct number " + randomNumber + " in " + attempt + " attempts!");
                return 1;
            } else {
                System.out.println(result + ". Try again.");
            }
        }

        System.out.println("Sorry, you've run out of attempts. The number was " + randomNumber + ".");
        return 0;
    }

    public static void main(String[] args) {
        int minRange = 1;
        int maxRange = 100;
        int attempts = 5;
        int totalScore = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!");
        boolean playAgain = true;

        while (playAgain) {
            totalScore += playGame(minRange, maxRange, attempts);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        }

        System.out.println("Your total score is " + totalScore + ".");
    }
}

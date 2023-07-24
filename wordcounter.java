import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class wordcounter {
    private static Set<String> stopWords = new HashSet<>(Arrays.asList(
        "a", "an", "the", "in", "on", "at", "and", "or", "is", "are", "was", "were", "for", "to", "of"
    ));

    public static String readTextFromFile(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        }
        return content.toString();
    }

    public static int countWords(String text) {
        String[] words = text.split("\\s+|(?=\\p{Punct})|(?<=\\p{Punct})");
        int count = 0;
        for (String word : words) {
            if (!word.isEmpty() && !stopWords.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Word Counter!");

        System.out.print("Do you want to enter text (T) or provide a file (F)? Enter 'T' or 'F': ");
        String choice = scanner.nextLine().trim();

        String text = "";
        try {
            if (choice.equalsIgnoreCase("T")) {
                System.out.println("Enter your text (type 'END' on a new line to finish):");
                String line;
                while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
                    text += line + "\n";
                }
            } else if (choice.equalsIgnoreCase("F")) {
                System.out.print("Enter the file path: ");
                String filePath = scanner.nextLine().trim();
                text = readTextFromFile(filePath);
            } else {
                System.out.println("Invalid choice. Please enter 'T' or 'F'.");
                return;
            }

            int wordCount = countWords(text);
            System.out.println("Word count: " + wordCount);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path and try again.");
        }
    }
}

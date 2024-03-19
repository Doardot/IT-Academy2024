package aplication;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BettingSaveHistory {
    public static void saveToFile(String text, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true); // Set true for append mode
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(text);  // New line
            printWriter.println();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}

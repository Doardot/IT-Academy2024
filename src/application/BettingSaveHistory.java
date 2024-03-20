package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BettingSaveHistory {
    /**
     * This method is used to save a string to a file.
     * It opens the file in append mode, which means that it will add the new text to the end of the file,
     * rather than overwriting the existing content.
     *
     * There are two txt files in the project: BetHistory.txt and Awards.txt.
     * BetHistory.txt is used to save the bets made by the players.
     * Awards.txt is used to save the awards that can be won by the players.
     */
    public static void saveToFile(String text, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true); // Set true to append mode
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(text);
            printWriter.println();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro na gravação do arquivo: " + e.getMessage());
        }
    }
}

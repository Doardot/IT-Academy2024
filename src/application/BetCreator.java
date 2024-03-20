package application;

import data.*;
import java.util.HashSet;

public class BetCreator {
    private int betRegistration;

    public BetCreator() {
        this.betRegistration = 999;
    }

    /**
     * This method is used to create a new specific bet.
     * 1- It increments the bet registration number;
     * 2- Creates a new Bet object with the provided parameters;
     * 3- Saves the bet to a file;
     * 4- Then returns the new Bet object.
     */
    public Bet newSpecificBet(String bettorName, String cpf, HashSet<Integer> wageredNumbers) {
        betRegistration++;
        Bet bet = new Bet(bettorName, cpf, betRegistration, wageredNumbers);
        BettingSaveHistory.saveToFile(bet.toString(), "BetHistory.txt");
        return bet;
    }

    /**
     * It works the same way as the newSpecificBet method, but it creates a new
     * random bet.
     */
    public Bet newRandomBet(String bettorName, String cpf, HashSet<Integer> wageredNumbers) {
        betRegistration++;
        Bet bet = new Bet(bettorName, cpf, betRegistration, wageredNumbers);
        BettingSaveHistory.saveToFile(bet.toString(), "BetHistory.txt");
        return bet;
    }

    public void resetBetRegistration() {
        this.betRegistration = 999;
    }
}

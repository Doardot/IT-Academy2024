package aplication;

import data.*;
import java.util.HashSet;

public class BetCreator {
    private int betRegistration;

    public BetCreator() {
        this.betRegistration = 999;
    }

    public Bet newSpecificBet(String bettorName, int CPF, HashSet<Integer> wageredNumbers) {
        betRegistration++;
        Bet bet = new Bet(bettorName, CPF, betRegistration, wageredNumbers);
        BettingSaveHistory.saveToFile(bet.toString(), "BetHistory.txt");
        return bet;
    }

    public Bet newRandomBet(String bettorName, int CPF, HashSet<Integer> wageredNumbers) {
        betRegistration++;
        Bet bet = new Bet(bettorName, CPF, betRegistration, wageredNumbers);
        BettingSaveHistory.saveToFile(bet.toString(), "BetHistory.txt");
        return bet;
    }

    public void resetBetRegistration() {
        this.betRegistration = 999;
    }
}

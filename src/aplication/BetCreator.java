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
        return new Bet(bettorName, CPF, betRegistration, wageredNumbers);
    }

    public Bet newRandomBet(String bettorName, int CPF, HashSet<Integer> wageredNumbers) {
        betRegistration++;
        return new Bet(bettorName, CPF, betRegistration, wageredNumbers);
    }

    public void resetBetRegistration() {
        this.betRegistration = 999;
    }
}

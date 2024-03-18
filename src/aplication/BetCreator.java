package aplication;

import data.*;

public class BetCreator {
    private BetRegistry betRegistry;
    private Bets bets;
    private int betRegistration = 999;

    public BetCreator(BetRegistry betRegistry, Bets bets) {
        this.betRegistry = betRegistry;
        this.bets = bets;
    }

    public Bet newBet(String bettorName, int CPF, int option) {
        Bet a = null;
//        try {
            betRegistration++;
            if (option == 1) {
                a = bets.newSpecificBet(bettorName, CPF, betRegistration);
            } else if (option == 2) {
                a = bets.newRandomBet(bettorName, CPF, betRegistration);
            }
            betRegistry.addBets(a);
//        } catch (Exception e) {
//            System.out.println("Ocorreu um erro ao processar a nova aposta: " + e.getMessage());
//        }
        return a;
    }
}

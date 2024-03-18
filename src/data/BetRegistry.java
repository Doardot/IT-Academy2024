package data;

import java.util.ArrayList;

public class BetRegistry {
    private ArrayList<Bet> bets;

    public BetRegistry() {
        bets = new ArrayList<>();
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void addBets(Bet a) {
        bets.add(a);
    }

}

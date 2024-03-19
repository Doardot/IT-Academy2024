package data;

import java.util.ArrayList;

public class BetRegistry {
    private final ArrayList<Bet> bets;
    private boolean betsOpen;

    public BetRegistry() {
        bets = new ArrayList<>();
        this.betsOpen = true;
    }

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void addBets(Bet bet) {
        if(betsOpen) {
            this.bets.add(bet);
        } else {
            //
        }
    }

    public void closeBets() {
        this.betsOpen = false;
    }

    public void resetBets() {
        this.bets.clear();
        this.betsOpen = true;
    }

}

package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Bet {
    private final String bettorName;
    private final int CPF;
    private final int betRegistration;
    private final HashSet<Integer> wagaredNumbers;

    public Bet(String bettorName, int CPF, int betRegistration, HashSet<Integer> wagaredNumbers) {
        this.bettorName = bettorName;
        this.CPF = CPF;
        this.betRegistration = betRegistration;
        this.wagaredNumbers = wagaredNumbers;
    }

    public String getBettorName() {
        return bettorName;
    }

    public List<Integer> getWagaredNumbers() {
        return new ArrayList<>(wagaredNumbers);
    }

    public String toString() {
        return "Nome do apostador: " + bettorName +
                "\nCPF: " + CPF +
                "\nRegistro da aposta: " + betRegistration +
                "\nNúmeros apostados: " + wagaredNumbers.toString();
    }
}

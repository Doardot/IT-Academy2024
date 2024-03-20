package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Bet {
    private final String bettorName;
    private final String cpf;
    private final int betRegistration;
    private final HashSet<Integer> wageredNumbers;

    public Bet(String bettorName, String cpf, int betRegistration, HashSet<Integer> wageredNumbers) {
        this.bettorName = bettorName;
        this.cpf = cpf;
        this.betRegistration = betRegistration;
        this.wageredNumbers = wageredNumbers;
    }

    public String getBettorName() {
        return bettorName;
    }

    public List<Integer> getWageredNumbers() {
        return new ArrayList<>(wageredNumbers);
    }

    public String toString() {
        return "Nome do apostador: " + bettorName +
                "\nCPF: " + cpf +
                "\nRegistro da aposta: " + betRegistration +
                "\nNúmeros apostados: " + wageredNumbers.toString();
    }
}

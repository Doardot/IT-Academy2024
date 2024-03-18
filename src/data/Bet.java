package data;

import java.util.ArrayList;

public class Bet {
    private String bettorName;
    private int CPF;
    private int betRegistration;
    private ArrayList<Integer> wagaredNumbers;

    public Bet(String bettorName, int CPF, int betRegistration, ArrayList<Integer> wagaredNumbers) {
        this.bettorName = bettorName;
        this.CPF = CPF;
        this.betRegistration = betRegistration;
        this.wagaredNumbers = wagaredNumbers;
    }

    public String getBettorName() {
        return bettorName;
    }

    public int getCPF() {
        return CPF;
    }

    public int getBetRegistration() {
        return betRegistration;
    }

    public ArrayList<Integer> getWagaredNumbers() {
        return wagaredNumbers;
    }

    public String toString() {
        return "Nome do apostador: " + bettorName +
                "\nCPF: " + CPF +
                "\nRegistro da aposta: " + betRegistration +
                "\nNúmeros apostados: " + wagaredNumbers.toString();
    }
}

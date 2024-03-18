package aplication;

import data.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Bets {
    private Scanner in;

    public Bets() {
        this.in = new Scanner(System.in);
    }

    public Bet newSpecificBet(String bettorName, int CPF, int betRegistration) {
        System.out.println("Digite os números apostados: ");
        ArrayList<Integer> wagaredNumbers = new ArrayList<>();
        // int numeroSelecionado;
        for (int i = 0; i < 5; i++) {
            // while (!in.hasNextInt() && (numeroSelecionado = in.nextInt()) < 1 ||
            // numeroSelecionado > 50) {
            while (!in.hasNextInt()) { // validar numeros entre 1 a 50
                System.out.println("Número inválido. Digite novamente: ");
                in.nextLine();
            }
            wagaredNumbers.add(in.nextInt());
        }
        return new Bet(bettorName, CPF, betRegistration, wagaredNumbers);
    }

    public Bet newRandomBet(String bettorName, int CPF, int betRegistration) {
        ArrayList<Integer> wagaredNumbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            wagaredNumbers.add((int) (Math.random() * 50 + 1));
        }
        return new Bet(bettorName, CPF, betRegistration, wagaredNumbers);
    }
}

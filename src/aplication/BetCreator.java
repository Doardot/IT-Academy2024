package aplication;

import data.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BetCreator {
    private int betRegistration = 999;
    private Scanner in;

    public BetCreator() {
        this.in = new Scanner(System.in);
    }

    public Bet newSpecificBet(String bettorName, int CPF) {
        HashSet<Integer> wageredNumbers = new HashSet<>();
        System.out.println("Digite os números apostados: ");

        while (wageredNumbers.size() < 5) {
            try {
                int number = in.nextInt();
                if (number < 1 || number > 50) {
                    System.out.println("Número inválido. Digite um número entre 1 a 50: ");
                } else if (!wageredNumbers.add(number)) {
                    System.out.println("Número já apostado. Digite novamente: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro: ");
                in.next(); // discard the non-integer input
            }
        }
        betRegistration++;
        return new Bet(bettorName, CPF, betRegistration, wageredNumbers);
    }

    public Bet newRandomBet(String bettorName, int CPF) {
        HashSet<Integer> wageredNumbers = new HashSet<>();
        while (wageredNumbers.size() < 5) {
            int randomNumber = (int) (Math.random() * 50 + 1);
            wageredNumbers.add(randomNumber);
        }
        betRegistration++;
        return new Bet(bettorName, CPF, betRegistration, wageredNumbers);
    }

    public void resetBetRegistration() {
        this.betRegistration = 999;
    }
}

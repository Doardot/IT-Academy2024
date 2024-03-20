package application;

import data.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LotteryUserInterface {
    private final Scanner in;
    private final BetRegistry betRegistry;
    private final BetCreator betCreator;
    private final LotteryRunner lotteryRunner;
    private final Awards awards;


    public LotteryUserInterface() {
        this.in = new Scanner(System.in);
        this.betRegistry = new BetRegistry();
        this.betCreator = new BetCreator();
        this.lotteryRunner = new LotteryRunner(betRegistry, betCreator);
        this.awards = new Awards();

        menu();
    }

    public void menu() {
        int option;
        do {
            System.out.println("0 - Sair");
            System.out.println("1 - Nova aposta");
            System.out.println("2 - Conferir lista de apostas");
            System.out.println("3 - Executar sorteio");
            System.out.println("4 - Checar premiação");
            System.out.print("Digite a opção desejada: ");

            while(true) {
                try {
                    option = in.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Digite um número entre 0 a 4: ");
                    in.next();
                }
            }

            switch (option) {
                case 0:
                    break;
                case 1:
                    newBet();
                    break;
                case 2:
                    betList();
                    break;
                case 3:
                    System.out.println("Você deseja executar o sorteio? Digite 's' para confirmar. ");
                    boolean confirmaSorteio = in.next().equalsIgnoreCase("s");
                    if (confirmaSorteio) {
                        lotteryRunner.runLotteryDraw();
                    }
                    break;
                case 4:
                    System.out.println("Os prêmios concorridos são: ");
                    System.out.println(awards.toString());
                    break;
                default:
                    System.out.println("Opção inválida. Digite um número entre 0 a 4: ");
            }
        } while (option != 0);
    }

    /**
     * This method is used to create a new bet.
     * 1- It prompts the bettor to enter their name and CPF. It ensures the CPF is a non-negative integer;
     * 2- Then asks the user whether they want to bet on specific numbers or have the system choose numbers randomly;
     * 3- If the bettor chooses to bet on specific numbers, it prompts the bettor to enter 5 numbers;
     * 4- If the bettor chooses to have the system choose numbers randomly, it generates 5 random numbers;
     * 5- In both cases, the numbers are limited to the range 1-50;
     * 6- After the bet is created, it is added to the bet registry.
     */
    public void newBet() {
        try {
            System.out.println("Digite o nome do apostador: ");
            in.nextLine(); // Consume the newline character left by the previous nextInt() call
            String bettorName = in.nextLine();
            System.out.println("Digite o CPF do apostador: ");
            String cpf;
            while (true) {
                try{
                    cpf = in.next();
                    if (cpf.length() != 11) {
                        throw new InputMismatchException(); // Throw exception if CPF is not 11 digits long
                    }
                    for(char c : cpf.toCharArray()) {
                        if (!Character.isDigit(c)) {
                            throw new InputMismatchException(); // Throw exception if CPF contains non-digit characters
                        }
                    }
                    break;
                } catch (InputMismatchException e) { // Catch exception if input is not an integer
                    System.out.println("CPF inválido. Digite um número inteiro de 11 dígitos: ");
                }
            }

            System.out.println(
                    "Você deseja apostar em números específicos ou deseja que o sistema escolha aleatoriamente? ");
            System.out.println("1 - Números específicos");
            System.out.println("2 - Aleatório");
            System.out.print("Digite a opção desejada: ");
            int option;
            while (true) {
                try {
                    option = in.nextInt();
                    if (option != 1 && option != 2) {
                        throw new InputMismatchException(); // Throw exception if option is not 1 or 2
                    }
                    Bet a;
                    HashSet<Integer> wageredNumbers = new HashSet<>();
                    if (option == 1) {
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
                                in.next(); // Discard the non-integer input
                            }
                        }
                        a = betCreator.newSpecificBet(bettorName, cpf, wageredNumbers);
                    } else {
                        while (wageredNumbers.size() < 5) {
                            int randomNumber = (int) (Math.random() * 50 + 1);
                            wageredNumbers.add(randomNumber);
                        }
                        a = betCreator.newRandomBet(bettorName, cpf, wageredNumbers);
                    }
                    betRegistry.addBets(a);
                    break;
                } catch (InputMismatchException e) { // Catch exception if input is not an integer
                    System.out.println("Opção inválida. Digite novamente: ");
                    in.nextLine();
                }
            }
        } catch (Exception e) { // Catch any other exception
            System.out.println("Ocorreu um erro ao processar a nova aposta: " + e.getMessage());
        }
    }

    public void betList() {
        for (Bet a : betRegistry.getBets()) {
            System.out.println("\n" + a.toString());
        }
    }
}
package aplication;

import data.*;

import java.util.Arrays;
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

        usuariosDeTeste();
        menu();
    }

    public void usuariosDeTeste() {
        HashSet<Integer> numerosApostadosMais5 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,
                33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50));
        Bet a = new Bet("Poggers", 123456789, 1, numerosApostadosMais5);
        Bet b = new Bet("Ana", 987654321, 2, numerosApostadosMais5);

        betRegistry.addBets(a);
        betRegistry.addBets(b);
    }

    public void menu() {
        int option;
        do {
            System.out.println("0 - Sair");
            System.out.println("1 - Nova aposta");
            System.out.println("2 - Conferir lista de apostas");
            System.out.println("3 - Executar sorteio");
            System.out.println("4 - Checar premiação");
            System.out.print("Digite a opcao desejada: ");

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

    public void newBet() {
        try {
            System.out.println("Digite o nome do apostador: ");
            String bettorName = in.next();
            System.out.println("Digite o CPF do apostador: ");
            int CPF;
            while (true) {
                try{
                    CPF = in.nextInt();
                    if (CPF < 0) {
                        throw new InputMismatchException(); // throw exception if number is negative
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("CPF inválido. Digite um número inteiro: ");
                    in.next();
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
                        throw new InputMismatchException(); // throw exception if option is not 1 or 2
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
                                in.next(); // discard the non-integer input
                            }
                        }
                        a = betCreator.newSpecificBet(bettorName, CPF, wageredNumbers);
                    } else {
                        while (wageredNumbers.size() < 5) {
                            int randomNumber = (int) (Math.random() * 50 + 1);
                            wageredNumbers.add(randomNumber);
                        }
                        a = betCreator.newRandomBet(bettorName, CPF, wageredNumbers);
                    }
                    betRegistry.addBets(a);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Digite novamente: ");
                    in.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao processar a nova aposta: " + e.getMessage());
        }
    }

    public void betList() {
        for (Bet a : betRegistry.getBets()) {
            System.out.println("\n" + a.toString());
        }
    }
}
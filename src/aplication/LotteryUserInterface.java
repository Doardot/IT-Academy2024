package aplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import data.*;

public class LotteryUserInterface {
    private Scanner in = null;
    private BetRegistry betRegistry;
    private BetCreator betCreator;
    private Bets bets;
    private LotteryRunner lotteryRunner;


    public LotteryUserInterface() {
        this.in = new Scanner(System.in);
        this.betRegistry = new BetRegistry();
        this.bets = new Bets();
        this.betCreator = new BetCreator(betRegistry, bets);
        this.lotteryRunner = new LotteryRunner(betRegistry);

        initializeTestObjects();
        menu();
    }

    //CLASSE PARA GERAR OBJETOS DE TESTE - SERÁ APAGADO NA VERSÃO FINAL
    private void initializeTestObjects() {
        // Array de teste
        ArrayList<Integer> numerosApostados5 = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int randomNumber = (int) (Math.random() * 50 + 1); // generates a random number between 1 and 50
            numerosApostados5.add(randomNumber);
        }
        ArrayList<Integer> numerosApostadosMais5 = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            int randomNumber = (int) (Math.random() * 50 + 1); // generates a random number between 1 and 50
            numerosApostadosMais5.add(randomNumber);
        }
        ArrayList<Integer> numerosApostados25 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));

        // Objetos de teste
        Bet a = new Bet("João", 123456789, 1, numerosApostadosMais5);
        Bet b = new Bet("Maria", 987654321, 2, numerosApostados5);

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
            System.out.print("Digite a opcao desejada: ");
            option = in.nextInt();

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
                default:
                    System.out.println("Opcao invalida");
            }
        } while (option != 0);
    }

    public void newBet() {
        try {
            System.out.println("Digite o nome do apostador: ");
            String bettorName = in.next();
            System.out.println("Digite o CPF do apostador: ");
            int CPF = 0;
            while (!in.hasNextInt()) {
                System.out.println("CPF inválido. Digite um número inteiro: ");
                in.next(); // Descarta a entrada inválida
            }
            CPF = in.nextInt();

            System.out.println(
                    "Você deseja apostar em números específicos ou deseja que o sistema escolha aleatoriamente? ");
            System.out.println("1 - Números específicos");
            System.out.println("2 - Aleatório");
            System.out.print("Digite a opção desejada: ");
            int option = 0;
            // ****** checar while para ver se a validacao de opcao esta funcional ******
            while (!in.hasNextInt() || ((option = in.nextInt()) != 1 && option != 2)) {
                System.out.println("Opção inválida. Digite novamente: ");
                in.nextLine();
            }
            betCreator.newBet(bettorName, CPF, option);

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
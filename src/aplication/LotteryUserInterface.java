package aplication;

import java.lang.reflect.Array;
import java.util.*;

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
        this.lotteryRunner = new LotteryRunner(betRegistry, betCreator);

        menu();
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
            while (true) {
                try{
                    CPF = in.nextInt();
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
            int option = 0;
            while (true) {
                try {
                    option = in.nextInt();
                    if (option != 1 && option != 2) {
                        throw new InputMismatchException(); // throw exception if option is not 1 or 2
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Digite novamente: ");
                }
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
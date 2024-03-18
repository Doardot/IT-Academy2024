package aplicacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import dados.*;

public class LotteryUserInterface {
    private Scanner in = null;
    private TodasApostas todasApostas;
    private BetCreator betCreator;
    private Bets bets;
    private LotteryRunner lotteryRunner;


    public LotteryUserInterface() {
        this.in = new Scanner(System.in);
        this.todasApostas = new TodasApostas();
        this.bets = new Bets();
        this.betCreator = new BetCreator(todasApostas, bets);
        this.lotteryRunner = new LotteryRunner(todasApostas);

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
        Aposta a = new Aposta("João", 123456789, 1, numerosApostadosMais5);
        Aposta b = new Aposta("Maria", 987654321, 2, numerosApostados5);

        todasApostas.cadastraApostas(a);
        todasApostas.cadastraApostas(b);
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("0 - Sair");
            System.out.println("1 - Nova aposta");
            System.out.println("2 - Conferir lista de apostas");
            System.out.println("3 - Executar sorteio");
            System.out.print("Digite a opcao desejada: ");
            opcao = in.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    novaAposta();
                    break;
                case 2:
                    listaApostas();
                    break;
                case 3:
                    System.out.println("Você deseja executar o sorteio? Digite 's' para confirmar. ");
                    boolean confirmaSorteio = in.next().equalsIgnoreCase("s");
                    if (confirmaSorteio) {
                        lotteryRunner.executaSorteio();
                    }
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (opcao != 0);
    }

    public void novaAposta() {
        try {
            System.out.println("Digite o nome do apostador: ");
            String nomeApostador = in.next();
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
            int opcao = 0;
            // ****** checar while para ver se a validacao de opcao esta funcional ******
            while (!in.hasNextInt() || ((opcao = in.nextInt()) != 1 && opcao != 2)) {
                System.out.println("Opção inválida. Digite novamente: ");
                in.nextLine();
            }
            betCreator.novaAposta(nomeApostador, CPF, opcao);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao processar a nova aposta: " + e.getMessage());
        }
    }

    public void listaApostas() {
        for (Aposta a : todasApostas.getApostas()) {
            System.out.println("\n" + a.toString());
        }
    }
}
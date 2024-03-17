package aplicacao;

import java.util.ArrayList;
import java.util.Scanner;
import dados.*;

public class Sorteador {
    private Scanner in = null;
    private TodasApostas todasApostas;

    public Sorteador() {
        in = new Scanner(System.in);
        todasApostas = new TodasApostas();

        // objetos de teste
        Aposta a = new Aposta("João", 123456789, 1,
                new int[] { 1, 2, 3, 4, 5 });
        Aposta b = new Aposta("Maria", 987654321, 2,
                new int[] { 6, 7, 8, 9, 10 });
        todasApostas.cadastraApostas(a);
        todasApostas.cadastraApostas(b);
        // ----------------
        menu();
    }

    public void menu() {
        int opcao = 0;
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
                        executaSorteio();
                    }
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (opcao != 0);
    }

    private int registroDeAposta = 999;

    public void novaAposta() {
        registroDeAposta++;
        System.out.println("Digite o nome do apostador: ");
        String nomeApostador = in.next();
        System.out.println("Digite o CPF do apostador: ");
        int CPF = 0;
        while (!in.hasNextInt()) {
            System.out.println("CPF inválido. Digite um número inteiro: ");
            in.next(); // Descarta a entrada inválida
        }
        CPF = in.nextInt();
        System.out
                .println("Você deseja apostar em números específicos ou deseja que o sistema escolha aleatoriamente? ");
        System.out.println("1 - Números específicos");
        System.out.println("2 - Aleatório");
        System.out.print("Digite a opção desejada: ");
        int opcao = in.nextInt();
        if (opcao == 1) {
            novaApostaEspecifica(nomeApostador, CPF);
        } else {
            novaApostaRandomica(nomeApostador, CPF);
        }
    }

    public void novaApostaEspecifica(String nomeApostador, int CPF) {
        System.out.println("Digite os números apostados: ");
        int[] numerosApostados = new int[5];
        for (int i = 0; i < 5; i++) {
            while (!in.hasNextInt()) {
                System.out.println("Número inválido. Digite um número inteiro: ");
                in.next();
            }
            numerosApostados[i] = in.nextInt();
        }
        Aposta a = new Aposta(nomeApostador, CPF, registroDeAposta, numerosApostados);
        todasApostas.cadastraApostas(a);
    }

    public void novaApostaRandomica(String nomeApostador, int CPF) {
        int[] numerosApostados = new int[5];
        for (int i = 0; i < 5; i++) {
            numerosApostados[i] = (int) (Math.random() * 50 + 1);
        }
        Aposta a = new Aposta(nomeApostador, CPF, registroDeAposta, numerosApostados);
        todasApostas.cadastraApostas(a);
    }

    public void listaApostas() {
        for (Aposta a : todasApostas.getApostas()) {
            System.out.println("Nome do apostador: " + a.getNomeApostador());
            System.out.println("CPF: " + a.getCPF());
            System.out.println("Registro da aposta: " + a.getRegistroDeAposta());
            for (int i = 0; i < 5; i++) {
                System.out.println("Número apostado: " + a.getNumerosApostados()[i]);
            }
            System.out.println();
        }
    }

    public void executaSorteio() {
        int count = 5; // Número de acertos necessários para vencer
        int maxTentativa = 26; // Número máximo de tentativas - 25 tentativas
        ArrayList<Integer> numerosSorteados = new ArrayList<Integer>();
        boolean winnerFound = false;

        for (int tentativa = 0; tentativa < maxTentativa && !winnerFound; tentativa++) {
            for (int i = 0; i < count; i++) {
                numerosSorteados.add((int) (Math.random() * 50 + 1));
            }
            System.out.println(
                    "Tentativa " + (tentativa + 1) + ": Números sorteados: " + numerosSorteados.toString());

            winnerFound = apuraVencedores(numerosSorteados);
            if (!winnerFound && numerosSorteados.size() < 31) {
                int novoNumero = (int) (Math.random() * 50 + 1); // Gera um novo número
                numerosSorteados.add(novoNumero); // Adiciona o novo número ao sorteio
                count = 0; // faz adicionar de 1 em 1
            }
        }

        if (!winnerFound) {
            System.out.println("Nenhum vencedor encontrado após " + maxTentativa + " tentativas.");
        }
    }

    public boolean apuraVencedores(ArrayList<Integer> numerosSorteados) {
        ArrayList<Aposta> vencedores = new ArrayList<Aposta>();
        for (Aposta a : todasApostas.getApostas()) {
            int[] numerosApostados = a.getNumerosApostados();
            int count = 0;
            for (int i = 0; i < numerosApostados.length; i++) {
                if (numerosSorteados.contains(numerosApostados[i])) {
                    count++;
                }
            }
            if (count == 5) {
                vencedores.add(a);
            }
        }

        if (!vencedores.isEmpty()) {
            System.out.println("Vencedores:");
            for (Aposta v : vencedores) {
                System.out.println("Nome do apostador: " + v.getNomeApostador());
            }
            return true;
        }
        return false;
    }
}

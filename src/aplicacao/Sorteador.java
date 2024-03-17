package aplicacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import dados.*;

public class Sorteador {
    private Scanner in = null;
    private TodasApostas todasApostas;

    public Sorteador() {
        in = new Scanner(System.in);
        todasApostas = new TodasApostas();

        // Array de teste
        ArrayList<Integer> numerosApostados = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));

        // Objetos de teste
        Aposta a = new Aposta("João", 123456789, 1, numerosApostados);
        Aposta b = new Aposta("Maria", 987654321, 2, numerosApostados);
        Aposta c = new Aposta("José", 123456789, 3, numerosApostados);
        Aposta d = new Aposta("Ana", 987654321, 4, numerosApostados);

        /*
         * Formato int[]
         * Aposta d = new Aposta("Ana", 987654321, 4,
         * new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
         * 19, 20, 21, 22, 23, 24,
         * 25 });
         */

        todasApostas.cadastraApostas(a);
        todasApostas.cadastraApostas(b);
        todasApostas.cadastraApostas(c);
        todasApostas.cadastraApostas(d);

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
        // int[] numerosApostados = new int[5];
        ArrayList<Integer> numerosApostados = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            while (!in.hasNextInt()) {
                System.out.println("Número inválido. Digite um número inteiro: ");
                in.next();
            }
            // numerosApostados[i] = in.nextInt();
            numerosApostados.add(in.nextInt());
        }
        Aposta a = new Aposta(nomeApostador, CPF, registroDeAposta, numerosApostados);
        todasApostas.cadastraApostas(a);
    }

    public void novaApostaRandomica(String nomeApostador, int CPF) {
        // int[] numerosApostados = new int[5];
        ArrayList<Integer> numerosApostados = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            // numerosApostados[i] = (int) (Math.random() * 50 + 1);
            numerosApostados.add((int) (Math.random() * 50 + 1));
        }
        Aposta a = new Aposta(nomeApostador, CPF, registroDeAposta, numerosApostados);
        todasApostas.cadastraApostas(a);
    }

    public void listaApostas() {
        for (Aposta a : todasApostas.getApostas()) {
            System.out.println("Nome do apostador: " + a.getNomeApostador());
            System.out.println("CPF: " + a.getCPF());
            System.out.println("Registro da aposta: " + a.getRegistroDeAposta());
            // System.out.println("Números apostados: " +
            // Arrays.toString(a.getNumerosApostados()));
            System.out.println("Números apostados: " + a.getNumerosApostados().toString());
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
            /*
             * System.out.println(
             * "Tentativa " + (tentativa + 1) + ": Números sorteados: " +
             * numerosSorteados.toString());
             */

            winnerFound = apuraVencedores(numerosSorteados);
            if (winnerFound) {
                System.out.println("\nVencedor encontrado após " + (tentativa) + " rodadas.");
                System.out.println("\nNúmeros sorteados: " + numerosSorteados.toString());
                break;
            }
            if (!winnerFound && numerosSorteados.size() < 31) {
                int novoNumero = (int) (Math.random() * 50 + 1); // Gera um novo número
                numerosSorteados.add(novoNumero); // Adiciona o novo número ao sorteio
                count = 0; // faz adicionar de 1 em 1
            }
        }

        if (!winnerFound) {
            System.out.println("\nNenhum vencedor encontrado após " + maxTentativa + " tentativas.");
            System.out.println("\nNúmeros sorteados: " + numerosSorteados.toString());
        }

        listaDeNumerosApostados();
    }

    public boolean apuraVencedores(ArrayList<Integer> numerosSorteados) {
        ArrayList<Aposta> vencedores = new ArrayList<Aposta>();
        for (Aposta a : todasApostas.getApostas()) {
            // int[] numerosApostados = a.getNumerosApostados();
            ArrayList<Integer> numerosApostados = a.getNumerosApostados();
            int count = 0;
            /*
             * for (int i = 0; i < numerosApostados.length; i++) {
             * if (numerosSorteados.contains(numerosApostados[i])) {
             * count++;
             * }
             * }
             */
            for (int i = 0; i < numerosApostados.size(); i++) {
                if (numerosSorteados.contains(numerosApostados.get(i))) {
                    count++;
                }
            }
            if (count == 5) {
                vencedores.add(a);
            }
        }

        if (!vencedores.isEmpty()) {
            // Ordena a lista de vencedores por nome
            Collections.sort(vencedores, new Comparator<Aposta>() {
                @Override
                public int compare(Aposta a1, Aposta a2) {
                    return a1.getNomeApostador().compareTo(a2.getNomeApostador());
                }
            });
            System.out.println("Vencedores:");
            for (Aposta v : vencedores) {
                System.out.println(
                        "\nNome do apostador: " + v.getNomeApostador() + "\nNúmeros apostados: " +
                        // Arrays.toString(v.getNumerosApostados()));
                                v.getNumerosApostados().toString());
            }
            return true;
        }
        return false;
    }

    public void listaDeNumerosApostados() {
        HashMap<Integer, Integer> frequenciaNumeros = new HashMap<>();

        for (Aposta aposta : todasApostas.getApostas()) {
            for (int numero : aposta.getNumerosApostados()) {
                frequenciaNumeros.put(numero, frequenciaNumeros.getOrDefault(numero, 0) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> listaOrdenada = new ArrayList<>(frequenciaNumeros.entrySet());

        Collections.sort(listaOrdenada, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue()); // Ordenação decrescente
            }
        });

        System.out.println("\nNúmero Apostado | Quantidade de Apostas");
        System.out.println("--------------------------------------");
        for (Map.Entry<Integer, Integer> entry : frequenciaNumeros.entrySet()) {
            System.out.printf("%-3d | %-4d\n", entry.getKey(), entry.getValue());
        }

        /*
         * for (Map.Entry<Integer, Integer> entry : listaOrdenada) {
         * System.out.println("Número: " + entry.getKey() + " - Quantidade de apostas: "
         * + entry.getValue());
         * }
         */
    }
}

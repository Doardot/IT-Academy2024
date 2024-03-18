package aplication;

import data.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class LotteryRunner {
    private BetRegistry betRegistry;

    public LotteryRunner(BetRegistry betRegistry) {
        this.betRegistry = betRegistry;
    }

    public void runLotteryDraw() {
        int count = 5; // Número de acertos necessários para vencer
        int maxAttempt = 26; // Número máximo de tentativas - 25 tentativas
        HashSet<Integer> drawNumbers = new HashSet<>();
        boolean winnerFound = false;

        for (int attempt = 0; attempt < maxAttempt && !winnerFound; attempt++) {
            //for (int i = 0; i < count; i++) {
            //**** CHECAR WHILE ***
            while(drawNumbers.size() < count + attempt) {
                drawNumbers.add((int) (Math.random() * 50 + 1));
            }
            /*
             * System.out.println(
             * "Tentativa " + (tentativa + 1) + ": Números sorteados: " +
             * numerosSorteados.toString());
             */

            winnerFound = determineWinners(drawNumbers);
            if (winnerFound) {
                System.out.println("\nVencedor encontrado após " + (attempt) + " rodadas.");
                System.out.println("\nNúmeros sorteados: " + drawNumbers.toString());
                break;
            }
//            if (!winnerFound && numerosSorteados.size() < 31) {
//                numerosSorteados.clear(); = metodo para limpar a lista
//            else {
//                int novoNumero = (int) (Math.random() * 50 + 1); // Gera um novo número
//                numerosSorteados.add(novoNumero); // Adiciona o novo número ao sorteio
//                count = 0; // faz adicionar de 1 em 1
//            }
        }

        if (!winnerFound) {
            System.out.println("\nNenhum vencedor encontrado após " + maxAttempt + " tentativas.");
            System.out.println("\nNúmeros sorteados: " + drawNumbers.toString());
        }

        frequencyOfWagaredNumbers();
    }

    private boolean determineWinners(HashSet<Integer> drawNumbers) {
        ArrayList<Bet> winners = new ArrayList<>();
        //HashSet<Integer> numerosSorteadosSet = new HashSet<>(numerosSorteados);

        for (Bet a : betRegistry.getBets()) {
            HashSet<Integer> betNumbers = new HashSet<>(a.getWagaredNumbers());
//            ArrayList<Integer> numerosApostados = a.getWagaredNumbers();
//            int count = 0;
            betNumbers.retainAll(drawNumbers);
            if(betNumbers.size() == 5) {
                winners.add(a);
            }
//            for (int numero : numerosApostados) {
//                if (numerosSorteadosSet.contains(numero)) {
//                    count++;
//                }
//            }
//            if (count == 5) {
//                vencedores.add(a);
//            }
        }

        if (!winners.isEmpty()) {
            // Ordena a lista de vencedores por nome
            Collections.sort(winners, new Comparator<Bet>() {
                @Override
                public int compare(Bet a1, Bet a2) {
                    return a1.getBettorName().compareTo(a2.getBettorName());
                }
            });
            System.out.println("Vencedores:");
            for (Bet v : winners) {
                System.out.println(
                        "\nNome do apostador: " + v.getBettorName() + "\nNúmeros apostados: " +
                                v.getWagaredNumbers().toString());
            }
            return true;
        }
        return false;
    }

    private void frequencyOfWagaredNumbers() {
        HashMap<Integer, Integer> frequencyNumbers = new HashMap<>();

        for (Bet bet : betRegistry.getBets()) {
            for (int numero : bet.getWagaredNumbers()) {
                frequencyNumbers.put(numero, frequencyNumbers.getOrDefault(numero, 0) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> listaOrdenada = new ArrayList<>(frequencyNumbers.entrySet());

        Collections.sort(listaOrdenada, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue()); // Ordenação decrescente
            }
        });

        System.out.println("\nNúmero Apostado | Quantidade de Apostas");
        System.out.println("--------------------------------------");
        for (Map.Entry<Integer, Integer> entry : frequencyNumbers.entrySet()) {
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


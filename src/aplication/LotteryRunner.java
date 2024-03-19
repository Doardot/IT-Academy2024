package aplication;

import data.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class LotteryRunner {
    private final BetRegistry betRegistry;
    private final BetCreator betCreator;
    private final Awards awards;

    public LotteryRunner(BetRegistry betRegistry, BetCreator betCreator) {
        this.betRegistry = betRegistry;
        this.betCreator = betCreator;
        this.awards = new Awards();
    }

    public void runLotteryDraw() { //metodo esta rodando entre 23 e 26 vezes > corrigir
        try{
            betRegistry.closeBets();
            int count = 5; // number of wagared numbers
            int maxAttempt = 26; // maximum number of attempts
            LinkedHashSet<Integer> drawNumbers = new LinkedHashSet<>();

            for (int attempt = 0; attempt < maxAttempt ; attempt++) {
                while(drawNumbers.size() < count + attempt) {
                    drawNumbers.add((int) (Math.random() * 50 + 1));
                }

                if(determineWinners(drawNumbers)) {
                    System.out.println("\nVencedor encontrado após " + (attempt) + " rodadas.");
                    System.out.println("\nNúmeros sorteados: " + drawNumbers);
                    break;
                }

                if (drawNumbers.size() == maxAttempt) {
                    System.out.println("\nNenhum vencedor encontrado após " + maxAttempt + " tentativas.");
                    System.out.println("\nNúmeros sorteados: " + drawNumbers);

                }
            }

            frequencyOfWagaredNumbers();
            betRegistry.resetBets();
            betCreator.resetBetRegistration();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao executar o sorteio: " + e.getMessage());
        }
    }

    private boolean determineWinners(HashSet<Integer> drawNumbers) {
        ArrayList<Bet> winners = new ArrayList<>();

        for (Bet a : betRegistry.getBets()) {
            HashSet<Integer> betNumbers = new HashSet<>(a.getWagaredNumbers());
            betNumbers.retainAll(drawNumbers);
            if(betNumbers.size() == 5) {
                winners.add(a);
            }
        }

        if (!winners.isEmpty()) {
            // Ordena a lista de vencedores por nome
            winners.sort(Comparator.comparing(Bet::getBettorName));
            System.out.println("Vencedores:");
            for (Bet v : winners) {
                String award = awards.pickRandomAwards();
                String winnerInfo = "Nome do apostador: " + v.getBettorName() + "\nNúmeros apostados: " +
                        v.getWagaredNumbers().toString() + "\nPrêmio: " + award;
                System.out.println(winnerInfo);
                BettingSaveHistory.saveToFile(winnerInfo, "WinnerHistory.txt");
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

        listaOrdenada.sort((entry1, entry2) -> {
            return entry2.getValue().compareTo(entry1.getValue()); // Ordenação decrescente
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


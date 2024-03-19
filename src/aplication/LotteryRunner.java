package aplication;

import data.*;
import java.util.ArrayList;
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

    /**
     * This method is used to run the lottery draw.
     * 1- It first closes all bets;
     * 2- Then generates a set of random numbers for the draw;
     * 3- It attempts to determine the winners for each draw.
     * 4- If winners are found, it breaks the loop and prints the winners;
     * 5- Otherwise, it prints a message indicating that no winners were found;
     * 6- After the draw, it calculates the frequency of wagered numbers.
     * 7- Finally, it resets the bets and the bet registration.
     */
    public void runLotteryDraw() {
        try{
            betRegistry.closeBets();
            int count = 5; // Number of wagered numbers
            int maxAttempt = 26; // Maximum number of attempts
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

    /**
     * This method is used to determine the winners of the lottery draw.
     * 1- It iterates over all the bets in the bet registry;
     * 2- For each bet, it creates a new set of bet numbers;
     * 3- And retains only the numbers that are also in the drawn numbers;
     * 4- If the size of this set is 5, the bet is a winner, adding it to the list of winners;
     * 5- Finally, if the list of winners is not empty, it sorts the winners by the bettor's name;
     * 6- Prints the winners, and saves the winner information to a file.
     */
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
            winners.sort(Comparator.comparing(Bet::getBettorName)); // Sort the winners by name
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

    /**
     * This method is used to calculate the frequency of wagered numbers.
     * 1- It creates a new HashMap to store the frequency of each number;
     * 2- For each bet in the bet registry, it iterates over the wagered numbers
     * and increments their count in the frequency map;
     * 3- After all bets have been processed, it creates a list from the entries in the
     * frequency map and sorts it in descending order based on the frequency of each number;
     * 4- Finally, it prints a table showing the wagered number and its corresponding frequency.
     */
    private void frequencyOfWagaredNumbers() {
        HashMap<Integer, Integer> frequencyNumbers = new HashMap<>();

        for (Bet bet : betRegistry.getBets()) {
            for (int numero : bet.getWagaredNumbers()) {
                frequencyNumbers.put(numero, frequencyNumbers.getOrDefault(numero, 0) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> listaOrdenada = new ArrayList<>(frequencyNumbers.entrySet());

        listaOrdenada.sort((entry1, entry2) -> {
            return entry2.getValue().compareTo(entry1.getValue()); // Sort the list in descending order
        });

        System.out.println("\nNúmero Apostado | Quantidade de Apostas");
        System.out.println("--------------------------------------");
        for (Map.Entry<Integer, Integer> entry : frequencyNumbers.entrySet()) {
            System.out.printf("%-3d | %-4d\n", entry.getKey(), entry.getValue());
        }
    }
}


package aplication;

import java.util.ArrayList;
import java.util.Random;

public class Awards {
    private final ArrayList<String> awards;
    private final Random random;

    public Awards(){
        awards = new ArrayList<>();
        random = new Random();
        generateAwards();
    }

    /**
     * This method is used to generate a list of awards.
     * It adds a series of predefined awards to the 'awards' ArrayList.
     */
    public void generateAwards(){
        awards.add("R$ 100,00");
        awards.add("Uma banana");
        awards.add("Uma viagem para o Caribe");
        awards.add("Um Kit-Copo");
        awards.add("Um livro de culinária");
        awards.add("Um PS5");
        awards.add("Um Samsung J5 Prime");
        awards.add("Um ingresso ao jogo do Internacional");
        awards.add("Uma camiseta do Internacional");
        awards.add("Uma boné do Internacional");
        awards.add("Dinheiro da aposentadoria do vovô");
        awards.add("Um livro de Java");
        awards.add("Um kit corrida");
        awards.add("Um cubo mágico");
        awards.add("Um cavalo");
    }

    /**
     * This method is used to pick a random award from the 'awards' ArrayList.
     * It generates a random index within the range of the 'awards' ArrayList size.
     * Then, it returns the award at the generated random index.
     */
    public String pickRandomAwards() {
        int randomIndex = random.nextInt(awards.size());
        return awards.get(randomIndex);
    }

    /**
     * This method is used to convert the 'awards' ArrayList into a single string.
     * It uses the "String.join" method to concatenate all the elements of the 'awards' ArrayList,
     * separating them with a newline character ("\n").
     */
    @Override
    public String toString() {
        return String.join("\n", awards);
    }
}

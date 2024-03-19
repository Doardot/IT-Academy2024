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

    public String pickRandomAwards() {
        int randomIndex = random.nextInt(awards.size());
        return awards.get(randomIndex);
    }

    @Override
    public String toString() {
        return String.join("\n", awards);
    }
}

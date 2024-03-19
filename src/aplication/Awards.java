package aplication;

import java.util.ArrayList;
import java.util.Random;

public class Awards {
    private ArrayList<String> awards;
    private Random random;

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
        //....
    }

    public String pickRandomAwards() {
        int randomIndex = random.nextInt(awards.size());
        return awards.get(randomIndex);
    }

    public String toString() {
        return awards.toString();
    }
}

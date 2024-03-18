package dados;

import java.util.ArrayList;

public class TodasApostas {
    private ArrayList<Aposta> apostas;

    public TodasApostas() {
        apostas = new ArrayList<>();
    }

    public ArrayList<Aposta> getApostas() {
        return apostas;
    }

    public void cadastraApostas(Aposta a) {
        apostas.add(a);
    }

}

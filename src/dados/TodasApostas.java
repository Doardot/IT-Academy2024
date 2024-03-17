package dados;

import java.util.ArrayList;

public class TodasApostas {
    private ArrayList<Aposta> apostas;

    public TodasApostas() {
        apostas = new ArrayList<Aposta>();
    }

    public ArrayList<Aposta> getApostas() {
        return apostas;
    }

    public void cadastraApostas(Aposta a) {
        apostas.add(a);
    }

    public void listaApostas() {
        //
    }
}

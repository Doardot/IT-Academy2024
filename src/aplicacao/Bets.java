package aplicacao;

import dados.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Bets {
    private Scanner in;

    public Bets() {
        this.in = new Scanner(System.in);
    }

    public Aposta novaApostaEspecifica(String nomeApostador, int CPF, int registroDeAposta) {
        System.out.println("Digite os números apostados: ");
        ArrayList<Integer> numerosApostados = new ArrayList<Integer>();
        // int numeroSelecionado;
        for (int i = 0; i < 5; i++) {
            // while (!in.hasNextInt() && (numeroSelecionado = in.nextInt()) < 1 ||
            // numeroSelecionado > 50) {
            while (!in.hasNextInt()) { // validar numeros entre 1 a 50
                System.out.println("Número inválido. Digite novamente: ");
                in.nextLine();
            }
            numerosApostados.add(in.nextInt());
        }
        return new Aposta(nomeApostador, CPF, registroDeAposta, numerosApostados);
    }

    public Aposta novaApostaRandomica(String nomeApostador, int CPF, int registroDeAposta) {
        ArrayList<Integer> numerosApostados = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            numerosApostados.add((int) (Math.random() * 50 + 1));
        }
        return new Aposta(nomeApostador, CPF, registroDeAposta, numerosApostados);
    }
}

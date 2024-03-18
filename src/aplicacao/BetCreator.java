package aplicacao;

import dados.*;

public class BetCreator {
    private TodasApostas todasApostas;
    private Bets bets;
    private int registroDeAposta = 999;

    public BetCreator(TodasApostas todasApostas, Bets bets) {
        this.todasApostas = todasApostas;
        this.bets = bets;
    }

    public Aposta novaAposta(String nomeApostador, int CPF, int opcao) {
        Aposta a = null;
//        try {
            registroDeAposta++;
            if (opcao == 1) {
                a = bets.novaApostaEspecifica(nomeApostador, CPF, registroDeAposta);
            } else if (opcao == 2) {
                a = bets.novaApostaRandomica(nomeApostador, CPF, registroDeAposta);
            }
            todasApostas.cadastraApostas(a);
//        } catch (Exception e) {
//            System.out.println("Ocorreu um erro ao processar a nova aposta: " + e.getMessage());
//        }
        return a;
    }
}

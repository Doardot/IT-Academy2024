package dados;

public class Aposta {
    private String nomeApostador;
    private int CPF;
    private int registroDeAposta;
    private int[] numerosApostados;

    public Aposta(String nomeApostador, int CPF, int registroDeAposta, int[] numerosApostados) {
        this.nomeApostador = nomeApostador;
        this.CPF = CPF;
        this.registroDeAposta = registroDeAposta;
        this.numerosApostados = numerosApostados;
    }

    public String getNomeApostador() {
        return nomeApostador;
    }

    public int getCPF() {
        return CPF;
    }

    public int getRegistroDeAposta() {
        return registroDeAposta;
    }

    public int[] getNumerosApostados() {
        return numerosApostados;
    }

    public String toString() {
        return "Nome do apostador: " + nomeApostador + "\nCPF: " + CPF + "\nRegistro da aposta: " + registroDeAposta;
    }
}

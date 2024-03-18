package aplicacao;

import dados.*;

import java.util.*;

public class LotteryRunner {
    private TodasApostas todasApostas;

    public LotteryRunner(TodasApostas todasApostas) {
        this.todasApostas = todasApostas;
    }

    public void executaSorteio() {
        int count = 5; // Número de acertos necessários para vencer
        int maxTentativa = 26; // Número máximo de tentativas - 25 tentativas
        ArrayList<Integer> numerosSorteados = new ArrayList<>();
        boolean winnerFound = false;

        for (int tentativa = 0; tentativa < maxTentativa && !winnerFound; tentativa++) {
            for (int i = 0; i < count; i++) {
                numerosSorteados.add((int) (Math.random() * 50 + 1));
            }
            /*
             * System.out.println(
             * "Tentativa " + (tentativa + 1) + ": Números sorteados: " +
             * numerosSorteados.toString());
             */

            winnerFound = apuraVencedores(numerosSorteados);
            if (winnerFound) {
                System.out.println("\nVencedor encontrado após " + (tentativa) + " rodadas.");
                System.out.println("\nNúmeros sorteados: " + numerosSorteados.toString());
                break;
            }
//            if (!winnerFound && numerosSorteados.size() < 31) {
//                numerosSorteados.clear(); = metodo para limpar a lista
            else {
                int novoNumero = (int) (Math.random() * 50 + 1); // Gera um novo número
                numerosSorteados.add(novoNumero); // Adiciona o novo número ao sorteio
                count = 0; // faz adicionar de 1 em 1
            }
        }

        if (!winnerFound) {
            System.out.println("\nNenhum vencedor encontrado após " + maxTentativa + " tentativas.");
            System.out.println("\nNúmeros sorteados: " + numerosSorteados.toString());
        }

        listaDeNumerosApostados();
    }

    public boolean apuraVencedores(ArrayList<Integer> numerosSorteados) {
        ArrayList<Aposta> vencedores = new ArrayList<Aposta>();
        HashSet<Integer> numerosSorteadosSet = new HashSet<>(numerosSorteados);
        for (Aposta a : todasApostas.getApostas()) {
            ArrayList<Integer> numerosApostados = a.getNumerosApostados();
            int count = 0;
            for (int numero : numerosApostados) {
                if (numerosSorteadosSet.contains(numero)) {
                    count++;
                }
            }
//            for (int i = 0; i < numerosApostados.size(); i++) {
//                if (numerosSorteados.contains(numerosApostados.get(i))) {
//                    count++;
//                }
//            }
            if (count == 5) {
                vencedores.add(a);
            }
        }

        if (!vencedores.isEmpty()) {
            // Ordena a lista de vencedores por nome
            Collections.sort(vencedores, new Comparator<Aposta>() {
                @Override
                public int compare(Aposta a1, Aposta a2) {
                    return a1.getNomeApostador().compareTo(a2.getNomeApostador());
                }
            });
            System.out.println("Vencedores:");
            for (Aposta v : vencedores) {
                System.out.println(
                        "\nNome do apostador: " + v.getNomeApostador() + "\nNúmeros apostados: " +
                                v.getNumerosApostados().toString());
            }
            return true;
        }
        return false;
    }

    public void listaDeNumerosApostados() {
        HashMap<Integer, Integer> frequenciaNumeros = new HashMap<>();

        for (Aposta aposta : todasApostas.getApostas()) {
            for (int numero : aposta.getNumerosApostados()) {
                frequenciaNumeros.put(numero, frequenciaNumeros.getOrDefault(numero, 0) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> listaOrdenada = new ArrayList<>(frequenciaNumeros.entrySet());

        Collections.sort(listaOrdenada, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue()); // Ordenação decrescente
            }
        });

        System.out.println("\nNúmero Apostado | Quantidade de Apostas");
        System.out.println("--------------------------------------");
        for (Map.Entry<Integer, Integer> entry : frequenciaNumeros.entrySet()) {
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


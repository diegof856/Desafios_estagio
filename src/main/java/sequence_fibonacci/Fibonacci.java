package sequence_fibonacci;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    private Integer firstTerm;  // Primeiro termo da sequência de Fibonacci (0)
    private Integer secondTerm; // Segundo termo da sequência de Fibonacci (1)

    // Construtor que inicializa os dois primeiros termos da sequência
    public Fibonacci() {
        this.firstTerm = 0;
        this.secondTerm = 1;
    }

    // Método que verifica se um número pertence à sequência de Fibonacci
    public void checkSequence(Integer number) {
        List<Integer> list = new ArrayList<>();
        list.add(this.firstTerm);  // Adiciona o primeiro termo (0) à lista
        list.add(this.secondTerm); // Adiciona o segundo termo (1) à lista
        int aux = list.get(1);     // Inicializa a variável auxiliar com o segundo termo

        // Verifica se o número está entre os dois primeiros termos da sequência
        if(list.stream().anyMatch(n -> n.equals(number))) {
            System.out.println("Esse numero pertence a sequencia fibonacci");
        } else {
            // Calcula a sequência até encontrar o número ou até ultrapassá-lo
            calculateSequence(list, number, aux);
        }
    }

    // Método recursivo que calcula a sequência de Fibonacci
    private void calculateSequence(List<Integer> list, int number, int aux) {
        // Calcula o próximo termo da sequência
        aux = list.get(list.size() - 2) + list.get(list.size() - 1);
        list.add(aux);  // Adiciona o novo termo à lista

        // Verifica se o número pertence à sequência ou se já foi ultrapassado
        if(list.stream().anyMatch(n -> n.equals(number))) {
            System.out.println("Esse numero pertence a sequencia fibonacci");
            return;
        } else if(aux > number) {
            System.out.println("Esse numero não pertence a sequencia fibonacci");
            return;
        }

        // Continua o cálculo da sequência recursivamente
        calculateSequence(list, number, aux);
    }
}

package application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import billing_distributor.Billing;
import sequence_fibonacci.Fibonacci;
import sum.Sum;

public class App {

    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
        Scanner sc = new Scanner(System.in);
        
        // Primeiro Desafio: Soma de números (implementação da classe Sum)
        System.out.println("Primeiro Desafio");
        Sum s = new Sum();
        s.calculate();  // Calcula a soma de uma sequência ou de números
        System.out.println(s);  // Exibe o resultado da soma
        System.out.println("---------------------");
        
        // Segundo Desafio: Verificação de número em sequência de Fibonacci
        System.out.println("Segundo Desafio");
        Fibonacci fibo = new Fibonacci();
        System.out.print("Digite um numero: ");
        int number = sc.nextInt();
        sc.nextLine();
        fibo.checkSequence(number);  // Verifica se o número pertence à sequência de Fibonacci
        System.out.println("---------------------");
        
        // Terceiro Desafio: Análise do faturamento mensal
        System.out.println("Terceiro Desafio");
        thirdChallenge();  // Executa a análise do faturamento
        System.out.println("---------------------");
        
        // Quarto Desafio: Cálculo do percentual de faturamento por estado
        System.out.println("Quarto Desafio");
        FourthChallenge();  // Executa o cálculo dos percentuais de faturamento por estado
        System.out.println("---------------------");
        
        // Quinto Desafio: Inversão de uma string
        System.out.println("Quinto Desafio");
        System.out.print("Digite a palavra: ");
        String original = sc.nextLine();
        
        // Inversão da string
        String reversed = fifthChallenge(original);
        System.out.println("String invertida: " + reversed);  // Exibe a string invertida
        
        sc.close();
    }
    
    // Função para o terceiro desafio: Análise do faturamento mensal
    private static void thirdChallenge() throws StreamReadException, DatabindException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Leitura dos dados de faturamento a partir de um arquivo JSON
        List<Billing> billings = objectMapper.readValue(new File("faturamento.json"), new TypeReference<List<Billing>>() {});

        // Calcula o menor faturamento ignorando os dias com faturamento 0
        double lowerRevenue = billings.stream()
                .filter(f -> f.getBilling() > 0)  // Filtra os dias com faturamento maior que 0
                .mapToDouble(Billing::getBilling)  // Mapeia os valores de faturamento para Double
                .min()  // Encontra o menor valor
                .orElse(0.0);  // Caso não haja valores, retorna 0.0

        // Calcula o maior faturamento
        double higherRevenue = billings.stream()
                .mapToDouble(Billing::getBilling)  // Mapeia os valores de faturamento para Double
                .max()  // Encontra o maior valor
                .orElse(0.0);  // Caso não haja valores, retorna 0.0

        // Calcula a média de faturamento ignorando os dias com faturamento 0
        double averageMonthly = billings.stream()
                .filter(f -> f.getBilling() > 0)  // Filtra os dias com faturamento maior que 0
                .mapToDouble(Billing::getBilling)  // Mapeia os valores de faturamento para Double
                .average()  // Calcula a média dos valores
                .orElse(0.0);  // Caso não haja valores, retorna 0.0

        // Conta o número de dias em que o faturamento foi maior que a média mensal
        long daysAboveAverage = billings.stream()
                .filter(f -> f.getBilling() > averageMonthly)  // Filtra os dias com faturamento acima da média
                .count();  // Conta esses dias

        // Exibe os resultados calculados
        System.out.println("Menor faturamento do mês: " + lowerRevenue);
        System.out.println("Maior faturamento do mês: " + higherRevenue);
        System.out.println("Número de dias com faturamento acima da média: " + daysAboveAverage);
    }
    
    // Função para o quarto desafio: Cálculo do percentual de faturamento por estado
    private static void FourthChallenge() {
        // Valores de faturamento por estado
        Map<String, Double> billingByState = new HashMap<>();
        billingByState.put("SP", 67836.43);
        billingByState.put("RJ", 36678.66);
        billingByState.put("MG", 29229.88);
        billingByState.put("ES", 27165.48);
        billingByState.put("Outros", 19849.53);

        // Calcula o faturamento total
        double RevenueTotal = billingByState.values().stream().mapToDouble(Double::doubleValue).sum();

        // Calcula e exibe o percentual de cada estado
        for (Map.Entry<String, Double> entry : billingByState.entrySet()) {
            String state = entry.getKey();
            double billing = entry.getValue();
            double percentage = (billing / RevenueTotal) * 100;  // Calcula o percentual de faturamento
            System.out.printf("Estado: %s - Percentual: %.2f%%\n", state, percentage);
        }
    }
    
    // Função para o quinto desafio: Inversão de uma string
    private static String fifthChallenge(String word) {
        char[] characters = word.toCharArray();  // Converte a string em um array de caracteres
        int n = characters.length;
        
        // Laço para inverter a string manualmente
        for (int i = 0; i < n / 2; i++) {
            char temp = characters[i];
            characters[i] = characters[n - 1 - i];
            characters[n - 1 - i] = temp;
        }
        
        return new String(characters);  // Retorna a string invertida
    }
}

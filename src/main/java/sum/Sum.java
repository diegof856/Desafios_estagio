package sum;

public class Sum {

    private Integer INDICE; // Valor limite para o somatório (número até onde somaremos)
    private Integer SOMA;   // Variável que armazenará o resultado da soma
    private Integer K;      // Contador que será incrementado a cada iteração

    // Construtor que inicializa as variáveis
    public Sum() {
        this.INDICE = 13; // O limite para o somatório
        this.K = 0;       // Inicialmente, o contador K é zero
        this.SOMA = 0;    // Inicialmente, a soma é zero
    }

    // Método que calcula o somatório de 1 até INDICE
    public void calculate() {
        while(this.K < this.INDICE) { // Enquanto K for menor que INDICE
            this.K++;                 // Incrementa K em 1
            this.SOMA += this.K;      // Soma o valor de K à variável SOMA
        }
    }

    // Método que retorna a representação em String do objeto, mostrando o resultado da soma
    @Override
    public String toString() {
        return "O Resultado da soma será: " + this.SOMA;
    }
}

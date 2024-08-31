package billing_distributor;

// Esta classe representa o faturamento diário de uma distribuidora
public class Billing {

    // Atributo que armazena o dia do faturamento
    private Integer day;
    
    // Atributo que armazena o valor do faturamento
    private Double billing;
    
    // Método getter para obter o valor do dia
    public Integer getDay() {
        return day;
    }
    
    // Método setter para definir o valor do dia
    public void setDay(Integer day) {
        this.day = day;
    }
    
    // Método getter para obter o valor do faturamento
    public Double getBilling() {
        return billing;
    }
    
    // Método setter para definir o valor do faturamento
    public void setBilling(Double billing) {
        this.billing = billing;
    }
}

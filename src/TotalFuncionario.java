import java.math.BigDecimal;

public class TotalFuncionario implements Comparable <TotalFuncionario>{
    private final Funcionario funcionario;
    private BigDecimal total;

    public TotalFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.total = new BigDecimal("0.00");
    }

    public void adicionar(BigDecimal valor) {
        this.total = this.total.add(valor);
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public BigDecimal getTotal() {
        return total;
    }

     
    @Override
    public int compareTo(TotalFuncionario outro) {
        
        return outro.getTotal().compareTo(this.total);
    }

    // Facilita a exibição dos dados no painel do console
    @Override
    public String toString() {
       
        return String.format("%s - R$ %.2f", funcionario.getNome(), total);
    }

}

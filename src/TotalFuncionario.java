import java.math.BigDecimal;

public class TotalFuncionario {
    private final Funcionario funcionario;
    private BigDecimal total;

    public TotalFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.total = new BigDecimal("0.00");
    }

    public void adicionar(BigDecimal valor) {
        total = valor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public BigDecimal getTotal() {
        return total;
    }

}

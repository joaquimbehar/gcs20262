import java.math.BigDecimal;
import java.time.LocalDate;

// Modelo minimo para estudar o painel. Substituir pela classe do grupo.
public class Custo {
    private final BigDecimal valor;
    private final LocalDate data;
    private final Departamento departamento;
    private final Funcionario funcionario;

    public Custo(String valor, LocalDate data, Departamento departamento) {
        this(valor, data, departamento, null);
    }

    public Custo(String valor, LocalDate data, Departamento departamento, Funcionario funcionario) {
        this.valor = new BigDecimal(valor);
        this.data = data;
        this.departamento = departamento;
        this.funcionario = funcionario;
    }

    public BigDecimal getValor() { return valor; }
    public LocalDate getData() { return data; }
    public Departamento getDepartamento() { return departamento; }
    public Funcionario getFuncionario() { return funcionario; }
}

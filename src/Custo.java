import java.time.LocalDate;

// Modelo minimo para estudar o painel. Substituir pela classe do grupo.
public class Custo {
    private final Double valor;
    private final LocalDate data;
    private final Departamento departamento;
    private final Funcionario funcionario;
    private final String descricao;
    private final String categoria;

    public Custo(Double valor, LocalDate data, Departamento departamento, Funcionario funcionario, String descricao, String categoria) {
        this.valor = valor;
        this.data = data;
        this.departamento = departamento;
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.categoria = categoria;
    }


    public Double getValor() {
         return valor; 
        }
    public LocalDate getData() {
         return data; 
        }
    public Departamento getDepartamento() {
         return departamento; 
        }
    public Funcionario getFuncionario() {
         return funcionario; 
        }
    public String getDescricao(){
        return descricao;
    }
    public String getCategoria(){
        return categoria;
    }
}

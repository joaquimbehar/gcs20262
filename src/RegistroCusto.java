import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RegistroCusto {
    private final int codigo;
    private final BigDecimal valor;
    private final String descricao;
    private final LocalDate data;
    private final String categoria;
    private final String departamento;
    private final Funcionario responsavel;

    public RegistroCusto(int codigo, BigDecimal valor, String descricao, LocalDate data,
            String categoria, String departamento, Funcionario responsavel) {
        if (codigo <= 0 || valor == null || valor.signum() < 0 || data == null
                || responsavel == null || descricao == null || descricao.trim().isEmpty()
                || categoria == null || departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do custo invalidos.");
        }
        if (!categoria.equals("Aquisicao de bens") && !categoria.equals("Manutencao de bens")
                && !categoria.equals("Outros servicos")) {
            throw new IllegalArgumentException("Categoria invalida.");
        }
        this.codigo = codigo;
        this.valor = valor.setScale(2, RoundingMode.UNNECESSARY);
        this.descricao = descricao.trim();
        this.data = data;
        this.categoria = categoria;
        this.departamento = departamento.trim();
        this.responsavel = responsavel;
    }

    public int getCodigo() {
        return codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

}

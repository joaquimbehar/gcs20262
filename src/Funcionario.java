import java.util.Locale;

public class Funcionario {
    private final int matricula;
    private final String nome;
    private final String iniciais;
    private final Departamento departamento;

    public Funcionario(int matricula, String nome, Departamento departamento) {
        this(matricula, nome, gerarIniciais(nome), departamento);
    }

    // Mantém o construtor utilizado pelos exemplos de consultas.
    public Funcionario(int matricula, String nome, String iniciais, String departamento) {
        this(matricula, nome, iniciais, new Departamento(departamento));
    }

    private Funcionario(int matricula, String nome, String iniciais, Departamento departamento) {
        if (matricula <= 0 || nome == null || nome.trim().isEmpty()
                || iniciais == null || iniciais.trim().isEmpty()
                || departamento == null || departamento.getNome() == null
                || departamento.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do funcionário inválidos.");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
        this.iniciais = iniciais.trim();
        this.departamento = departamento;
    }

    private static String gerarIniciais(String nome) {
        if (nome == null || nome.trim().isEmpty()) { return ""; }
        String iniciais = "";
        for (String parte : nome.trim().split("\\s+")) {
            iniciais += parte.substring(0, 1);
        }
        return iniciais.toUpperCase(Locale.ROOT);
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getIniciais() { return iniciais; }
    public Departamento getDepartamento() { return departamento; }

    @Override
    public String toString() {
        return matricula + " - " + nome + " (" + iniciais + ") - " + departamento.getNome();
    }
}

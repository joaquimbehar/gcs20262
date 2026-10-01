public class Funcionario {
    private final int matricula;
    private final String nome;
    private final String iniciais;
    private final String departamento;

    public Funcionario(int matricula, String nome, String iniciais, String departamento) {
        if (matricula <= 0 || nome == null || nome.trim().isEmpty()
                || iniciais == null || iniciais.trim().isEmpty()
                || departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Dados do funcionario invalidos.");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
        this.iniciais = iniciais.trim();
        this.departamento = departamento.trim();

    private int matricula;
    private String nome;
    private Departamento departamento;

    public Funcionario(int matricula, String nome, Departamento departamento) {
        this.matricula = matricula;
        this.nome = nome;
        this.departamento = departamento;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " - " + departamento.getNome();
    }
}

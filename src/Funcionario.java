public class Funcionario {
    private final String matricula;
    private final String nome;
    private final String iniciais;

    public Funcionario(String nome, String iniciais) {
        this(nome, nome, iniciais);
    }

    public Funcionario(String matricula, String nome, String iniciais) {
        this.matricula = matricula;
        this.nome = nome;
        this.iniciais = iniciais;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getIniciais() { return iniciais; }

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

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " - " + departamento.getNome();
    }
}

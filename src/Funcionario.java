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
}

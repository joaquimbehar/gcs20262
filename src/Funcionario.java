public class Funcionario {
    private final int matricula;
    private final String nome;
    private final String iniciais;
    private final Departamento departamento;

    public Funcionario(int matricula, String nome, String iniciais, Departamento departamento) {
        if (matricula <= 0 || nome == null || nome.trim().isEmpty() || departamento == null) {
            throw new IllegalArgumentException("Dados do funcionario invalidos.");
        }
        this.matricula = matricula;
        this.nome = nome;
        this.iniciais = iniciais;
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

    public Departamento getDepartamento() {
        return departamento;
    }


    @Override
    public String toString() {
        return matricula + " - " + nome + " - " + departamento.getNome();
    }
}

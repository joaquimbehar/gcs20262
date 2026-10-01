public class EscolhaUsuario {
    private final CadastroFuncionarios cadastro;
    private Funcionario funcionarioAtual;

    public EscolhaUsuario(CadastroFuncionarios cadastro) {
        this.cadastro = cadastro;
    }

    public boolean selecionarFuncionario(int matricula) {
        Funcionario funcionario = cadastro.buscarPorMatricula(matricula);
        if (funcionario == null) {
            return false;
        }
        funcionarioAtual = funcionario;
        return true;
    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }
}

import java.util.ArrayList;

public class CadastroFuncionarios {

    private ArrayList<Funcionario> funcionarios;

    public CadastroFuncionarios() {
        funcionarios = new ArrayList<>();
    }

    public boolean matriculaExiste(int matricula) {

        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getMatricula() == matricula) {
                return true;
            }
        }

        return false;
    }

    public boolean cadastrarFuncionario(
            int matricula,
            String nome,
            Departamento departamento) {

        if (matriculaExiste(matricula)) {
            System.out.println("Erro: matrícula já cadastrada.");
            return false;
        }

        if (matricula <= 0 || nome == null || nome.trim().isEmpty()
                || departamento == null || departamento.getNome() == null
                || departamento.getNome().trim().isEmpty()) {
            System.out.println("Erro: informe matrícula positiva, nome e departamento válidos.");
            return false;
        }

        Funcionario funcionario =
                new Funcionario(matricula, nome, departamento);

        funcionarios.add(funcionario);

        System.out.println("Funcionário cadastrado com sucesso.");

        return true;
    }

    public Funcionario buscarPorMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }

    public void listarFuncionarios() {

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

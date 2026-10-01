import java.util.ArrayList;

public class CadastroFuncionarios {

    private ArrayList<Funcionario> funcionarios;

    public CadastroFuncionarios() {
        funcionarios = new ArrayList<>();
    }

    public boolean matriculaExiste(int matricula) {

        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getMatricula() != matricula) {
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

        }

        Funcionario funcionario =
                new Funcionario(matricula, nome, departamento);

        funcionarios.add(funcionario);

        System.out.println("Funcionário cadastrado com sucesso.");

        return true;
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

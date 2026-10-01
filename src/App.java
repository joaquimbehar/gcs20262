import java.util.Scanner;
import java.util.NoSuchElementException;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CadastroDepartamentos cadastroDepartamentos =
                new CadastroDepartamentos();

        CadastroFuncionarios cadastroFuncionarios =
                new CadastroFuncionarios();

        int opcao = -1;
        cadastroFuncionarios.getFuncionarios().add(
                new Funcionario(101, "Ana Silva", cadastroDepartamentos.buscarPorIndice(1)));
        cadastroFuncionarios.getFuncionarios().add(
                new Funcionario(102, "Bruno Lima", cadastroDepartamentos.buscarPorIndice(2)));
        cadastroFuncionarios.getFuncionarios().add(
                new Funcionario(103, "Carla Souza", cadastroDepartamentos.buscarPorIndice(6)));
        EscolhaUsuario escolhaUsuario = new EscolhaUsuario(cadastroFuncionarios);

        while (opcao != 0) {

            System.out.println();
            System.out.println("=== SISTEMA DE CUSTOS ===");
            Funcionario atual = escolhaUsuario.getFuncionarioAtual();
            if (atual == null) {
                System.out.println("Funcionário atual: nenhum selecionado.");
            } else {
                System.out.println("Funcionário atual: " + atual);
            }
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Escolher/trocar funcionário");
            System.out.println("0 - Sair");

            try {

                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine().trim());

                if (opcao == 1) {

                    System.out.print("Matrícula: ");
                    int matricula =
                            Integer.parseInt(scanner.nextLine().trim());

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.println();
                    System.out.println("Departamentos:");

                    cadastroDepartamentos.listarDepartamentos();

                    System.out.print("Escolha o departamento: ");

                    int escolhaDepartamento =
                            Integer.parseInt(scanner.nextLine().trim());

                    Departamento departamento =
                            cadastroDepartamentos.buscarPorIndice(
                                    escolhaDepartamento
                            );

                    if (departamento == null) {

                        System.out.println("Departamento inválido.");

                    } else {

                        cadastroFuncionarios.cadastrarFuncionario(
                                matricula,
                                nome,
                                departamento
                        );
                    }

                } else if (opcao == 2) {

                    cadastroFuncionarios.listarFuncionarios();

                } else if (opcao == 3) {
                    cadastroFuncionarios.listarFuncionarios();
                    if (!cadastroFuncionarios.getFuncionarios().isEmpty()) {
                        System.out.print("Matrícula do funcionário (0 para cancelar): ");
                        int matricula = Integer.parseInt(scanner.nextLine().trim());
                        if (matricula != 0) {
                            if (escolhaUsuario.selecionarFuncionario(matricula)) {
                                System.out.println("Funcionário selecionado: "
                                        + escolhaUsuario.getFuncionarioAtual());
                            } else {
                                System.out.println("Funcionário não encontrado. Seleção mantida.");
                            }
                        }
                    }
                } else if (opcao == 0) {

                    System.out.println("Sistema encerrado.");

                } else {

                    System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Erro: digite um número válido."
                );
            } catch (NoSuchElementException e) {
                break;
            }
        }

        scanner.close();
    }
}

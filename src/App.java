import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CadastroDepartamentos cadastroDepartamentos =
                new CadastroDepartamentos();

        CadastroFuncionarios cadastroFuncionarios =
                new CadastroFuncionarios();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println();
            System.out.println("=== SISTEMA DE CUSTOS ===");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("0 - Sair");

            try {

                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) {

                    System.out.print("Matrícula: ");
                    int matricula =
                            Integer.parseInt(scanner.nextLine());

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.println();
                    System.out.println("Departamentos:");

                    cadastroDepartamentos.listarDepartamentos();

                    System.out.print("Escolha o departamento: ");

                    int escolhaDepartamento =
                            Integer.parseInt(scanner.nextLine());

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

                } else if (opcao == 0) {

                    System.out.println("Sistema encerrado.");

                } else {

                    System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Erro: digite um número válido."
                );
            }
        }

        scanner.close();
    }
}

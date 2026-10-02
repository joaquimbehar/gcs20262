import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Predicate;

public class App {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter FORMATO_PESQUISA_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CadastroDepartamentos cadastroDepartamentos =
                new CadastroDepartamentos();

        CadastroFuncionarios cadastroFuncionarios =
                new CadastroFuncionarios();

        List<Custo> custos = new ArrayList<>();

        DadosExemplo.preencher(
                custos,
                cadastroDepartamentos,
                cadastroFuncionarios
        );

        int opcao = -1;

        while (opcao != 0) {

            System.out.println();
            System.out.println("=== SISTEMA DE CUSTOS ===");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Cadastrar custo");
            System.out.println("4 - Pesquisar custos");
            System.out.println("0 - Sair");

            try {

                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1) {

                    cadastrarFuncionario(
                            scanner,
                            cadastroDepartamentos,
                            cadastroFuncionarios
                    );

                } else if (opcao == 2) {

                    cadastroFuncionarios.listarFuncionarios();

                } else if (opcao == 3) {

                    cadastrarCusto(
                            scanner,
                            custos,
                            cadastroDepartamentos,
                            cadastroFuncionarios
                    );

                } else if (opcao == 4) {

                    pesquisarCustos(
                            scanner,
                            custos
                    );

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

    private static void cadastrarFuncionario(
            Scanner scanner,
            CadastroDepartamentos cadastroDepartamentos,
            CadastroFuncionarios cadastroFuncionarios
    ) {

        System.out.println();
        System.out.println("=== CADASTRAR FUNCIONÁRIO ===");

        System.out.print("Matrícula: ");

        int matricula =
                Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");

        String nome =
                scanner.nextLine().trim();

        // Temporário para testes.
        String iniciais = "LA";

        System.out.println();
        System.out.println("Departamentos:");

        cadastroDepartamentos.listarDepartamentos();

        System.out.print("Escolha o departamento: ");

        int escolhaDepartamento =
                Integer.parseInt(scanner.nextLine());

        Departamento departamento =
                cadastroDepartamentos.buscarPorIndice(
                        escolhaDepartamento - 1
                );

        if (departamento == null) {

            System.out.println(
                    "Departamento inválido."
            );

            return;
        }

        cadastroFuncionarios.cadastrarFuncionario(
                matricula,
                nome,
                iniciais,
                departamento
        );
    }

    private static void cadastrarCusto(
            Scanner scanner,
            List<Custo> custos,
            CadastroDepartamentos cadastroDepartamentos,
            CadastroFuncionarios cadastroFuncionarios
    ) {

        System.out.println();
        System.out.println("=== CADASTRAR CUSTO ===");

        System.out.print("Valor: R$ ");

        String valorInformado =
                scanner.nextLine()
                        .trim()
                        .replace(",", ".");

        double valor =
                Double.parseDouble(valorInformado);

        if (!Double.isFinite(valor) || valor <= 0) {

            System.out.println(
                    "O valor deve ser maior que zero."
            );

            return;
        }

        System.out.print("Descrição: ");

        String descricao =
                scanner.nextLine().trim();

        if (descricao.isEmpty()) {

            System.out.println(
                    "A descrição não pode ficar vazia."
            );

            return;
        }

        System.out.print("Categoria: ");

        String categoria =
                scanner.nextLine().trim();

        if (categoria.isEmpty()) {

            System.out.println(
                    "A categoria não pode ficar vazia."
            );

            return;
        }

        System.out.print(
                "Data (dd/MM/yyyy) ou ENTER para hoje: "
        );

        String dataInformada =
                scanner.nextLine().trim();

        LocalDate data;

        if (dataInformada.isEmpty()) {

            data = LocalDate.now();

        } else {

            try {

                data = LocalDate.parse(
                        dataInformada,
                        FORMATO_DATA
                );

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Data inválida. Utilize o formato dd/MM/yyyy."
                );

                return;
            }
        }

        System.out.println();
        System.out.println("Departamentos:");

        cadastroDepartamentos.listarDepartamentos();

        System.out.print("Escolha o departamento: ");

        int escolhaDepartamento =
                Integer.parseInt(scanner.nextLine());

        Departamento departamento =
                cadastroDepartamentos.buscarPorIndice(
                        escolhaDepartamento - 1
                );

        if (departamento == null) {

            System.out.println(
                    "Departamento inválido."
            );

            return;
        }

        System.out.println();
        System.out.println("Funcionários:");

        cadastroFuncionarios.listarFuncionarios();

        System.out.print(
                "Informe a matrícula do funcionário responsável: "
        );

        int matricula =
                Integer.parseInt(scanner.nextLine());

        Funcionario funcionario =
                cadastroFuncionarios.buscarPorMatricula(
                        matricula
                );

        if (funcionario == null) {

            System.out.println(
                    "Funcionário não encontrado."
            );

            return;
        }

        Custo custo = new Custo(
                valor,
                data,
                departamento,
                funcionario,
                descricao,
                categoria
        );

        custos.add(custo);

        System.out.println();
        System.out.println(
                "Custo cadastrado com sucesso."
        );
    }

    private static void pesquisarCustos(
            Scanner scanner,
            List<Custo> custos
    ) {

        if (custos.isEmpty()) {

            System.out.println();
            System.out.println(
                    "Nenhum custo cadastrado."
            );

            return;
        }

        System.out.println();
        System.out.println("=== PESQUISAR CUSTOS ===");
        System.out.println("1 - Pesquisar por descrição");
        System.out.println("2 - Pesquisar por categoria");
        System.out.println("3 - Pesquisar por data");
        System.out.println("4 - Pesquisar por departamento");
        System.out.println("0 - Voltar");

        System.out.print("Escolha uma opção: ");

        int opcaoPesquisa =
                Integer.parseInt(scanner.nextLine());

        if (opcaoPesquisa == 0) {
            return;
        }

        Predicate<Custo> filtro;

        if (opcaoPesquisa == 1) {

            System.out.print("Descrição: ");

            String descricao =
                    scanner.nextLine().trim();

            filtro = custo ->
                    contemTexto(
                            custo.getDescricao(),
                            descricao
                    );

        } else if (opcaoPesquisa == 2) {

            System.out.print("Categoria: ");

            String categoria =
                    scanner.nextLine().trim();

            filtro = custo ->
                    contemTexto(
                            custo.getCategoria(),
                            categoria
                    );

        } else if (opcaoPesquisa == 3) {

            System.out.print("Data (dd/MM/yyyy): ");

            String dataInformada =
                    scanner.nextLine().trim();

            try {

                LocalDate data = LocalDate.parse(
                        dataInformada,
                        FORMATO_PESQUISA_DATA
                );

                List<Custo> resultados =
                        PesquisaPorData.buscar(
                                custos,
                                data
                        );

                listarCustosFiltrados(
                        resultados,
                        custo -> true
                );

                return;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Data inválida. Informe uma data existente "
                                + "no formato dd/MM/yyyy."
                );

                return;
            }

        } else if (opcaoPesquisa == 4) {

            System.out.print(
                    "Nome completo do departamento: "
            );

            String departamento =
                    scanner.nextLine().trim();

            if (departamento.isEmpty()) {

                System.out.println(
                        "Informe o nome do departamento."
                );

                return;
            }

            List<Custo> resultados =
                    PesquisaPorDepartamento.buscar(
                            custos,
                            departamento
                    );

            listarCustosFiltrados(
                    resultados,
                    custo -> true
            );

            return;

        } else {

            System.out.println(
                    "Opção de pesquisa inválida."
            );

            return;
        }

        listarCustosFiltrados(
                custos,
                filtro
        );
    }

    private static void listarCustosFiltrados(
            List<Custo> custos,
            Predicate<Custo> filtro
    ) {

        List<Custo> resultados =
                custos.stream()
                        .filter(filtro)
                        .sorted(
                                Comparator.comparing(
                                        Custo::getData
                                ).reversed()
                        )
                        .toList();

        if (resultados.isEmpty()) {

            System.out.println();
            System.out.println(
                    "Nenhum registro de custo encontrado."
            );

            return;
        }

        System.out.println();
        System.out.println("=== RESULTADOS ===");

        for (Custo custo : resultados) {

            exibirCusto(custo);
        }
    }

    private static boolean contemTexto(
            String texto,
            String pesquisa
    ) {

        if (texto == null) {
            return false;
        }

        return texto
                .toLowerCase(Locale.ROOT)
                .contains(
                        pesquisa.toLowerCase(Locale.ROOT)
                );
    }

    private static void exibirCusto(
            Custo custo
    ) {

        System.out.println();
        System.out.println(
                "------------------------------"
        );

        System.out.println(
                "Descrição: " +
                        custo.getDescricao()
        );

        System.out.println(
                "Categoria: " +
                        custo.getCategoria()
        );

        System.out.printf(
                "Valor: R$ %.2f%n",
                custo.getValor()
        );

        System.out.println(
                "Data: " +
                        custo.getData()
                                .format(FORMATO_DATA)
        );

        System.out.println(
                "Departamento: " +
                        custo.getDepartamento().getNome()
        );

        System.out.println(
                "Funcionário: " +
                        custo.getFuncionario().getNome()
        );

        System.out.println(
                "Matrícula: " +
                        custo.getFuncionario().getMatricula()
        );

        System.out.println(
                "------------------------------"
        );
    }
}
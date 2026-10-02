import java.time.LocalDate;
import java.util.List;

public class DadosExemplo {

    public static void preencher(
            List<Custo> custos,
            CadastroDepartamentos cadastroDepartamentos,
            CadastroFuncionarios cadastroFuncionarios
    ) {

        // Evita duplicar os exemplos.
        if (!custos.isEmpty()) {
            return;
        }

        // A ordem corresponde à classe CadastroDepartamentos enviada.
        Departamento rh =
                cadastroDepartamentos.getDepartamentos().get(0);

        Departamento vendas =
                cadastroDepartamentos.getDepartamentos().get(1);

        Departamento compras =
                cadastroDepartamentos.getDepartamentos().get(2);

        List<Funcionario> funcionarios =
                cadastroFuncionarios.getFuncionarios();

        // Acrescenta funcionários à própria lista usada pelo sistema.
        if (funcionarios.isEmpty()) {

            funcionarios.add(
                    new Funcionario(
                            1001,
                            "Ana Silva",
                            "AS",
                            rh
                    )
            );

            funcionarios.add(
                    new Funcionario(
                            1002,
                            "Bruno Lima",
                            "BL",
                            vendas
                    )
            );

            funcionarios.add(
                    new Funcionario(
                            1003,
                            "Carla Souza",
                            "CS",
                            compras
                    )
            );
        }

        Funcionario funcionario1 = funcionarios.get(0);

        Funcionario funcionario2 =
                funcionarios.size() >= 2
                        ? funcionarios.get(1)
                        : funcionario1;

        Funcionario funcionario3 =
                funcionarios.size() >= 3
                        ? funcionarios.get(2)
                        : funcionario1;

        LocalDate hoje = LocalDate.now();

        LocalDate inicioMes =
                hoje.withDayOfMonth(1);

        custos.add(
                new Custo(
                        980.00,
                        inicioMes.minusMonths(1).plusDays(10),
                        vendas,
                        funcionario2,
                        "Impressora colorida",
                        "Aquisicao de bens"
                )
        );

        custos.add(
                new Custo(
                        240.00,
                        inicioMes.minusMonths(2).plusDays(5),
                        rh,
                        funcionario1,
                        "Reparo de computador",
                        "Manutencao de bens"
                )
        );

        custos.add(
                new Custo(
                        450.00,
                        inicioMes.minusMonths(1).plusDays(3),
                        compras,
                        funcionario3,
                        "Frete de materiais",
                        "Outros servicos"
                )
        );

        custos.add(
                new Custo(
                        1200.00,
                        hoje,
                        vendas,
                        funcionario2,
                        "Cadeiras de atendimento",
                        "Aquisicao de bens"
                )
        );

        custos.add(
                new Custo(
                        300.00,
                        hoje,
                        rh,
                        funcionario1,
                        "Limpeza da sala",
                        "Outros servicos"
                )
        );

        custos.add(
                new Custo(
                        180.00,
                        inicioMes.minusMonths(2).plusDays(15),
                        compras,
                        funcionario3,
                        "Reparo de armario",
                        "Manutencao de bens"
                )
        );

        custos.add(
                new Custo(
                        650.00,
                        inicioMes.minusMonths(1).plusDays(20),
                        rh,
                        funcionario1,
                        "Treinamento da equipe",
                        "Outros servicos"
                )
        );

        custos.add(
                new Custo(
                        90.00,
                        inicioMes.minusMonths(3).plusDays(7),
                        vendas,
                        funcionario2,
                        "Reparo de telefone",
                        "Manutencao de bens"
                )
        );

        custos.add(
                new Custo(
                        720.00,
                        hoje,
                        compras,
                        funcionario3,
                        "Monitor para compras",
                        "Aquisicao de bens"
                )
        );

        // Na inicialização vazia, Ana é do RH e registra este gasto para Vendas.
        custos.add(
                new Custo(
                        200.00,
                        inicioMes.minusMonths(1).plusDays(10),
                        vendas,
                        funcionario1,
                        "Transporte para reuniao",
                        "Outros servicos"
                )
        );

        custos.add(
                new Custo(
                        110.00,
                        inicioMes.minusMonths(2).plusDays(11),
                        rh,
                        funcionario1,
                        "Reparo de impressora",
                        "Manutencao de bens"
                )
        );

        custos.add(
                new Custo(
                        350.00,
                        inicioMes.minusMonths(1).plusDays(1),
                        compras,
                        funcionario3,
                        "Estante de materiais",
                        "Aquisicao de bens"
                )
        );
    }
}
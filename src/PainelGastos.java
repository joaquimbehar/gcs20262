import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainelGastos {

    private static final int LIMITE_RANKING = 3;

    public List<TotalFuncionario> ranking(List<Custo> custos) {
        Map<Integer, TotalFuncionario> rankingPorMatricula = new HashMap<>();

        for (Custo custo : custos) {
            Funcionario funcionario = custo.getFuncionario();

            if (funcionario == null) {
                continue;
            }

            TotalFuncionario tf = rankingPorMatricula.computeIfAbsent(
                    funcionario.getMatricula(),
                    matricula -> new TotalFuncionario(funcionario)
            );

            tf.adicionar(custo.getValor());
        }

        List<TotalFuncionario> ranking =
                new ArrayList<>(rankingPorMatricula.values());

        Collections.sort(ranking);

        int limite = Math.min(
                LIMITE_RANKING,
                ranking.size()
        );

        return new ArrayList<>(
                ranking.subList(0, limite)
        );
    }

    public Double totalDoMes(
            List<Custo> custos,
            LocalDate hoje
    ) {

        YearMonth mesAtual =
                YearMonth.from(hoje);

        Double total = 0.0;

        for (Custo custo : custos) {

            YearMonth mesDoCusto =
                    YearMonth.from(custo.getData());

            if (mesDoCusto.equals(mesAtual)) {
                total += custo.getValor();
            }
        }

        return total;
    }

    public Double totalPorDepartamento(
            List<Custo> custos,
            Departamento departamento,
            LocalDate hoje
    ) {

        YearMonth mesAtual =
                YearMonth.from(hoje);

        LocalDate inicioPeriodo =
                mesAtual.minusMonths(2).atDay(1);

        LocalDate fimPeriodo =
                mesAtual.plusMonths(1).atDay(1);

        Double total = 0.0;

        for (Custo custo : custos) {

            LocalDate data =
                    custo.getData();

            Departamento departamentoDoCusto =
                    custo.getDepartamento();

            boolean dentroDoPeriodo =
                    !data.isBefore(inicioPeriodo)
                            && data.isBefore(fimPeriodo);

            boolean mesmoDepartamento =
                    departamentoDoCusto != null
                            && departamentoDoCusto.getNome()
                            .equals(departamento.getNome());

            if (dentroDoPeriodo && mesmoDepartamento) {
                total += custo.getValor();
            }
        }

        return total;
    }

    public void exibir(
            Funcionario funcionarioAtual,
            List<Custo> custos,
            List<Departamento> departamentos
    ) {

        exibir(
                funcionarioAtual,
                custos,
                departamentos,
                LocalDate.now()
        );
    }

    public void exibir(
            Funcionario funcionarioAtual,
            List<Custo> custos,
            List<Departamento> departamentos,
            LocalDate hoje
    ) {

        YearMonth mesAtual =
                YearMonth.from(hoje);

        System.out.println(
                "=== PAINEL DE GASTOS ==="
        );

        if (funcionarioAtual == null) {

            System.out.println(
                    "Selecione um funcionario para visualizar o painel."
            );

            return;
        }

        System.out.println(
                "Funcionario: "
                        + funcionarioAtual.getNome()
                        + " ("
                        + funcionarioAtual.getIniciais()
                        + ")"
        );

        System.out.println(
                "Total da empresa no mes "
                        + mesAtual
                        + ": R$ "
                        + totalDoMes(custos, hoje)
        );

        System.out.println(
                "Totais por departamento: "
                        + mesAtual.minusMonths(2)
                        + " a "
                        + mesAtual
        );

        for (Departamento departamento : departamentos) {

            Double total =
                    totalPorDepartamento(
                            custos,
                            departamento,
                            hoje
                    );

            System.out.println(
                    departamento.getNome()
                            + ": R$ "
                            + total
            );
        }

        System.out.println(
                "Top 3 funcionarios - todos os custos cadastrados:"
        );

        List<TotalFuncionario> lideres =
                ranking(custos);

        if (lideres.isEmpty()) {

            System.out.println(
                    "Nenhum custo com funcionario cadastrado."
            );

            return;
        }

        for (int i = 0; i < lideres.size(); i++) {

            TotalFuncionario tf =
                    lideres.get(i);

            Funcionario funcionario =
                    tf.getFuncionario();

            System.out.println(
                    (i + 1)
                            + ". "
                            + funcionario.getNome()
                            + " ["
                            + funcionario.getMatricula()
                            + "]: R$ "
                            + tf.getTotal()
            );
        }
    }
}
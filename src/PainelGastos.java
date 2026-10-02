import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PainelGastos {

    private static final int LIMITE_RANKING = 3;
    private static final Locale LOCALE_BRASIL = Locale.forLanguageTag("pt-BR");

    public static class PosicaoRanking {

        private final Funcionario funcionario;
        private BigDecimal total;

        public PosicaoRanking(Funcionario funcionario) {
            this.funcionario = funcionario;
            this.total = BigDecimal.ZERO;
        }

        public Funcionario getFuncionario() {
            return funcionario;
        }

        public BigDecimal getTotal() {
            return total;
        }

        private void adicionarValor(BigDecimal valor) {
            total = total.add(valor);
        }
    }

    public List<PosicaoRanking> ranking(List<Custo> custos) {
        Map<Integer, PosicaoRanking> rankingPorMatricula = new HashMap<>();

        for (Custo custo : custos) {
            Funcionario funcionario = custo.getFuncionario();

            if (funcionario == null) {
                continue;
            }

            PosicaoRanking posicao = rankingPorMatricula.computeIfAbsent(
                    funcionario.getMatricula(),
                    matricula -> new PosicaoRanking(funcionario)
            );

            posicao.adicionarValor(custo.getValor());
        }

        List<PosicaoRanking> ranking = new ArrayList<>(rankingPorMatricula.values());

        ranking.sort(
                Comparator.comparing(PosicaoRanking::getTotal)
                        .reversed()
                        .thenComparingInt(posicao ->
                                posicao.getFuncionario().getMatricula())
        );

        int limite = Math.min(LIMITE_RANKING, ranking.size());

        return new ArrayList<>(ranking.subList(0, limite));
    }

    public BigDecimal totalDoMes(List<Custo> custos, LocalDate hoje) {
        YearMonth mesAtual = YearMonth.from(hoje);
        BigDecimal total = BigDecimal.ZERO;

        for (Custo custo : custos) {
            YearMonth mesDoCusto = YearMonth.from(custo.getData());

            if (mesDoCusto.equals(mesAtual)) {
                total = total.add(custo.getValor());
            }
        }

        return total;
    }

    public BigDecimal totalPorDepartamento(
            List<Custo> custos,
            Departamento departamento,
            LocalDate hoje
    ) {
        YearMonth mesAtual = YearMonth.from(hoje);

        LocalDate inicioPeriodo = mesAtual.minusMonths(2).atDay(1);
        LocalDate fimPeriodo = mesAtual.plusMonths(1).atDay(1);

        BigDecimal total = BigDecimal.ZERO;

        for (Custo custo : custos) {
            LocalDate data = custo.getData();
            Departamento departamentoDoCusto = custo.getDepartamento();

            boolean dentroDoPeriodo =
                    !data.isBefore(inicioPeriodo) && data.isBefore(fimPeriodo);

            boolean mesmoDepartamento =
                    departamentoDoCusto != null
                            && departamentoDoCusto.getNome()
                                    .equals(departamento.getNome());

            if (dentroDoPeriodo && mesmoDepartamento) {
                total = total.add(custo.getValor());
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
                LocalDate.now(),
                System.out
        );
    }

    public void exibir(
            Funcionario funcionarioAtual,
            List<Custo> custos,
            List<Departamento> departamentos,
            LocalDate hoje,
            PrintStream saida
    ) {
        NumberFormat moeda =
                NumberFormat.getCurrencyInstance(LOCALE_BRASIL);

        YearMonth mesAtual = YearMonth.from(hoje);

        saida.println("=== PAINEL DE GASTOS ===");

        if (funcionarioAtual == null) {
            saida.println(
                    "Selecione um funcionario para visualizar o painel."
            );
            return;
        }

        saida.println(
                "Funcionario: "
                        + funcionarioAtual.getNome()
                        + " ("
                        + funcionarioAtual.getIniciais()
                        + ")"
        );

        saida.println(
                "Total da empresa no mes "
                        + mesAtual
                        + ": "
                        + moeda.format(totalDoMes(custos, hoje))
        );

        saida.println(
                "Totais por departamento: "
                        + mesAtual.minusMonths(2)
                        + " a "
                        + mesAtual
        );

        for (Departamento departamento : departamentos) {
            BigDecimal total = totalPorDepartamento(
                    custos,
                    departamento,
                    hoje
            );

            saida.println(
                    departamento.getNome()
                            + ": "
                            + moeda.format(total)
            );
        }

        saida.println(
                "Top 3 funcionarios - todos os custos cadastrados:"
        );

        List<PosicaoRanking> lideres = ranking(custos);

        if (lideres.isEmpty()) {
            saida.println(
                    "Nenhum custo com funcionario cadastrado."
            );
            return;
        }

        for (int i = 0; i < lideres.size(); i++) {
            PosicaoRanking posicao = lideres.get(i);
            Funcionario funcionario = posicao.getFuncionario();

            saida.println(
                    (i + 1)
                            + ". "
                            + funcionario.getNome()
                            + " ["
                            + funcionario.getMatricula()
                            + "]: "
                            + moeda.format(posicao.getTotal())
            );
        }
    }
}
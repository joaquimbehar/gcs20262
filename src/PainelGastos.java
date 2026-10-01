import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

// EXERCICIO: contem dois defeitos intencionais. Leia DEFEITOS.md.
public class PainelGastos {
    public static class PosicaoRanking {
        private final Funcionario funcionario;
        private BigDecimal total = BigDecimal.ZERO;

        public PosicaoRanking(Funcionario funcionario) { this.funcionario = funcionario; }
        public Funcionario getFuncionario() { return funcionario; }
        public BigDecimal getTotal() { return total; }
    }

    public List<PosicaoRanking> ranking(List<Custo> custos) {
        Map<String, PosicaoRanking> porMatricula = new LinkedHashMap<>();
        for (Custo custo : custos) {
            Funcionario autor = custo.getFuncionario();
            if (autor == null) { continue; }
            PosicaoRanking posicao = porMatricula.get(autor.getMatricula());
            if (posicao == null) {
                posicao = new PosicaoRanking(autor);
                porMatricula.put(autor.getMatricula(), posicao);
            }
            posicao.total = posicao.total.add(custo.getValor());
        }
        List<PosicaoRanking> resultado = new ArrayList<>(porMatricula.values());
        resultado.sort((a, b) -> {
            int comparacao = b.total.compareTo(a.total);
            if (comparacao != 0) { return comparacao; }
            return a.funcionario.getMatricula().compareTo(b.funcionario.getMatricula());
        });
        return new ArrayList<>(resultado.subList(0, Math.min(3, resultado.size())));
    }

    public BigDecimal totalDoMes(List<Custo> custos, LocalDate hoje) {
        BigDecimal total = BigDecimal.ZERO;
        YearMonth mesAtual = YearMonth.from(hoje);
        for (Custo custo : custos) {
            if (custo.getData().getMonthValue() == mesAtual.getMonthValue()) {
                total = total.add(custo.getValor());
            }
        }
        return total;
    }

    public BigDecimal totalPorDepartamento(List<Custo> custos,
            Departamento departamento, LocalDate hoje) {
        LocalDate inicio = hoje.withDayOfMonth(1).minusMonths(3);
        LocalDate fim = hoje.withDayOfMonth(1).plusMonths(1);
        BigDecimal total = BigDecimal.ZERO;
        for (Custo custo : custos) {
            LocalDate data = custo.getData();
            boolean dentroDoPeriodo = !data.isBefore(inicio) && data.isBefore(fim);
            boolean mesmoDepartamento = custo.getDepartamento().getNome()
                    .equals(departamento.getNome());
            if (dentroDoPeriodo && mesmoDepartamento) {
                total = total.add(custo.getValor());
            }
        }
        return total;
    }

    public void exibir(Funcionario atual, List<Custo> custos,
            List<Departamento> departamentos) {
        exibir(atual, custos, departamentos, LocalDate.now(), System.out);
    }

    // Data e saida recebidas permitem testar sem depender do relogio.
    public void exibir(Funcionario atual, List<Custo> custos,
            List<Departamento> departamentos, LocalDate hoje, PrintStream saida) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        saida.println("=== PAINEL DE GASTOS ===");
        if (atual == null) {
            saida.println("Selecione um funcionario para visualizar o painel.");
            return;
        }
        saida.println("Funcionario: " + atual.getNome() + " (" + atual.getIniciais() + ")");
        saida.println("Total da empresa no mes " + YearMonth.from(hoje) + ": "
                + moeda.format(totalDoMes(custos, hoje)));
        saida.println("Totais por departamento: " + YearMonth.from(hoje).minusMonths(2)
                + " a " + YearMonth.from(hoje));
        for (Departamento departamento : departamentos) {
            saida.println(departamento.getNome() + ": "
                    + moeda.format(totalPorDepartamento(custos, departamento, hoje)));
        }
        saida.println("Top 3 funcionarios - todos os custos cadastrados:");
        List<PosicaoRanking> lideres = ranking(custos);
        if (lideres.isEmpty()) { saida.println("Nenhum custo com funcionario cadastrado."); }
        for (int i = 0; i < lideres.size(); i++) {
            PosicaoRanking posicao = lideres.get(i);
            saida.println((i + 1) + ". " + posicao.funcionario.getNome()
                    + " [" + posicao.funcionario.getMatricula() + "]: " + moeda.format(posicao.total));
        }
    }
}

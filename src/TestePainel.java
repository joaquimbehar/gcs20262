import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestePainel {
    private static int falhas;

    private static void conferir(String nome, String esperado, BigDecimal obtido) {
        if (new BigDecimal(esperado).compareTo(obtido) != 0) {
            falhas++;
            System.out.println("FALHOU: " + nome + "; esperado=" + esperado + "; obtido=" + obtido);
        }
    }

    public static void main(String[] args) {
        PainelGastos painel = new PainelGastos();
        Departamento rh = new Departamento("RH");
        Departamento vendas = new Departamento("Vendas");
        Departamento compras = new Departamento("Compras");
        LocalDate hoje = LocalDate.of(2026, 1, 15);
        List<Custo> custos = Arrays.asList(
                new Custo("10.10", LocalDate.of(2026, 1, 1), rh),
                new Custo("20.20", LocalDate.of(2026, 1, 31), vendas),
                new Custo("1000", LocalDate.of(2025, 1, 1), rh),
                new Custo("30", LocalDate.of(2025, 12, 31), rh),
                new Custo("40", LocalDate.of(2025, 11, 1), rh),
                new Custo("2000", LocalDate.of(2025, 10, 31), rh),
                new Custo("4000", LocalDate.of(2026, 2, 1), rh));
        conferir("mes e ano, todos os departamentos", "30.30", painel.totalDoMes(custos, hoje));
        conferir("tres meses com virada de ano e limites", "80.10", painel.totalPorDepartamento(custos, rh, hoje));
        conferir("separacao por departamento", "20.20", painel.totalPorDepartamento(custos, vendas, hoje));
        conferir("departamento sem custos", "0", painel.totalPorDepartamento(custos, compras, hoje));
        conferir("lista vazia", "0", painel.totalDoMes(Collections.<Custo>emptyList(), hoje));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        painel.exibir(new Funcionario("Ana Silva", "AS"), custos,
                Arrays.asList(rh, vendas, compras), hoje, new PrintStream(buffer));
        if (!buffer.toString().contains("Ana Silva (AS)") || !buffer.toString().contains("Compras:")) {
            falhas++;
        }
        buffer.reset();
        painel.exibir(null, custos, Arrays.asList(rh), hoje, new PrintStream(buffer));
        if (!buffer.toString().contains("Selecione um funcionario")) { falhas++; }
        Funcionario a = new Funcionario("001", "Mesmo Nome", "MN");
        Funcionario b = new Funcionario("002", "Mesmo Nome", "MN");
        Funcionario c = new Funcionario("003", "Carla", "C");
        Funcionario d = new Funcionario("004", "Daniel", "D");
        List<PainelGastos.PosicaoRanking> ranking = painel.ranking(Arrays.asList(
                new Custo("60", hoje.minusYears(1), rh, a),
                new Custo("40", hoje, vendas, new Funcionario("001", "Mesmo Nome", "MN")),
                new Custo("100", hoje, rh, b),
                new Custo("200", hoje, rh, c),
                new Custo("1", hoje, rh, d)));
        if (ranking.size() != 3
                || !ranking.get(0).getFuncionario().getMatricula().equals("003")
                || !ranking.get(1).getFuncionario().getMatricula().equals("001")
                || !ranking.get(2).getFuncionario().getMatricula().equals("002")) { falhas++; }
        conferir("soma por matricula em todo o historico", "100", ranking.get(1).getTotal());
        if (!painel.ranking(Collections.<Custo>emptyList()).isEmpty()) { falhas++; }
        if (painel.ranking(Arrays.asList(new Custo("1", hoje, rh, a))).size() != 1) { falhas++; }
        if (falhas > 0) { throw new AssertionError(falhas + " verificacao(oes) falharam"); }
        System.out.println("11 verificacoes passaram.");
    }
}

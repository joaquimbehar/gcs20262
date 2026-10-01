import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ExemploPainel {
    public static void main(String[] args) {
        Departamento rh = new Departamento("RH");
        Departamento vendas = new Departamento("Vendas");
        Departamento compras = new Departamento("Compras");
        Funcionario atual = new Funcionario("001", "Ana Silva", "AS");
        Funcionario bruno = new Funcionario("002", "Bruno Lima", "BL");
        Funcionario carla = new Funcionario("003", "Carla Souza", "CS");
        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        List<Custo> custos = Arrays.asList(
                new Custo("100.50", primeiroDia, rh, atual),
                new Custo("200.00", primeiroDia, vendas, bruno),
                new Custo("50.00", primeiroDia.minusMonths(1), rh, atual),
                new Custo("75.00", primeiroDia.minusMonths(2), vendas, carla),
                new Custo("999.00", primeiroDia.minusMonths(3), rh, bruno),
                new Custo("888.00", primeiroDia.minusYears(1), rh, carla));
        new PainelGastos().exibir(atual, custos, Arrays.asList(rh, vendas, compras));
    }
}

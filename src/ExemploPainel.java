import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ExemploPainel {
    public static void main(String[] args) {
        Departamento rh = new Departamento("RH");
        Departamento vendas = new Departamento("Vendas");
        Departamento compras = new Departamento("Compras");
        Funcionario atual = new Funcionario(001, "Ana Silva", "AS", rh);
        Funcionario bruno = new Funcionario(002, "Bruno Lima", "BL", vendas);
        Funcionario carla = new Funcionario(003, "Carla Souza", "CS", compras);
        String descricaoMaquinaLavar = "Maquina de Lavar";
        String descricaoSoftware = "Aplicativo de Custos";
        String descricaoLaptop = "Thinkpad Lenovo";
        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDia = hoje.withDayOfMonth(1);

        ArrayList<Custo> custos = new ArrayList<Custo>();

        custos.add(new Custo(100.50, hoje, rh, atual, descricaoMaquinaLavar, "Aquisição de item"));
        custos.add(new Custo(200.00, primeiroDia, vendas, bruno, descricaoSoftware, "Aquisição de Software"));
        custos.add(new Custo(900.50, primeiroDia.minusYears(1), compras, carla, descricaoLaptop, "Aquisição de Tecnologia"));

        new PainelGastos().exibir(atual, custos, Arrays.asList(rh, vendas, compras));
    }
}

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainPessoa8 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<RegistroCusto> custos = exemplos();
        ConsultasCustos consultas = new ConsultasCustos();
        String opcao = "";

        while (!opcao.equals("0")) {
            System.out.println("\n1 - Ranking de funcionarios");
            System.out.println("2 - Buscar por valor");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            if (!entrada.hasNextLine()) {
                break;
            }
            opcao = entrada.nextLine().trim();

            if (opcao.equals("1")) {
                List<TotalFuncionario> ranking = consultas.maioresTotais(custos);
                mostrarRanking(ranking);
            } else if (opcao.equals("2")) {
                try {
                    System.out.print("Valor minimo (ex.: 1250,50, sem ponto de milhar): ");
                    if (!entrada.hasNextLine()) {
                        break;
                    }
                    BigDecimal minimo = lerValor(entrada.nextLine());

                    System.out.print("Valor maximo: ");
                    if (!entrada.hasNextLine()) {
                        break;
                    }
                    BigDecimal maximo = lerValor(entrada.nextLine());

                    List<RegistroCusto> resultado = consultas.buscarPorValor(custos, minimo, maximo);
                    mostrarCustos(resultado);
                } catch (IllegalArgumentException erro) {
                    System.out.println("Valor invalido. Use numeros positivos ou zero, com minimo <= maximo.");
                } catch (ArithmeticException erro) {
                    System.out.println("Use no maximo duas casas decimais.");
                }
            } else if (!opcao.equals("0")) {
                System.out.println("Opcao invalida.");
            }
        }

        entrada.close();
    }

    private static BigDecimal lerValor(String texto) {
        texto = texto.trim().replace(',', '.');
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Informe um valor.");
        }

        int pontos = 0;
        int casasDecimais = 0;
        int algarismos = 0;
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            if (caractere == '.') {
                pontos++;
            } else if (caractere >= '0' && caractere <= '9') {
                algarismos++;
                if (pontos == 1) {
                    casasDecimais++;
                }
            } else {
                throw new IllegalArgumentException("Digite apenas o valor.");
            }
        }

        if (pontos > 1 || casasDecimais > 2 || algarismos == 0) {
            throw new IllegalArgumentException("Formato de valor invalido.");
        }
        return new BigDecimal(texto);
    }

    private static String formatarValor(BigDecimal valor) {
        Locale brasil = new Locale("pt", "BR");
        NumberFormat formato = NumberFormat.getCurrencyInstance(brasil);
        return formato.format(valor);
    }

    public static void mostrarRanking(List<TotalFuncionario> ranking) {
        if (ranking.isEmpty()) {
            System.out.println("Nenhum custo cadastrado.");
        }

        for (int i = 0; i < ranking.size(); i++) {
            TotalFuncionario total = ranking.get(i);
            Funcionario funcionario = total.getFuncionario();

            System.out.println(i + ". " + funcionario.getNome());
            System.out.println("Matricula: " + funcionario.getMatricula());
            System.out.println("Iniciais: " + funcionario.getIniciais());
            System.out.println("Total: " + formatarValor(total.getTotal()));
            System.out.println();
        }
    }

    public static void mostrarCustos(List<RegistroCusto> custos) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (custos.isEmpty()) {
            System.out.println("Nenhum registro nesse intervalo.");
        }

        for (int i = 0; i < custos.size(); i++) {
            RegistroCusto custo = custos.get(i);
            Funcionario funcionario = custo.getResponsavel();

            System.out.println("\nCodigo: " + custo.getCodigo());
            System.out.println("Valor: " + formatarValor(custo.getValor()));
            System.out.println("Descricao: " + custo.getDescricao());
            System.out.println("Data: " + custo.getData().format(formatoData));
            System.out.println("Categoria: " + custo.getCategoria());
            System.out.println("Departamento do custo: " + custo.getDepartamento());
            System.out.println("Funcionario: " + funcionario.getNome());
            System.out.println("Matricula: " + funcionario.getMatricula());
            System.out.println("Iniciais: " + funcionario.getIniciais());
            System.out.println("Departamento do funcionario: " + funcionario.getDepartamento());
        }
    }

    public static List<RegistroCusto> exemplos() {
        Funcionario ana = new Funcionario(101, "Ana Lima", "AL", "RH");
        Funcionario bruno = new Funcionario(102, "Bruno Reis", "BR", "Compras");
        Funcionario clara = new Funcionario(103, "Clara Souza", "CS", "Engenharia");
        Funcionario diego = new Funcionario(104, "Diego Melo", "DM", "Vendas");
        List<RegistroCusto> custos = new ArrayList<>();
        LocalDate hoje = LocalDate.now();
        custos.add(new RegistroCusto(1, new BigDecimal("980.00"), "Impressora colorida",
                hoje.minusDays(5), "Aquisicao de bens", "Vendas", ana));
        custos.add(new RegistroCusto(2, new BigDecimal("320.50"), "Reparo de computador",
                hoje.minusDays(4), "Manutencao de bens", "RH", ana));
        custos.add(new RegistroCusto(3, new BigDecimal("1500.00"), "Cadeiras de escritorio",
                hoje.minusDays(3), "Aquisicao de bens", "RH", bruno));
        custos.add(new RegistroCusto(4, new BigDecimal("2100.00"), "Computador",
                hoje.minusDays(2), "Aquisicao de bens", "Engenharia", clara));
        custos.add(new RegistroCusto(5, new BigDecimal("500.00"), "Instalacao de rede",
                hoje.minusDays(1), "Outros servicos", "Engenharia", clara));
        custos.add(new RegistroCusto(6, new BigDecimal("200.00"), "Transporte de material",
                hoje, "Outros servicos", "Vendas", diego));
        return custos;
    }
}

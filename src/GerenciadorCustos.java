import java.util.ArrayList;
import java.util.List;

class Custo {
    double valor;
    String descricao;
    String data; 
    String categoria;
    Departamento departamento;
    Funcionario funcionarioLogado;
    
    public Custo(double valor, String descricao, String data, String categoria, Departamento departamento, Funcionario funcionarioLogado) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.categoria = categoria;
        this.departamento = departamento;
        this.funcionarioLogado = funcionarioLogado;
    }

    @Override
    public String toString() {
        return "Custo: " + descricao + " | Valor: R$" + valor + " | Data: " + data + 
               " | Categoria: " + categoria + " | Depto: " + departamento.nome + 
               " | Registado por: " + funcionarioLogado.nome;
    }
}

public class GerenciadorCustos {
    static List<Custo> custos = new ArrayList<>();

    public static void adicionarCusto(Custo novoCusto) {
        custos.add(novoCusto);
    }

    public static boolean excluirUltimoCusto() {
        if (!custos.isEmpty()) {
            Custo removido = custos.remove(custos.size() - 1);
            System.out.println("Custo excluído com sucesso: " + removido.descricao);
            return true;
        }
        System.out.println("Não há custos para excluir.");
        return false;
    }
}
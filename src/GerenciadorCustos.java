import java.math.BigDecimal;
import java.time.LocalDate;
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

    public Custo(double d, LocalDate hoje, Departamento rh, Funcionario atual) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Custo: " + descricao + " | Valor: R$" + valor + " | Data: " + data + 
               " | Categoria: " + categoria + " | Depto: " + departamento.getNome() + 
               " | Registado por: " + funcionarioLogado.getNome();
    }

    public LocalDate getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }

    public Departamento getDepartamento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartamento'");
    }

    public BigDecimal getValor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValor'");
    }

    public Funcionario getFuncionario() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFuncionario'");
    }
}

public class GerenciadorCustos {
    // Lista em memória que guarda os dados de cada custo
    static List<Custo> custos = new ArrayList<>();

    // Método atualizado para adicionar um custo com validação (Segunda contribuição)
    public static boolean adicionarCusto(Custo novoCusto) {
        if (novoCusto.valor <= 0) {
            System.out.println("Erro: O valor do custo deve ser maior que zero.");
            return false;
        }
        if (novoCusto.descricao == null || novoCusto.descricao.trim().isEmpty()) {
             System.out.println("Erro: A descrição do custo não pode ficar em branco.");
             return false;
        }
        
        custos.add(novoCusto);
        System.out.println("Custo cadastrado com sucesso!");
        return true;
    }

    // Regra da Pessoa 3: Excluir somente o último custo inserido
    public static boolean excluirUltimoCusto() {
        if (!custos.isEmpty()) {
            // Remove sempre o último elemento da lista
            Custo removido = custos.remove(custos.size() - 1);
            System.out.println("Custo excluído com sucesso: " + removido.descricao);
            return true;
        }
        System.out.println("Não há custos para excluir.");
        return false;
    }
}
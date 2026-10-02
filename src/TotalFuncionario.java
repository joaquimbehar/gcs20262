public class TotalFuncionario implements Comparable <TotalFuncionario>{
    private final Funcionario funcionario;
    private double total;

    public TotalFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.total = 0.00;
    }

    public void adicionar(double valor) {
        this.total += valor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public double getTotal() {
        return total;
    }

     
    @Override
    public int compareTo(TotalFuncionario outro) {
        if (outro.getTotal()!=this.total){
            return 1;
        }

        return 0;
    }

    // Facilita a exibição dos dados no painel do console
    @Override
    public String toString() {
       
        return String.format("%s - R$ %.2f", funcionario.getNome(), total);
    }

}

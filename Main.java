import java.util.Scanner;
 
// Menu principal (Pessoa 9): conecta todas as partes do sistema.
public class Main {
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int opcao = -1;
 
        while (opcao != 0) {
            System.out.println("\n===== CONTROLE DE CUSTOS =====");
            System.out.println("Usuario atual: " + sistema.funcionarioAtual.getNome());
            System.out.println("1  - Escolher/trocar funcionario");
            System.out.println("2  - Cadastrar funcionario");
            System.out.println("3  - Cadastrar custo");
            System.out.println("4  - Excluir ultimo custo");
            System.out.println("5  - Buscar por descricao");
            System.out.println("6  - Buscar por categoria");
            System.out.println("7  - Buscar por data");
            System.out.println("8  - Buscar por departamento");
            System.out.println("9  - Painel de gastos");
            System.out.println("10 - Ranking dos 3 funcionarios");
            System.out.println("11 - Buscar por valor minimo e maximo (extra 1)");
            System.out.println("12 - Resumo por categoria (extra 2)");
            System.out.println("0  - Sair");
            System.out.print("Escolha uma opcao: ");
 
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
 
            // Cada case chama a parte de um colega.
            // Quando a parte estiver pronta, troque a mensagem pela chamada real.
            // Exemplo: case 1: EscolhaUsuario.escolher(sistema, scanner); break;
            switch (opcao) {
                case 1:
                    System.out.println("(falta integrar: Pessoa 2)");
                    break;
                case 2:
                    System.out.println("(falta integrar: Pessoa 2)");
                    break;
                case 3:
                    System.out.println("(falta integrar: Pessoa 4)");
                    break;
                case 4:
                    System.out.println("(falta integrar: Pessoa 4)");
                    break;
                case 5:
                    System.out.println("(falta integrar: Pessoa 5)");
                    break;
                case 6:
                    System.out.println("(falta integrar: Pessoa 5)");
                    break;
                case 7:
                    System.out.println("(falta integrar: Pessoa 6)");
                    break;
                case 8:
                    System.out.println("(falta integrar: Pessoa 6)");
                    break;
                case 9:
                    System.out.println("(falta integrar: Pessoa 7)");
                    break;
                case 10:
                    System.out.println("(falta integrar: Pessoa 8)");
                    break;
                case 11:
                    System.out.println("(falta integrar: Pessoa 8)");
                    break;
                case 12:
                    ResumoCategoria.exibir(sistema);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
}
 
import java.util.ArrayList;
 
// Função extra 2 (Pessoa 9): resumo dos gastos por categoria.
public class ResumoCategoria {
 
    public static void exibir(Sistema sistema) {
        if (sistema.custos.size() == 0) {
            System.out.println("Nenhum custo cadastrado.");
            return;
        }
 
        // 1) Descobrir quais categorias existem (sem repetir)
        ArrayList<String> categorias = new ArrayList<>();
        for (int i = 0; i < sistema.custos.size(); i++) {
            String cat = sistema.custos.get(i).getCategoria();
            boolean jaExiste = false;
            for (int j = 0; j < categorias.size(); j++) {
                if (categorias.get(j).equalsIgnoreCase(cat)) {
                    jaExiste = true;
                }
            }
            if (!jaExiste) {
                categorias.add(cat);
            }
        }
 
        // 2) Para cada categoria, somar os valores e contar os custos
        System.out.println("\n--- RESUMO DE GASTOS POR CATEGORIA ---");
        double totalGeral = 0;
        for (int i = 0; i < categorias.size(); i++) {
            String categoria = categorias.get(i);
            double soma = 0;
            int quantidade = 0;
            for (int j = 0; j < sistema.custos.size(); j++) {
                Custo c = sistema.custos.get(j);
                if (c.getCategoria().equalsIgnoreCase(categoria)) {
                    soma = soma + c.getValor();
                    quantidade++;
                }
            }
            System.out.printf("%s: %d custo(s) - R$ %.2f%n", categoria, quantidade, soma);
            totalGeral = totalGeral + soma;
        }
        System.out.printf("TOTAL GERAL: R$ %.2f%n", totalGeral);
    }
}
 
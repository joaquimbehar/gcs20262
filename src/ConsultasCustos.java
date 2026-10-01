import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ConsultasCustos {

    public List<TotalFuncionario> maioresTotais(List<RegistroCusto> custos) {
        List<TotalFuncionario> totais = new ArrayList<>();

        for (int i = 0; i < custos.size(); i++) {
            RegistroCusto custo = custos.get(i);
            Funcionario funcionario = custo.getResponsavel();
            int posicao = -1;

            // Procura se esse funcionario ja tem um total na lista.
            for (int j = 0; j < totais.size(); j++) {
                int matricula = totais.get(j).getFuncionario().getMatricula();
                if (matricula == funcionario.getMatricula()) {
                    posicao = j;
                    break;
                }
            }

            if (posicao == -1) {
                TotalFuncionario novoTotal = new TotalFuncionario(funcionario);
                totais.add(novoTotal);
                posicao = totais.size() - 1;
            }

            totais.get(posicao).adicionar(custo.getValor());
        }

        // Coloca o maior total na frente, trocando os elementos de lugar.
        for (int i = 0; i < totais.size() - 1; i++) {
            int maior = i;

            for (int j = i + 1; j < totais.size(); j++) {
                int comparacao = totais.get(j).getTotal().compareTo(totais.get(maior).getTotal());

                if (comparacao > 0) {
                    maior = j;
                } else if (comparacao == 0) {
                    int matriculaAtual = totais.get(j).getFuncionario().getMatricula();
                    int matriculaMaior = totais.get(maior).getFuncionario().getMatricula();
                    if (matriculaAtual < matriculaMaior) {
                        maior = j;
                    }
                }
            }

            TotalFuncionario auxiliar = totais.get(i);
            totais.set(i, totais.get(maior));
            totais.set(maior, auxiliar);
        }

        List<TotalFuncionario> ranking = new ArrayList<>();
        for (int i = 0; i < totais.size() && i < 3; i++) {
            ranking.add(totais.get(i));
        }

        return ranking;
    }

    public List<RegistroCusto> buscarPorValor(List<RegistroCusto> custos,
            BigDecimal minimo, BigDecimal maximo) {
        if (minimo == null || maximo == null) {
            throw new IllegalArgumentException("Informe os dois valores.");
        }
        if (minimo.signum() < 0 || maximo.signum() < 0) {
            throw new IllegalArgumentException("Os valores nao podem ser negativos.");
        }
        if (minimo.compareTo(maximo) > 0) {
            throw new IllegalArgumentException("O minimo nao pode ser maior que o maximo.");
        }

        minimo = minimo.setScale(2, RoundingMode.UNNECESSARY);
        maximo = maximo.setScale(2, RoundingMode.UNNECESSARY);
        List<RegistroCusto> encontrados = new ArrayList<>();

        for (int i = 0; i < custos.size(); i++) {
            RegistroCusto custo = custos.get(i);
            // compareTo retorna negativo, zero ou positivo ao comparar dois valores.
            if (custo.getValor().compareTo(minimo) > 0
                    && custo.getValor().compareTo(maximo) <= 0) {
                encontrados.add(custo);
            }
        }

        // Organiza os resultados pela data, do mais recente para o mais antigo.
        for (int i = 0; i < encontrados.size() - 1; i++) {
            int maisRecente = i;

            for (int j = i + 1; j < encontrados.size(); j++) {
                RegistroCusto atual = encontrados.get(j);
                RegistroCusto escolhido = encontrados.get(maisRecente);

                if (atual.getData().isAfter(escolhido.getData())) {
                    maisRecente = j;
                } else if (atual.getData().equals(escolhido.getData())) {
                    if (atual.getCodigo() > escolhido.getCodigo()) {
                        maisRecente = j;
                    }
                }
            }

            RegistroCusto auxiliar = encontrados.get(i);
            encontrados.set(i, encontrados.get(maisRecente));
            encontrados.set(maisRecente, auxiliar);
        }

        return encontrados;
    }
}

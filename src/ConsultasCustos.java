import java.util.ArrayList;
import java.util.List;

public class ConsultasCustos {

    public List<TotalFuncionario> maioresTotais(List<Custo> custos) {
        List<TotalFuncionario> totais = new ArrayList<>();

        for (int i = 0; i < custos.size(); i++) {
            Custo custo = custos.get(i);
            Funcionario funcionario = custo.getFuncionario();
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
            //totais.get(posicao).adicionar(custo.getValor());
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

    public List<Custo> buscarPorValor(
        List<Custo> custos,
        int minimo,
        int maximo
) {

    if (minimo < 0 || maximo < 0) {
        throw new IllegalArgumentException(
                "Os valores nao podem ser negativos."
        );
    }

    if (minimo > maximo) {
        throw new IllegalArgumentException(
                "O minimo nao pode ser maior que o maximo."
        );
    }

    List<Custo> encontrados = new ArrayList<>();

    for (int i = 0; i < custos.size(); i++) {

        Custo custo = custos.get(i);

        if (custo.getValor() >= minimo && custo.getValor() <= maximo) {

            encontrados.add(custo);
        }
    }

    // Organiza do mais recente para o mais antigo.
    for (int i = 0; i < encontrados.size() - 1; i++) {

        int maisRecente = i;

        for (int j = i + 1; j < encontrados.size(); j++) {

            Custo atual =
                    encontrados.get(j);

            Custo escolhido =
                    encontrados.get(maisRecente);

            if (atual.getData().isAfter(
                    escolhido.getData()
            )) {

                maisRecente = j;
            }
        }

        Custo auxiliar =
                encontrados.get(i);

        encontrados.set(
                i,
                encontrados.get(maisRecente)
        );

        encontrados.set(
                maisRecente,
                auxiliar
        );
    }

    return encontrados;
}
}

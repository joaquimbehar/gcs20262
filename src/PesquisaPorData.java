import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PesquisaPorData {

    public static List<Custo> buscar(
            List<Custo> custos,
            LocalDate data
    ) {

        List<Custo> resultados = new ArrayList<>();

        for (Custo custo : custos) {

            if (custo.getData().equals(data)) {
                resultados.add(custo);
            }
        }

        ordenarMaisRecentesPrimeiro(resultados);

        return resultados;
    }

    public static void ordenarMaisRecentesPrimeiro(
            List<Custo> resultados
    ) {

        for (int i = 1; i < resultados.size(); i++) {

            Custo atual = resultados.get(i);
            int j = i - 1;

            while (
                    j >= 0
                    && resultados.get(j).getData()
                            .isBefore(atual.getData())
            ) {

                resultados.set(
                        j + 1,
                        resultados.get(j)
                );

                j--;
            }

            resultados.set(j + 1, atual);
        }
    }
}
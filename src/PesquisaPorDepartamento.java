import java.util.ArrayList;
import java.util.List;

public class PesquisaPorDepartamento {

    public static List<Custo> buscar(
            List<Custo> custos,
            String nomeDepartamento
    ) {

        if (
                nomeDepartamento == null
                || nomeDepartamento.trim().isEmpty()
        ) {
            throw new IllegalArgumentException(
                    "Informe o nome do departamento."
            );
        }

        List<Custo> resultados = new ArrayList<>();

        for (Custo custo : custos) {

            Departamento departamento =
                    custo.getDepartamento();

            if (
                    departamento != null
                    && departamento.getNome().equalsIgnoreCase(
                            nomeDepartamento.trim()
                    )
            ) {
                resultados.add(custo);
            }
        }

        PesquisaPorData.ordenarMaisRecentesPrimeiro(
                resultados
        );

        return resultados;
    }
}
import java.util.ArrayList;

public class CadastroDepartamentos {

    private ArrayList<Departamento> departamentos;

    public CadastroDepartamentos() {
        departamentos = new ArrayList<>();

        cadastrarDepartamentosIniciais();
    }

    private void cadastrarDepartamentosIniciais() {

        departamentos.add(new Departamento("RH"));
        departamentos.add(new Departamento("Vendas"));
        departamentos.add(new Departamento("Compras"));
        departamentos.add(new Departamento("Expedição"));
        departamentos.add(new Departamento("Engenharia"));
        departamentos.add(new Departamento("Produção"));
    }

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void listarDepartamentos() {

        for (int i = 0; i < departamentos.size(); i++) {
            System.out.println(
                    (i + 1) + " - " + departamentos.get(i).getNome()
            );
        }
    }

    public Departamento buscarPorIndice(int indice) {

        if (indice >= 1 && indice <= departamentos.size()) {
            return departamentos.get(indice - 1);
        }

        return null;
    }
}

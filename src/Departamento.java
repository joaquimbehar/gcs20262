public class Departamento {
    private final String nome;

    public Departamento(String nome) { this.nome = nome; }
    public String getNome() { return nome; }

    private String nome;

    public Departamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

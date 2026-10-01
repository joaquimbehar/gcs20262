public class TesteEscolhaUsuario {
    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    public static void main(String[] args) {
        CadastroDepartamentos departamentos = new CadastroDepartamentos();
        CadastroFuncionarios cadastro = new CadastroFuncionarios();
        EscolhaUsuario escolha = new EscolhaUsuario(cadastro);
        Departamento rh = departamentos.buscarPorIndice(1);
        Departamento producao = departamentos.buscarPorIndice(6);

        conferir(rh.getNome().equals("RH"), "Primeiro departamento incorreto");
        conferir(producao.getNome().equals("Produção"), "Último departamento incorreto");
        conferir(departamentos.buscarPorIndice(0) == null
                && departamentos.buscarPorIndice(7) == null, "Aceitou departamento inexistente");
        conferir(escolha.getFuncionarioAtual() == null, "Selecionou usuário sem escolha");
        conferir(!escolha.selecionarFuncionario(1), "Selecionou em cadastro vazio");

        conferir(cadastro.cadastrarFuncionario(1, "  Ana Silva  ", rh), "Cadastro falhou");
        conferir(cadastro.cadastrarFuncionario(2, "Bruno Lima", producao), "Segundo cadastro falhou");
        conferir(!cadastro.cadastrarFuncionario(1, "Outra Pessoa", producao), "Aceitou duplicidade");
        conferir(!cadastro.cadastrarFuncionario(0, "Nome", rh), "Aceitou matrícula zero");
        conferir(!cadastro.cadastrarFuncionario(-1, "Nome", rh), "Aceitou matrícula negativa");
        conferir(!cadastro.cadastrarFuncionario(3, " ", rh), "Aceitou nome vazio");
        conferir(!cadastro.cadastrarFuncionario(3, "Nome", null), "Aceitou departamento nulo");
        conferir(cadastro.getFuncionarios().size() == 2, "Cadastros inválidos alteraram a lista");

        conferir(escolha.selecionarFuncionario(1), "Seleção falhou");
        Funcionario ana = escolha.getFuncionarioAtual();
        conferir(ana == cadastro.buscarPorMatricula(1), "Não reutilizou o funcionário cadastrado");
        conferir(ana.getNome().equals("Ana Silva") && ana.getIniciais().equals("AS"), "Nome/iniciais incorretos");
        conferir(!escolha.selecionarFuncionario(99) && escolha.getFuncionarioAtual() == ana,
                "Matrícula inexistente alterou usuário atual");
        conferir(cadastro.cadastrarFuncionario(3, "Carla Souza", rh), "Novo cadastro falhou");
        conferir(escolha.getFuncionarioAtual() == ana, "Cadastro trocou usuário atual");
        conferir(escolha.selecionarFuncionario(2)
                && escolha.getFuncionarioAtual().getDepartamento() == producao, "Troca falhou");
        conferir(escolha.selecionarFuncionario(3), "Não selecionou funcionário recém-cadastrado");
        conferir(ana.getMatricula() == 1, "Troca modificou referência do usuário anterior");
        System.out.println("Testes de cadastro e escolha de usuário passaram.");
    }
}

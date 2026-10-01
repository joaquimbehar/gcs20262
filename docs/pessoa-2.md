# Pessoa 2 — Escolha do usuário

Implementação em Java Console, sem dependências e com dados apenas em memória.

Com um JDK no PATH, execute na raiz do projeto:

```powershell
javac -encoding UTF-8 -d out -sourcepath src src/App.java
java -cp out App
```

O menu oferece cadastro, listagem e escolha/troca de funcionário por matrícula.
Começa com Ana (101), Bruno (102) e Carla (103), sem usuário selecionado.
As iniciais são geradas com a primeira letra de cada palavra do nome.
Na escolha, digite 0 para cancelar. Matrícula inexistente mantém a seleção anterior.
Cadastrar um funcionário não altera a seleção; ele fica disponível na lista.
Os dados são descartados ao encerrar o programa.

`EscolhaUsuario` guarda o funcionário atual. Para integrar os custos e o painel,
use `getFuncionarioAtual()` na mesma instância criada pelo menu. O retorno é
`null` até a primeira seleção. Cada custo deve guardar a referência ao funcionário
selecionado no momento do cadastro, para preservar seu autor após uma troca.

Correções necessárias no código existente: remover declarações duplicadas de
Funcionario/Departamento, rejeitar matrícula repetida e alinhar a escolha dos
departamentos à numeração de 1 a 6 exibida no menu.

Para executar os testes desta parte:

```powershell
javac -encoding UTF-8 -d out -sourcepath src tests/TesteEscolhaUsuario.java
java -cp out TesteEscolhaUsuario
```

A compilação de todos os arquivos (`src/*.java`) ainda depende de correções nas
outras partes: classe Custo duplicada, acesso a campos incompatíveis no
GerenciadorCustos e uso de matrícula textual/construtores antigos nos exemplos,
testes e ranking do painel. Os comandos acima compilam apenas esta funcionalidade
e suas dependências.

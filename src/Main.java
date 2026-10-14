package src;

// Classe Main responsável por iniciar a aplicação, receber os dados do usuário, chamar os métodos do Gerenciador, receber seus retornos e exibir os resultados.

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Crio um único Gerenciador para armazenar e gerenciar os produtos
        Gerenciador gerenciador = new Gerenciador();

        //=================================================================================================
        // 0 - INICIALIZAÇÃO E MENU PRINCIPAL
        // Exibo as opções disponíveis para o usuário.
        // A variável opcao controla o laço while.
        // Enquanto opcao for diferente de 0, o menu continuará sendo exibido.
        //=================================================================================================

        System.out.println("===================================");
        System.out.println("Bem-vindo ao Mini Estoque");
        System.out.printf("===================================%n%n");

        int opcao = -1;

        while (opcao != 0) {

            System.out.printf("%n========== MENU PRINCIPAL ==========%n");
            System.out.println("1 - Cadastrar Produtos.");
            System.out.println("2 - Exibir Produtos Cadastrados.");
            System.out.println("3 - Buscar por um Produto.");
            System.out.println("4 - Editar um Produto.");
            System.out.println("5 - Deletar um Produto.");
            System.out.println("6 - Exibir a quantidade de produtos cadastrados.");
            System.out.println("0 - Sair.");
            System.out.println("====================================");

            //=================================================================================================
            // TRATAMENTO DE EXCEÇÕES DO MENU E DAS OPERAÇÕES
            // Utilizo um único try para proteger a leitura do menu e as operações do switch.
            // InputMismatchException trata valores incompatíveis com nextInt() ou nextDouble().
            // IllegalArgumentException trata valores que violam as regras de negócio do Produto.
            // Se ocorrer uma exceção, o catch mostra o erro e o while retorna ao menu.
            //=================================================================================================

            try {

                System.out.print("Digite sua opção: ");
                opcao = teclado.nextInt();
                teclado.nextLine(); // Consome o Enter pendente.

                switch (opcao) {

                    //=========================================================================================
                    // 0 - ENCERRAR O PROGRAMA
                    // Quando o usuário digita 0, o while será encerrado na próxima verificação.
                    //=========================================================================================

                    case 0:
                        System.out.println("Encerrando o Mini Estoque...");
                        break;

                    //=========================================================================================
                    // 1 - CADASTRAR PRODUTOS
                    // Utilizo o Scanner para receber descrição, preço e quantidade.
                    // Crio um objeto Produto com os dados informados.
                    // O construtor de Produto chama os setters para validar os dados.
                    // Depois, envio o objeto ao Gerenciador para adicioná-lo ao ArrayList.
                    //=========================================================================================

                    case 1:

                        System.out.print("Digite o nome do produto: ");
                        String nome = teclado.nextLine();

                        System.out.print("Digite o preço do produto: ");
                        double preco = teclado.nextDouble();

                        System.out.print("Digite a quantidade (em und) do produto: ");
                        int quantidade = teclado.nextInt();

                        // Cria o objeto e executa as validações do construtor.
                        Produto prod = new Produto(nome, preco, quantidade);

                        // Envia o objeto criado para a lista do Gerenciador.
                        gerenciador.adicionarProdutos(prod);

                        System.out.println("Produto cadastrado com sucesso!");
                        break;

                    //=========================================================================================
                    // 2 - EXIBIR PRODUTOS CADASTRADOS
                    // Chamo o método exibirProdutos(), que retorna um ArrayList de produtos.
                    // Utilizo o for-each para percorrer os objetos da lista.
                    // Os getters permitem acessar os atributos privados de cada produto.
                    // A Main é responsável por definir como esses dados serão exibidos.
                    //=========================================================================================

                    case 2:

                        System.out.println("========== PRODUTOS CADASTRADOS ==========");

                        if (gerenciador.exibirProdutos().isEmpty()) {
                            System.out.println("Nenhum produto cadastrado.");
                        } else {

                            for (Produto produto : gerenciador.exibirProdutos()) {

                                System.out.printf("%nID: %d%n", produto.getId());
                                System.out.printf("Descrição: %s%n", produto.getDescricao());
                                System.out.printf("Quantidade: %d%n", produto.getQuantidade());
                                System.out.printf("Preço: R$ %.2f%n", produto.getPreco());
                                System.out.println("-----------------------------------");
                            }
                        }

                        break;

                    //=========================================================================================
                    // 3 - BUSCAR UM PRODUTO
                    // Recebo o ID informado pelo usuário através do Scanner.
                    // O método buscarProduto() procura o objeto no ArrayList.
                    // Guardo o retorno em uma variável do tipo Produto.
                    // Se o retorno não for null, exibo os dados usando os getters.
                    // Caso seja null, significa que nenhum produto foi encontrado com aquele ID.
                    //=========================================================================================

                    case 3:

                        System.out.print("Digite o ID do produto que deseja buscar: ");
                        int idProd = teclado.nextInt();

                        Produto prodEncontrado = gerenciador.buscarProduto(idProd);

                        if (prodEncontrado != null) {

                            System.out.println("========== PRODUTO ENCONTRADO ==========");

                            System.out.printf("%nID: %d%n", prodEncontrado.getId());
                            System.out.printf("Descrição: %s%n", prodEncontrado.getDescricao());
                            System.out.printf("Quantidade: %d%n", prodEncontrado.getQuantidade());
                            System.out.printf("Preço: R$ %.2f%n", prodEncontrado.getPreco());

                        } else {
                            System.out.println("Produto não encontrado!");
                        }

                        break;

                    //=========================================================================================
                    // 4 - EDITAR UM PRODUTO
                    // Recebo o ID do produto que o usuário deseja editar.
                    // Utilizo um segundo switch para escolher qual atributo será alterado.
                    // Cada método de edição do Gerenciador retorna um boolean:
                    // true  = produto encontrado e alteração realizada.
                    // false = produto não encontrado.
                    // Guardo o retorno em uma variável boolean e uso if/else para exibir o resultado.
                    // Os setters do Produto continuam responsáveis por validar os novos valores.
                    //=========================================================================================

                    case 4:

                        System.out.print("Digite o ID do produto que deseja alterar: ");
                        int idProdEdit = teclado.nextInt();

                        // Verifico a existência do produto antes de apresentar as opções de edição.
                        if (gerenciador.buscarProduto(idProdEdit) == null) {
                            System.out.println("Produto não encontrado!");
                            break;
                        }

                        System.out.println("O que você deseja editar?");
                        System.out.println("1 - Descrição");
                        System.out.println("2 - Preço");
                        System.out.println("3 - Quantidade");

                        System.out.print("Digite sua opção: ");
                        int opcaoProd = teclado.nextInt();
                        teclado.nextLine(); // Evita que o nextLine da descrição leia o Enter anterior.

                        switch (opcaoProd) {

                            //---------------------------------------------------------------------------------
                            // 4.1 - EDITAR DESCRIÇÃO
                            //---------------------------------------------------------------------------------

                            case 1:

                                System.out.print("Digite a nova descrição do produto: ");
                                String novaDescProd = teclado.nextLine();

                                boolean altDescricao = gerenciador.editarDescricao(
                                    idProdEdit, novaDescProd
                                );

                                if (altDescricao) {
                                    System.out.println("Descrição alterada com sucesso!");
                                } else {
                                    System.out.println("Produto não encontrado!");
                                }

                                break;

                            //---------------------------------------------------------------------------------
                            // 4.2 - EDITAR PREÇO
                            //---------------------------------------------------------------------------------

                            case 2:

                                System.out.print("Digite o novo preço: ");
                                double novoPreco = teclado.nextDouble();

                                boolean altPreco = gerenciador.editarPreco(
                                    idProdEdit, novoPreco
                                );

                                if (altPreco) {
                                    System.out.println("Preço alterado com sucesso!");
                                } else {
                                    System.out.println("Produto não encontrado!");
                                }

                                break;

                            //---------------------------------------------------------------------------------
                            // 4.3 - EDITAR QUANTIDADE
                            //---------------------------------------------------------------------------------

                            case 3:

                                System.out.print("Digite a nova quantidade: ");
                                int novaQuantidade = teclado.nextInt();

                                boolean altQuantidade = gerenciador.editarQuantidade(
                                    idProdEdit, novaQuantidade
                                );

                                if (altQuantidade) {
                                    System.out.println("Quantidade alterada com sucesso!");
                                } else {
                                    System.out.println("Produto não encontrado!");
                                }

                                break;

                            default:
                                System.out.println("Opção de edição inválida!");
                                break;
                        }

                        // Encerra o case 4 do switch principal.
                        break;

                    //=========================================================================================
                    // 5 - DELETAR UM PRODUTO
                    // Recebo o ID do produto que o usuário deseja excluir.
                    // Chamo o método deletarProduto() do Gerenciador.
                    // O método retorna um boolean informando se a exclusão foi realizada.
                    // Utilizo if/else para verificar esse retorno e apresentar a mensagem ao usuário.
                    //=========================================================================================

                    case 5:

                        System.out.print("Digite o ID do produto que deseja excluir: ");
                        int idProdEx = teclado.nextInt();

                        boolean deletado = gerenciador.deletarProduto(idProdEx);

                        if (deletado) {
                            System.out.println("Produto deletado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado!");
                        }

                        break;

                    //=========================================================================================
                    // 6 - CONTAR PRODUTOS
                    // Chamo o método contarProdutos(), que retorna um int.
                    // Guardo o resultado em uma variável e exibo a quantidade de produtos cadastrados.
                    // A contagem representa o número de produtos da lista, não a soma das unidades.
                    //=========================================================================================

                    case 6:

                        int qtdProdutos = gerenciador.contarProdutos();

                        System.out.printf(
                            "Quantidade de produtos cadastrados: %d%n",
                            qtdProdutos
                        );

                        break;

                    //=========================================================================================
                    // OPÇÃO INVÁLIDA
                    // Executada quando o usuário informa um número que não existe no menu.
                    //=========================================================================================

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }

            } catch (InputMismatchException e) {

                // O Scanner lança essa exceção quando não consegue interpretar
                // a entrada como o tipo numérico solicitado.
                System.out.println("Entrada inválida! Digite um número no formato esperado.");

                // Descarta o restante da linha que contém a entrada inválida.
                teclado.nextLine();

            } catch (IllegalArgumentException e) {

                // Captura as exceções lançadas pelas validações da classe Produto.
                // getMessage() recupera a mensagem definida no throw.
                System.out.println("Erro: " + e.getMessage());
            }

            // Ao terminar a operação ou tratar uma exceção, o while volta ao início.
        }

        // Fecha o Scanner depois que o usuário escolhe a opção 0.
        teclado.close();
    }
}
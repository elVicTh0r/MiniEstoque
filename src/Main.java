package src;
// Classe Main serve para iniciar a aplicação, chamar metodos, receber os seus retornos e definir como serão exibidos.

import java.util.Scanner;;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Gerenciador gerenciador = new Gerenciador();
    

        // ==================================================================
        // 0 Acessando Menu (Inicialização)
        // ==================================================================
        System.out.println("===================================");
        System.out.println("Bem-Vindo ao Mini Estoque");
        System.out.printf("===================================%n%n");
       
        int opcao = -1; //Inicialzação da variável controladora do laço while.

        while (opcao != 0) {
            System.out.printf("%nEscolha uma das opções do Menu abaixo !%n");

                        
            System.out.println("1 - Cadastrar Produtos.");
            System.out.println("2 - Exibir Produtos Cadastrados.");
            System.out.println("3 - Buscar por um Produto.");
            System.out.println("4 - Editar um Produto.");
            System.out.println("5 - Deletar um Produto.");
            System.out.println("6 - Exibir a quantidade de produtos já cadastrados.");
            System.out.println("0 - Para SAIR.");

            System.out.printf("%nDigite sua opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            
            
            switch (opcao) {
            case 0:
                break;

            case 1:

                System.out.print("Digite o nome do produto: ");
                String nome = teclado.nextLine();

                System.out.print("Digite o preço do produto: ");
                double preco = teclado.nextDouble();

                System.out.print("Digite a quantidade (em und) do produto: ");
                int quantidade = teclado.nextInt();

                Produto prod = new Produto(nome, preco, quantidade);

                gerenciador.adicionarProdutos(prod);
                
                break;

            case 2:

                System.out.println("================= Produtos Cadastrados =================");
                for (Produto produto : gerenciador.exibirProdutos()) {
                    System.out.printf("%nId: %d%n", produto.getId());
                    System.out.printf("Descrição: %s%n", produto.getDescricao());
                    System.out.printf("Quantidade: %d%n", produto.getQuantidade());
                    System.out.printf("Preço: %.2f%n%n", produto.getPreco());
                }

                break;

            case 3:

                System.out.print("Digite o id do produto que deseja buscar: ");
                int idProd = teclado.nextInt();

                Produto prodEncontrado = gerenciador.buscarProduto(idProd);

                if(prodEncontrado != null){
                    System.out.println("================= Produto Encontrado na Busca =================");
                    
                    System.out.printf("%nId: %d%n", prodEncontrado.getId());
                    System.out.printf("Descrição: %s%n", prodEncontrado.getDescricao());
                    System.out.printf("Quantidade: %s%n", prodEncontrado.getQuantidade());
                    System.out.printf("Preço: %.2f%n%n", prodEncontrado.getPreco());
                } else {
                    System.out.println("Produto não encontrado.");
                }
                break;
            
            case 4:
                System.out.println("Digite o ID do produto que deseja alterar: ");
                int idProdEdit = teclado.nextInt();

                System.out.println("O que você deseja editar ?");
                System.out.println("1 - Descrição");
                System.out.println("2 - Preço");
                System.out.println("3 - Quantidade");

                int opcaoProd = teclado.nextInt();
                teclado.nextLine();

                switch (opcaoProd) {
 
                    case 1:
                        System.out.print("Digite a nova descrição do produto: ");
                        String novaDescProd = teclado.nextLine();

                        boolean altDescricao = gerenciador.editarDescricao(idProdEdit, novaDescProd); // Variável para guardar o retorno do método e a passagem dos paramentos.

                    if(altDescricao){
                        System.out.println("Descrição alterada com sucesso!");
                        break;
                    } else {
                        System.out.println("Produto não encontrado !");
                    }
                    break;
           
                case 2:

                    System.out.print("Digite o novo preço: ");
                    double novoPreco = teclado.nextDouble();

                    boolean altPreco = gerenciador.editarPreco(idProdEdit, novoPreco);

                    if(altPreco){
                        System.out.println("Preço alterado com sucesso !");
                        break;
                    } else {
                        System.out.println("Produto não encontrado !");
                    }
                    break;
                
                case 3:

                    System.out.print("Digite a nova quantidade: ");
                    int novaQuantidade = teclado.nextInt();

                    boolean altQuantidade = gerenciador.editarQuantidade(idProdEdit, novaQuantidade);

                    if(altQuantidade){
                        System.out.println("Quantidade alterada com sucesso !");
                    } else {
                        System.out.println("Produto não encontrado !");
                    }
                    break;
            
                default:
                    System.out.println("Opção inválida");
                    break;
            }

            break;

            case 5:
                    System.out.print("Digite o ID do produto que deseja excluir: ");
                    int idProdEx = teclado.nextInt();
                    boolean deletado = gerenciador.deletarProduto(idProdEx);
                    if (deletado) {
                        System.out.println("Produto Deletado com Sucesso !");                     
                    } else{
                        System.out.println("Produto não encontado.");
                    }

                    break;
            
            case 6:

                int qtdProdutos = gerenciador.contarProdutos();
                System.out.printf("Quantidade de Produtos Cadastrados: %d%n", qtdProdutos);
                break;
                       
            default:
                System.out.println("Opção Inválida !");
                break;
            }
            
        }
        teclado.close();
    }
}





        //=====================================================================
        // 1 ADICIONANDO PRODUTOS CRIADOS DA CLASSE PRODUTO A LISTA DE PRODUTOS
        //=====================================================================


        //=================================================================================================
        // 2 EXIBINDO A LISTA DE PRODUTOS
        //  uso gerenciador.exibirProdutos pois o metodo exibirProdutos retorna uma cópia lista de produtos.
        //  uso for para iterar sobre a lista retornada pelo metodo.
        //=================================================================================================



        //=================================================================================================
        // 3 BUSCANDO PRODUTOS
        //  crio uma variável do tipo Produto para guardar o retorno do metodo buscarProduto (que retorna um objeto tbm do tipo Produto)
        //  quando a variável recebe o objeto retornado, posso exibir seus dados com get.
        //=================================================================================================



        //=================================================================================================
        // 3 EDITAR UM PRODUTO
        // criei um metod para facilitar a edição (com switch) e utilizei tbm a biblioteca scanner.
        //  crio uma variável do tipo boolean para guardar o retorno do metodo editarProduto (que tbm retorna um boolean)
        //  uso if/else para validar a ação do metodo (se funcionou ou não).
        //=================================================================================================



        // System.out.printf("=============================%n");
        // System.out.printf("Bem-Vindo ao Menu de Edição%n");
        // System.out.printf("=============================%n%n");

        // System.out.printf("Digite o ID do produto que deseja editar: ");
        // int id = teclado.nextInt();

        // System.out.println("O que você deseja editar ?");
        // System.out.println("1 - Descrição");
        // System.out.println("2 - Preço");
        // System.out.println("3 - Quantidade");
        // int opcao = teclado.nextInt();
        // teclado.nextLine(); // Uso para consumir o espaço que vem depois do usuário apertar "Enter", assim o programa não para.

        // switch (1) {
        //     case 1:
        //         System.out.print("Digite a nova descrição: ");
        //         String novaDescricao = teclado.nextLine();

        //         boolean altDescricao = gerenciador.editarDescricao(3, "Macbook"); // Variável para guardar o retorno do método e a passagem dos paramentos.

        //         if(altDescricao){
        //             System.out.println("Descrição alterada com sucesso !");
        //         } else {
        //             System.out.println("Produto não encontrado !");
        //         }
        //         break;
            
        //     case 2:
        //         System.out.println("Digite o novo preço");
        //         double novoPreco = teclado.nextDouble();

        //         boolean altPreco = gerenciador.editarPreco(id, novoPreco);

        //         if(altPreco){
        //             // System.out.println("Preço alterado com sucesso !");
        //         } else {
        //             System.out.println("Produto não encontrado !");
        //         }
        //         break;
            
        //     case 3:
        //         System.out.println("Digite o nova quantidade");
        //         int novaQuantidade = teclado.nextInt();

        //         boolean altQuantidade = gerenciador.editarQuantidade(id, novaQuantidade);

        //         if(altQuantidade){
        //             System.out.println("Quantidade alterada com sucesso !");
        //         } else {
        //             System.out.println("Produto não encontrado !");
        //         }
        //         break;
        
        //     default:
        //         System.out.println("Opção inválida");
        //         break;
        // }

        // System.out.println("================= Lista de Produtos Atualizada =================");


        // for (Produto produto : gerenciador.exibirProdutos()) {
        //     System.out.printf("%nId: %d%n", produto.getId());
        //     System.out.printf("Descrição: %s%n", produto.getDescricao());
        //     System.out.printf("Quantidade: %s%n", produto.getQuantidade());
        //     System.out.printf("Preço: %s%n%n", produto.getPreco());
        // }

        
        // //=================================================================================================
        // // 4 DELETER PRODUTO
        // // criei um metod para facilitar a edição (com switch) e utilizei tbm a biblioteca scanner.
        // //  crio uma variável do tipo boolean para guardar o retorno do metodo editarProduto (que tbm retorna um boolean)
        // //  uso if/else para validar a ação do metodo (se funcionou ou não).
        // //=================================================================================================

        // boolean deletado = gerenciador.deletarProduto(3);

        // System.out.println("================= Lista de Produtos Atualizaa pós Delete =================");

        // for (Produto produto : gerenciador.exibirProdutos()) {
        //     System.out.printf("%nId: %d%n", produto.getId());
        //     System.out.printf("Descrição: %s%n", produto.getDescricao());
        //     System.out.printf("Quantidade: %s%n", produto.getQuantidade());
        //     System.out.printf("Preço: %s%n%n", produto.getPreco());
        // }

        // if(deletado){
        //     System.out.println("Produto deletado com sucesso !");
        // } else {
        //     System.out.println("Produto não encontrado.");
        // }

        // // ===============================================================================================

        // System.out.println("================= Quantidade Produtos na Lista (Final) =================");

        // int qtdProdutos = gerenciador.contarProdutos();
        // System.out.printf("Quantidade de Produtos Cadastrados: %d", qtdProdutos);
        

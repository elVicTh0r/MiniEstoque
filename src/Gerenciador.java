package src;

import java.util.ArrayList;

public class Gerenciador {
    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    // ===========================================================================

    public void adicionarProdutos(Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        listaProdutos.add(produto);
    }

    // ===========================================================================

    public ArrayList<Produto> exibirProdutos(){
        return new ArrayList<>(listaProdutos);
    }

    // ===========================================================================

    public Produto buscarProduto(int idProd){
        for (Produto produto : listaProdutos) {
            if(produto.getId() == idProd){
                return produto;
            }
        }
        return null;
    }

    // ===========================================================================

    public boolean editarDescricao(int id, String novaDescricao){
        for (Produto produto : listaProdutos) {
            if(produto.getId() == id){
                produto.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }

    public boolean editarPreco(int id, double novoPreco){
        for (Produto produto : listaProdutos) {
            if(produto.getId() == id){
                produto.setPreco(novoPreco);
                return true;
            }
        }
        return false;
    }

    public boolean editarQuantidade(int id, int novaQuantidade){
        for (Produto produto : listaProdutos) {
            if(produto.getId() == id){
                produto.setQuantidade(novaQuantidade);
                return true;
            }
        }
        return false;
    }

    public boolean deletarProduto(int id){
        for (Produto produto : listaProdutos) {
            if(produto.getId() == id){
                listaProdutos.remove(produto);
                return true;
            }
        }
        return false;
    }

    public int contarProdutos(){
        return listaProdutos.size();
    }

}
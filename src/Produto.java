package src;

public class Produto{
    private static int contador = 1;
    private int id;
    private String descricao;
    private double preco;
    private int quantidade;

    public Produto(String descricao, double preco, int quantidade){
        this.id = contador;
        contador++;

        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
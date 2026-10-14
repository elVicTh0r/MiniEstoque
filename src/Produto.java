package src;

public class Produto{
    private static int contador = 1;
    private int id;
    private String descricao;
    private double preco;
    private int quantidade;

    public Produto(String descricao, double preco, int quantidade){
        
        setDescricao(descricao);;
        setPreco(preco);
        setQuantidade(quantidade); 

        this.id = contador++; // Contador abaixo dos set, pois se houve falha, ele estiver acima, ele vai gerar o ID 1 de dum objeto inválido
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
        if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descrição inválida!");
        }
        this.descricao = descricao;
    }
    
    public void setPreco(double preco) {
        if(!Double.isFinite(preco) || preco <= 0){
            throw new IllegalArgumentException("Preço precisa ser maior que 0");
        }
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade < 0){
            throw new IllegalArgumentException("Quantidade precisa ser maior ou igual a 0");
        }
        this.quantidade = quantidade;
    }
    
}
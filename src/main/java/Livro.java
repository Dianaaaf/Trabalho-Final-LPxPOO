public class Livro {
    public String titulo;
    public double preco;
    public int quantEstoque;

    public Livro(String titulo,  double preco, int quantEstoque){
        this.titulo = titulo;
        this.preco = preco;
        this.quantEstoque = quantEstoque;
    }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setQuantEstoque(int quantEstoque) { this.quantEstoque = quantEstoque; }

    public String getTitulo() { return titulo; }
    public double getPreco() { return preco; }
    public int getQuantEstoque() { return quantEstoque; }
}

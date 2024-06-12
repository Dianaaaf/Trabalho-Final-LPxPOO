import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Livro> livrosCarrinho = new ArrayList<>();
    private JFrame frame;

    public Carrinho(JFrame frame) {
        this.frame = frame;
    }

    public void adicionaCarrinho(Livro livro, int quantidade) {
        boolean livroExiste = false;

        for (Livro livroCarrinho : livrosCarrinho) {
            if (livroCarrinho.getTitulo().equals(livro.getTitulo())) {
                livroCarrinho.setQuantEstoque(livroCarrinho.getQuantEstoque() + quantidade);
                livroExiste = true;
                break;
            }
        }

        if (!livroExiste) {
            livrosCarrinho.add(new Livro(livro.getTitulo(), livro.getPreco(), quantidade));
        }
        JOptionPane.showMessageDialog(frame, "Livro adicionado ao carrinho!");
    }

    public void removeCarrinho(Livro livro, int quantidade) {
        System.out.println("entrou na f");

        livrosCarrinho.removeIf(livroRemove -> {
            if (livroRemove.getTitulo().equals(livro.getTitulo())) {
                int novaQuantidade = livroRemove.getQuantEstoque() - quantidade;
                if (novaQuantidade > 0) {
                    livroRemove.setQuantEstoque(novaQuantidade);
                    return false;
                } else { return true; }
            }
            return false;
        });
        JOptionPane.showMessageDialog(frame, "Livro removido do carrinho!");
    }

    public double valorTotal() {
        double total = 0.0;

        for (Livro livro : livrosCarrinho) {
            total += livro.getPreco() * livro.getQuantEstoque();
        }
        return total;
    }

    public List<Livro> retornaLivrosCarrinho() {
        return livrosCarrinho;
    }
}

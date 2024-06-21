import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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



/*
public class Carrinho {
    private List<Livro> livrosCarrinho = new ArrayList<>();
    private JFrame frame;
    private Connection connection;

    public Carrinho(JFrame frame) {
        this.frame = frame;
        conectaBanco();
        carregaCarrinho();
    }

    private void conectaBanco() {
        String url = "jdbc:sqlite:bd.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Conexão com o banc.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(frame, "Erro ao conectar ao banco.");
        }
    }

    private void carregaCarrinho() {
        String sql = "SELECT Livro.titulo, Livro.preco, Carrinho.quantidade FROM Carrinho JOIN Livro ON Carrinho.livro_id = Livro.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("quantidade");
                livrosCarrinho.add(new Livro(titulo, preco, quantidade));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(frame, "Erro ao carregar o carrinho .");
        }
    }

    public void adicionaCarrinho(Livro livro, int quantidade) {
        boolean livroExiste = false;
        for (Livro livroCarrinho : livrosCarrinho) {
            if (livroCarrinho.getTitulo().equals(livro.getTitulo())) {
                livroCarrinho.setQuantEstoque(livroCarrinho.getQuantEstoque() + quantidade);
                atualizaLivroCarrinho(livro, livroCarrinho.getQuantEstoque());
                livroExiste = true;
                break;
            }
        }

        if (!livroExiste) {
            livrosCarrinho.add(new Livro(livro.getTitulo(), livro.getPreco(), quantidade));
            insereLivroCarrinho(livro, quantidade);
        }
        JOptionPane.showMessageDialog(frame, "Livro adicionado ao carrinho!");
    }

    private void insereLivroCarrinho(Livro livro, int quantidade) {
        String sql = "INSERT INTO Carrinho (livro_id, quantidade) VALUES ((SELECT id FROM Livro WHERE titulo = " + livro.titulo + "), ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setInt(2, quantidade);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar livro ao carrinho no banco de dados.");
        }
    }

    private void atualizaLivroCarrinho(Livro livro, int quantidade) {
        String sql = "UPDATE Carrinho SET quantidade = " + quantidade + " WHERE livro_id = (SELECT id FROM Livro WHERE titulo = )" + livro.titulo;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, quantidade);
            pstmt.setString(2, livro.getTitulo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(frame, "Erro ao atualizar a quantidade do livro no carrinho.");
        }
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
*/
import java.sql.*;

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



/*
public class Livro {
    private String titulo;
    private double preco;
    private int quantEstoque;
    private Connection connection;

    public Livro(String titulo, double preco, int quantEstoque) {
        this.titulo = titulo;
        this.preco = preco;
        this.quantEstoque = quantEstoque;
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:sqlite:bd.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Conexão com o banco.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void salvarBD() {
        String sql = "INSERT INTO Livro (titulo, preco, quantEstoque) VALUES (" + titulo + ", " + preco + ", "+ quantEstoque + " )";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setDouble(2, preco);
            pstmt.setInt(3, quantEstoque);
            pstmt.executeUpdate();
            System.out.println("Livro salvo no banco de dados.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Livro carregaBanco(String titulo) {
        String url = "jdbc:sqlite:bd.db";
        String sql = "SELECT * FROM Livro WHERE titulo = " + titulo;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double preco = rs.getDouble("preco");
                int quantEstoque = rs.getInt("quantEstoque");
                return new Livro(titulo, preco, quantEstoque);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
*/


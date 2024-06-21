import java.sql.*;

public class Usuario {
    public String nome;
    public String senha;

    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    public void setNome(String nome) { this.nome = nome; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public String getSenha() { return senha; }
}



/*
public class Usuario {
    private String nome;
    private String senha;
    private Connection connection;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:sqlite:bd.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Conexão com o banco de dados.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void salvarBD() {
        String sql = "INSERT INTO Usuario (nome, senha) VALUES (" + nome + ", "+ senha + ")";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, senha);
            pstmt.executeUpdate();
            System.out.println("Usuário salvo.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Usuario carregaBD(String nome) {
        String url = "jdbc:sqlite:bd.db";
        String sql = "SELECT * FROM Usuario WHERE nome = " + nome;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String senha = rs.getString("senha");
                return new Usuario(nome, senha);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
*/

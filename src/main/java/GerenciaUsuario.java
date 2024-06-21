import java.sql.*;
import java.util.ArrayList;


public class GerenciaUsuario {
    ArrayList<Usuario> usuario = new ArrayList<>();

    public boolean adicionarUsuario(Usuario usu) {
        if (!usuario.contains(usu)) {
            usuario.add(usu);
            return true;
        }
        return false;
    }

    public boolean autenticar(String login, String senha) {
        for (Usuario usu : usuario) {
            if (usu.getNome().equals(login) && usu.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}


/*
public class GerenciaUsuario {
    private Connection connection;

    public GerenciaUsuario() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bd.db");
            Statement statement = connection.createStatement();
        } catch(SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public boolean adicionarUsuario(Usuario usu) {
        if (usuarioExiste(usu.getNome())) {
            System.out.println("Usuário já existe.");
            return false;
        }

        String sql = "INSERT INTO Usuario (nome, senha) VALUES (nome, senha)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usu.getNome());
            stmt.setString(2, usu.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM Usuario WHERE nome = " + login + " AND senha = " + senha;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean usuarioExiste(String nome) {
        String sql = "SELECT * FROM Usuario WHERE nome = " + nome;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    public void fecharConexao() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
 */


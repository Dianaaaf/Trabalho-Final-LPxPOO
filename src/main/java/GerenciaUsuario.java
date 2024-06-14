import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GerenciaUsuario {
    private Connection connection;

    public GerenciaUsuario() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bd.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean adicionarUsuario(Usuario usu) {
        String sql = "INSERT INTO Usuario (nome, senha) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usu.getNome());
            stmt.setString(2, usu.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM Usuario WHERE nome = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void fecharConexao() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*
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
 */
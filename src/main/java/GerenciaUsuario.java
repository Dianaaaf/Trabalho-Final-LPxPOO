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

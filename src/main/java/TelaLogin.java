import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField login;
    private JPasswordField senha;
    private JButton botaoLogin, botaoCadastro;
    private GerenciaUsuario gerenciaUsuario;

    public TelaLogin() {
        gerenciaUsuario = new GerenciaUsuario();

        setTitle("Tela de Acesso");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xF4A460));
        add(panel);
        componentes(panel);

        setVisible(true);
    }

    private void componentes(JPanel panel) {
        panel.setLayout(null);
        this.setLocationRelativeTo(null);

        JLabel tituloLB = new JLabel("Biblioteca Online! Os mais procurados estão aqui.");
        tituloLB.setFont(new Font("Arial", Font.BOLD, 12));
        tituloLB.setBounds(10, 10, 330, 25);
        panel.add(tituloLB);

        JLabel usuarioLB = new JLabel("Usuário:");
        usuarioLB.setFont(new Font("Arial", Font.BOLD, 10));
        usuarioLB.setBounds(10, 50, 80, 25);
        panel.add(usuarioLB);

        login = new JTextField(20);
        login.setBounds(100, 50, 165, 25);
        panel.add(login);

        JLabel senhaLB = new JLabel("Senha:");
        senhaLB.setFont(new Font("Arial", Font.BOLD, 10));
        senhaLB.setBounds(10, 80, 80, 25);
        panel.add(senhaLB);

        senha = new JPasswordField(20);
        senha.setBounds(100, 80, 165, 25);
        panel.add(senha);

        botaoLogin = new JButton("Login");
        botaoLogin.setBounds(10, 120, 80, 25);
        botaoLogin.setBackground(Color.WHITE);
        botaoLogin.setForeground(Color.BLACK);
        panel.add(botaoLogin);

        botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setBounds(100, 120, 120, 25);
        botaoCadastro.setBackground(Color.WHITE);
        botaoCadastro.setForeground(Color.BLACK);
        panel.add(botaoCadastro);

        botaoLogin.addActionListener(e -> {
            String log = login.getText();
            String sen = new String(senha.getPassword());

            if(!log.isEmpty() & !sen.isEmpty()) {
                if (gerenciaUsuario.autenticar(log, sen)) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

                    Carrinho carrinho = new Carrinho(this);
                    TelaListaLivros telaListaLivros = new TelaListaLivros(carrinho);
                    telaListaLivros.setVisible(true);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao realizar login.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            }
        });

        botaoCadastro.addActionListener(e -> {
            String log = login.getText();
            String sen = new String(senha.getPassword());

            Usuario newUsuario = new Usuario(log, sen);

            if(!log.isEmpty() & !sen.isEmpty()) {
                if (gerenciaUsuario.adicionarUsuario(newUsuario)) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso! Por gentileza, faça seu login.");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            }
        });
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}


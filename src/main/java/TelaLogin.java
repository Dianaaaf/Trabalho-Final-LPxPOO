import javax.swing.*;

public class TelaLogin extends JFrame {
    private JTextField login;
    private JPasswordField senha;
    private JButton botaoLogin, botaoCadastro;
    private GerenciaUsuario gerenciaUsuario;

    public TelaLogin() {
        gerenciaUsuario = new GerenciaUsuario();

        setTitle("Tela de Acesso");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        componentes(panel);

        setVisible(true);
    }

    private void componentes(JPanel panel) {
        panel.setLayout(null);
        this.setLocationRelativeTo(null);

        JLabel usuarioLB = new JLabel("Usuário:");
        usuarioLB.setBounds(10, 20, 80, 25);
        panel.add(usuarioLB);

        login = new JTextField(20);
        login.setBounds(100, 20, 165, 25);
        panel.add(login);

        JLabel senhaLB = new JLabel("Senha:");
        senhaLB.setBounds(10, 50, 80, 25);
        panel.add(senhaLB);

        senha = new JPasswordField(20);
        senha.setBounds(100, 50, 165, 25);
        panel.add(senha);

        botaoLogin = new JButton("Login");
        botaoLogin.setBounds(10, 80, 80, 25);
        panel.add(botaoLogin);

        botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setBounds(100, 80, 120, 25);
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


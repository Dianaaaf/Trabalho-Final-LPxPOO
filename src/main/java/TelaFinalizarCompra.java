import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class TelaFinalizarCompra extends JFrame {
    private Carrinho carrinho;
    private JPanel painelPrincipal;
    private JLabel lblValorTotal;
    private ButtonGroup grupoPagamento;
    private JRadioButton rdbtnPix, rdbtnFiado;
    private JTextField txtPix;
    private JSpinner spinnerData;
    private JButton btnFinalizar, btnConfirmar;


    public TelaFinalizarCompra(Carrinho carrinho) {
        this.carrinho = carrinho;
        componentes();
    }

    private void componentes() {
        setTitle("Finalizar Compra");

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(new Color(0xF4A460));

        double valorTotal = carrinho.valorTotal();
        lblValorTotal = new JLabel("Valor Total: R$ " + String.format("%.2f", valorTotal));
        lblValorTotal.setBounds(10, 20, 200, 25);
        painelPrincipal.add(lblValorTotal);

        grupoPagamento = new ButtonGroup();
        rdbtnPix = new JRadioButton("Pix (Código QR Code)");
        rdbtnPix.setBounds(10, 50, 160, 25);
        rdbtnPix.setBackground(new Color(0xF4A460));
        painelPrincipal.add(rdbtnPix);
        grupoPagamento.add(rdbtnPix);

        rdbtnFiado = new JRadioButton("Fiado (Selecione uma data para pagar)");
        rdbtnFiado.setBounds(180, 50, 300, 25);
        rdbtnFiado.setBackground(new Color(0xF4A460));
        painelPrincipal.add(rdbtnFiado);
        grupoPagamento.add(rdbtnFiado);

        txtPix = new JTextField("00020126300014BR.GOV.BCB.PIX2508PIXDIANA5204000053039865802BR5905Diana6002BH62070503***63048B76", 30);
        txtPix.setBounds(10, 80, 260, 25);
        txtPix.setVisible(false);
        painelPrincipal.add(txtPix);

        spinnerData = new JSpinner();
        spinnerData.setModel(new SpinnerDateModel(Calendar.getInstance().getTime(), null, null, Calendar.DAY_OF_MONTH));
        spinnerData.setBounds(10, 80, 260, 25);
        spinnerData.setVisible(false);
        painelPrincipal.add(spinnerData);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(150, 150, 160, 25);
        btnConfirmar.setBackground(Color.WHITE);
        btnConfirmar.setForeground(Color.BLACK);
        btnConfirmar.addActionListener(e -> {
            if (rdbtnPix.isSelected()) {
                txtPix.setVisible(true);
                spinnerData.setVisible(false);
            } else if (rdbtnFiado.isSelected()) {
                spinnerData.setVisible(true);
                txtPix.setVisible(false);
            }
        });
        painelPrincipal.add(btnConfirmar);

        btnFinalizar = new JButton("Finalizar e Fechar");
        btnFinalizar.setBounds(150, 190, 160, 25);
        btnFinalizar.setBackground(Color.WHITE);
        btnFinalizar.setForeground(Color.BLACK);
        btnFinalizar.addActionListener(e -> {
            if (rdbtnPix.isSelected() || rdbtnFiado.isSelected()) {
                JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso!");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma forma de pagamento.");
            }
        });

        painelPrincipal.add(btnFinalizar);
        add(painelPrincipal);

        setSize(460, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

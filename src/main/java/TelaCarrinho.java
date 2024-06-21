import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TelaCarrinho extends JFrame {
    private Carrinho carrinho;
    private JTable table;
    private DefaultTableModel modeloTabela;

    public TelaCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
        componentes();
    }

    private void componentes() {
        setTitle("Carrinho");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        double valorTotal = carrinho.valorTotal();
        JLabel lblValorTotal = new JLabel("Valor Total: R$ " + String.format("%.2f", valorTotal));

        getContentPane().add(lblValorTotal, BorderLayout.NORTH);

        JTextArea contCarrinho = new JTextArea();
        contCarrinho.setEditable(false);

        for (Livro livro : carrinho.retornaLivrosCarrinho()) {
            contCarrinho.append("Título: " + livro.getTitulo() + ", Preço: " + livro.getPreco() + "\n");
        }

        getContentPane().add(contCarrinho, BorderLayout.CENTER);

        modeloTabela = new DefaultTableModel(new Object[][]{}, new Object[]{"Título", "Preço", "Quantidade"}) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(modeloTabela);
        table.setBackground(new Color(0xF4A460));

        DefaultTableCellRenderer precoConfig = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    setText("R$ " + value);
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        };
        table.getColumn("Preço").setCellRenderer(precoConfig);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0xA0522D));
        header.setForeground(Color.BLACK);

        atualizaTabela();

        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnPagamento = new JButton("Ir ao Pagamento");
        btnPagamento.setBackground(Color.WHITE);
        btnPagamento.setForeground(Color.BLACK);
        btnPagamento.addActionListener(e -> {
            TelaFinalizarCompra finalizarCompra = new TelaFinalizarCompra(carrinho);
            finalizarCompra.setVisible(true);
        });
        getContentPane().add(btnPagamento, BorderLayout.SOUTH);
    }

    private void atualizaTabela() {
        modeloTabela.setRowCount(0);
        for (Livro livro : carrinho.retornaLivrosCarrinho()) {
            modeloTabela.addRow(new Object[]{livro.getTitulo(), livro.getPreco(), livro.getQuantEstoque(), "Remover"});
        }
    }
}

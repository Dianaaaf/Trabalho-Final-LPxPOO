import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListaLivros extends JFrame {
    private Carrinho carrinho;
    private JTable table;
    private DefaultTableModel modeloTabela;

    public TelaListaLivros(Carrinho carrinho) {
        this.carrinho = carrinho;
        componentes();
    }

    private void componentes() {
        setTitle("Vitrine de Livros");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(0xF4A460));

        modeloTabela = new DefaultTableModel(new Object[][]{}, new Object[]{"Título", "Preço", "Adicionar ao Carrinho"}) {
            @Override
            public boolean isCellEditable(int row, int column) { return column == 2; }
        };
        table = new JTable(modeloTabela);

        table.setBackground(new Color(0xF4A460));

        table.getColumn("Adicionar ao Carrinho").setCellRenderer(new ButtonRenderer());
        table.addMouseListener(new TableButtonMouseListener(table));

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

        adicionaLivro("Deuses Humanos", 36.90);
        adicionaLivro("Os sete maridos de Evelyn Hugo", 59.99);
        adicionaLivro("Assassinato no Expresso Oriente", 39.99);
        adicionaLivro("O sol da meia-noite", 69.99);
        adicionaLivro("Vermelho, Branco e Sangue Azul", 48.97);
        adicionaLivro("Amêndoas", 32.90);
        adicionaLivro("Belo mundo, onde você está", 27.90);
        adicionaLivro("Suicidas", 41.00);
        adicionaLivro("Malibu Renasce", 45.50);
        adicionaLivro("Corte de Chamas Prateadas", 68.90);
        adicionaLivro("Amor Teoricamente", 52.90);
        adicionaLivro("Herdeira do Fogo", 39.99);

        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnCarrinho = new JButton("Ir ao Carrinho");
        btnCarrinho.setBackground(Color.WHITE);
        btnCarrinho.setForeground(Color.BLACK);
        btnCarrinho.addActionListener(e -> {
            TelaCarrinho telaCarrinho = new TelaCarrinho(carrinho);
            telaCarrinho.setVisible(true);
        });
        getContentPane().add(btnCarrinho, BorderLayout.SOUTH);
    }

    private void adicionaLivro(String titulo, double preco) {
        modeloTabela.addRow(new Object[]{titulo, preco, "Adicionar ao Carrinho"});
    }


    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    class TableButtonMouseListener extends MouseAdapter {
        private final JTable table;
        public TableButtonMouseListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof String && value.equals("Adicionar ao Carrinho")) {
                    String titulo = (String) table.getValueAt(row, 0);
                    double preco = (double) table.getValueAt(row, 1);
                    carrinho.adicionaCarrinho(new Livro(titulo, preco, 1), 1);
                }
            }
        }
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        modeloTabela = new DefaultTableModel(new Object[][]{}, new Object[]{"Título", "Preço", "Adicionar ao Carrinho"}) {
            @Override
            public boolean isCellEditable(int row, int column) { return column == 2; }
        };
        table = new JTable(modeloTabela);

        table.getColumn("Adicionar ao Carrinho").setCellRenderer(new ButtonRenderer());
        table.addMouseListener(new TableButtonMouseListener(table));

        adicionaLivro("Java Programming", 29.99);
        adicionaLivro("Python for Data Science", 24.99);
        adicionaLivro("Machine Learning with R", 39.99);

        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnCarrinho = new JButton("Ir ao Carrinho");
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
        public ButtonRenderer() { setOpaque(true); }

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

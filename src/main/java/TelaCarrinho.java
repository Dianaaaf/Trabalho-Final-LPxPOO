import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        setSize(400, 300);
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

        modeloTabela = new DefaultTableModel(new Object[][]{}, new Object[]{"Título", "Preço", "Quantidade", "Remover"}) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(modeloTabela);

        table.getColumn("Remover").setCellRenderer(new ButtonRenderer());
        table.getColumn("Remover").setCellEditor(new ButtonEditor(new JCheckBox()));

        atualizaTabela();

        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll, BorderLayout.CENTER);

        JButton btnCarrinho = new JButton("Ir ao Pagamento");
        btnCarrinho.addActionListener(e -> {
            TelaFinalizarCompra finalizarCompra = new TelaFinalizarCompra(carrinho);
            finalizarCompra.setVisible(true);
        });
        getContentPane().add(btnCarrinho, BorderLayout.SOUTH);
    }

    private void atualizaTabela() {
        modeloTabela.setRowCount(0);
        for (Livro livro : carrinho.retornaLivrosCarrinho()) {
            modeloTabela.addRow(new Object[]{livro.getTitulo(), livro.getPreco(), livro.getQuantEstoque(), "Remover"});
        }
    }


    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() { setOpaque(true); }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private JButton button;
        private boolean selecionado;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Remover");

            System.out.println("texto do botão");

            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            System.out.println("entrou em outra função");

            label = (value == null) ? "" : value.toString();
            button.setText(label);
            selecionado = true;
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            System.out.println("entrou em uma função");

            if (selecionado) {
                String titulo = (String) table.getValueAt(table.getSelectedRow(), 0);
                Livro livroParaRemover = null;
                for (Livro livro : carrinho.retornaLivrosCarrinho()) {
                    if (livro.getTitulo().equals(titulo)) {
                        livroParaRemover = livro;
                        break;
                    }
                }
                if (livroParaRemover != null) {
                    carrinho.removeCarrinho(livroParaRemover, 1);
                    atualizaTabela();
                }
            }
            selecionado = false;
            return label;
        }
        @Override
        public boolean stopCellEditing() {
            selecionado = false;
            return super.stopCellEditing();
        }
        @Override
        protected void fireEditingStopped() { super.fireEditingStopped(); }
    }
}

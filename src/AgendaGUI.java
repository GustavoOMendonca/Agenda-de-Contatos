import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AgendaGUI extends JFrame {
    private Cadastro cadastro;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNome, txtTelefone;

    public AgendaGUI() {
        // Aplica o visual nativo do Sistema Operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        cadastro = new Cadastro();

        setTitle("Agenda de Contatos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de Entrada (Topo)
        JPanel panelInput = new JPanel(new GridLayout(2, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Novo Contato / Edição"));
        panelInput.add(new JLabel(" Nome:"));
        txtNome = new JTextField();
        panelInput.add(txtNome);
        panelInput.add(new JLabel(" Telefone:"));
        txtTelefone = new JTextField();
        panelInput.add(txtTelefone);

        // Tabela (Centro)
        String[] colunas = {"Nome", "Telefone"};
        tableModel = new DefaultTableModel(colunas, 0);
        table = new JTable(tableModel);

        // Configuração do Filtro/Pesquisa em tempo real
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JPanel panelBusca = new JPanel(new BorderLayout(5, 5));
        panelBusca.setBorder(BorderFactory.createTitledBorder("Pesquisar"));
        JTextField txtBusca = new JTextField();
        panelBusca.add(new JLabel(" Filtro (Nome ou Telefone): "), BorderLayout.WEST);
        panelBusca.add(txtBusca, BorderLayout.CENTER);

        txtBusca.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }
            private void filter() {
                String text = txtBusca.getText();
                sorter.setRowFilter(text.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + text));
            }
        });

        atualizarTabela();

        // Painel de Botões (Baixo)
        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Salvar Alteração");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnLimpar);

        // Evento: Selecionar linha da tabela preenche os campos
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    txtNome.setText((String) tableModel.getValueAt(modelRow, 0));
                    txtTelefone.setText((String) tableModel.getValueAt(modelRow, 1));
                }
            }
        });

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnRemover.addActionListener(e -> remover());
        btnLimpar.addActionListener(e -> limparCampos());

        // Atalho: Botão adicionar é o padrão ao apertar Enter
        getRootPane().setDefaultButton(btnAdicionar);

        // Adicionando ao Frame
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelInput, BorderLayout.NORTH);
        panelNorte.add(panelBusca, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Contato c : cadastro.getContatos()) {
            tableModel.addRow(new Object[]{c.getNome(), c.getTelefone()});
        }
    }

    private void adicionar() {
        String nome = txtNome.getText().trim();
        String tel = txtTelefone.getText().trim();

        if (nome.isEmpty() || tel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        if (!tel.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "O telefone deve conter apenas números.");
            return;
        }

        try {
            cadastro.adicionar(nome, tel);
            atualizarTabela();
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void atualizar() {
        int viewRow = table.getSelectedRow();
        if (viewRow != -1) {
            int modelRow = table.convertRowIndexToModel(viewRow);
            String nome = txtNome.getText().trim();
            String tel = txtTelefone.getText().trim();
            
            if (nome.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            if (!tel.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "O telefone deve conter apenas números.");
                return;
            }

            try {
                cadastro.atualizar(modelRow, nome, tel);
                atualizarTabela();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Contato atualizado!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato na tabela para editar.");
        }
    }

    private void remover() {
        int viewRow = table.getSelectedRow();
        if (viewRow != -1) {
            int modelRow = table.convertRowIndexToModel(viewRow);
            int confirm = JOptionPane.showConfirmDialog(this, "Excluir este contato?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cadastro.remover(modelRow);
                atualizarTabela();
                limparCampos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para remover.");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        table.clearSelection();
    }
}

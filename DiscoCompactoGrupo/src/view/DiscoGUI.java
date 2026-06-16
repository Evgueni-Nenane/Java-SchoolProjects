package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import controller.DiscoController;
import model.DiscoCompacto;
import model.GENEROS;

public class DiscoGUI extends JFrame implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textTitulo;
    private JTextField tFMusico;
    private JTextField tFEditora;
    private JTextField tFGravadora;
    private JTextField tFProdutor;
    private JTextField tFPreco;
    private JTextField tFAnoEdicao;

    private JButton btnNovoDisco, btnAdicionarFaixa, btnAdicionarDisco,
                    btnApagarDiscos, btnListarDisco, btnSair, btnAtualizarDisco;
    private JComboBox<GENEROS> cbGenero;
    private JTextField txtCodigo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DiscoGUI frame = new DiscoGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public DiscoGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DiscoGUI.class.getResource("/resources/icon.png")));
        setTitle("Gerenciador de CDs");
        setFont(new Font("Arial", Font.PLAIN, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 874, 504);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(639, 0, 221, 480);
        buttonPanel.setBackground(new Color(217, 217, 217));
        contentPane.add(buttonPanel);
        buttonPanel.setLayout(null);

        btnNovoDisco = new JButton("Novo Disco");
        btnNovoDisco.setBackground(new Color(0, 255, 255));
        btnNovoDisco.setBorderPainted(false);
        btnNovoDisco.setToolTipText("Novo Disco");
        btnNovoDisco.setFont(new Font("Arial", Font.PLAIN, 12));
        btnNovoDisco.setBounds(40, 69, 141, 32);
        buttonPanel.add(btnNovoDisco);
        btnNovoDisco.addActionListener(this);

        btnAdicionarFaixa = new JButton("Adicionar Faixa");
        btnAdicionarFaixa.setBorderPainted(false);
        btnAdicionarFaixa.setBackground(new Color(0, 255, 255));
        btnAdicionarFaixa.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAdicionarFaixa.setBounds(40, 241, 141, 32);
        buttonPanel.add(btnAdicionarFaixa);

        btnApagarDiscos = new JButton("Apagar Discos");
        btnApagarDiscos.setBackground(new Color(0, 255, 255));
        btnApagarDiscos.setBorderPainted(false);
        btnApagarDiscos.setFont(new Font("Arial", Font.PLAIN, 12));
        btnApagarDiscos.setBounds(40, 198, 141, 32);
        buttonPanel.add(btnApagarDiscos);
        btnApagarDiscos.addActionListener(this);

        btnListarDisco = new JButton("Listar Discos");
        btnListarDisco.setBorderPainted(false);
        btnListarDisco.setBackground(new Color(0, 255, 255));
        btnListarDisco.setFont(new Font("Arial", Font.PLAIN, 12));
        btnListarDisco.setBounds(40, 112, 141, 32);
        buttonPanel.add(btnListarDisco);
        btnListarDisco.addActionListener(this);

        btnSair = new JButton("Sair");
        btnSair.setBorderPainted(false);
        btnSair.setBackground(new Color(128, 255, 255));
        btnSair.setFont(new Font("Arial", Font.PLAIN, 12));
        btnSair.setBounds(40, 313, 141, 32);
        buttonPanel.add(btnSair);
        btnSair.addActionListener(this);

        btnAtualizarDisco = new JButton("Atualizar Disco");
        btnAtualizarDisco.setBackground(new Color(0, 255, 255));
        btnAtualizarDisco.setBorderPainted(false);
        btnAtualizarDisco.setBounds(40, 155, 141, 32);
        buttonPanel.add(btnAtualizarDisco);
        btnAtualizarDisco.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAtualizarDisco.addActionListener(this);

        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(0, 0, 639, 223);
        contentPane.add(tablePanel);
        tablePanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 629, 205);
        tablePanel.add(scrollPane);

        table = new JTable();
        table.setCellSelectionEnabled(true);
        scrollPane.setViewportView(table);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Codigo", "Musico", "Genero", "Pre\u00E7o",
                "Editora", "Produtor", "Gravadora",
                "Ano de Edi\u00E7\u00E3o", "Ano de Existencia"
            }
        ));
        table.addMouseListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(0, 221, 639, 246);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Título");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel.setBounds(10, 42, 80, 20);
        panel.add(lblNewLabel);

        textTitulo = new JTextField();
        lblNewLabel.setLabelFor(textTitulo);
        textTitulo.setBounds(92, 42, 188, 20);
        panel.add(textTitulo);
        textTitulo.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Músico");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(10, 74, 80, 20);
        panel.add(lblNewLabel_1);

        tFMusico = new JTextField();
        lblNewLabel_1.setLabelFor(tFMusico);
        tFMusico.setBounds(92, 74, 142, 20);
        panel.add(tFMusico);
        tFMusico.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Editora");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(10, 110, 80, 20);
        panel.add(lblNewLabel_2);

        tFEditora = new JTextField();
        lblNewLabel_2.setLabelFor(tFEditora);
        tFEditora.setBounds(92, 110, 96, 20);
        panel.add(tFEditora);
        tFEditora.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Gravadora");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_3.setBounds(10, 144, 80, 20);
        panel.add(lblNewLabel_3);

        tFGravadora = new JTextField();
        lblNewLabel_3.setLabelFor(tFGravadora);
        tFGravadora.setBounds(92, 144, 96, 20);
        panel.add(tFGravadora);
        tFGravadora.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Produtor");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_4.setBounds(10, 177, 80, 20);
        panel.add(lblNewLabel_4);

        tFProdutor = new JTextField();
        lblNewLabel_4.setLabelFor(tFProdutor);
        tFProdutor.setBounds(92, 177, 142, 20);
        panel.add(tFProdutor);
        tFProdutor.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Género");
        lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_5.setBounds(307, 43, 80, 20);
        panel.add(lblNewLabel_5);

        cbGenero = new JComboBox<>(GENEROS.values());
        lblNewLabel_5.setLabelFor(cbGenero);
        cbGenero.setBounds(396, 42, 96, 22);
        panel.add(cbGenero);

        JLabel lblNewLabel_6 = new JLabel("Preço");
        lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_6.setBounds(306, 75, 80, 20);
        panel.add(lblNewLabel_6);

        tFPreco = new JTextField();
        lblNewLabel_6.setLabelFor(tFPreco);
        tFPreco.setBounds(396, 75, 70, 20);
        panel.add(tFPreco);
        tFPreco.setColumns(10);

        btnAdicionarDisco = new JButton("Adicionar Disco");
        btnAdicionarDisco.setBackground(new Color(0, 255, 255));
        btnAdicionarDisco.setBorderPainted(false);
        btnAdicionarDisco.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAdicionarDisco.setBounds(492, 107, 125, 32);
        panel.add(btnAdicionarDisco);
        btnAdicionarDisco.addActionListener(this);

        JLabel lblNewLabel_7 = new JLabel("Ano de Edição");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_7.setBounds(306, 111, 80, 20);
        panel.add(lblNewLabel_7);

        tFAnoEdicao = new JTextField();
        lblNewLabel_7.setLabelFor(tFAnoEdicao);
        tFAnoEdicao.setBounds(396, 111, 70, 20);
        panel.add(tFAnoEdicao);
        tFAnoEdicao.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("Codigo");
        lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_8.setBounds(10, 17, 48, 14);
        panel.add(lblNewLabel_8);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(92, 14, 96, 20);
        panel.add(txtCodigo);
        txtCodigo.setColumns(10);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdicionarDisco) {
            if (tFMusico.getText().isEmpty() || tFPreco.getText().isEmpty() || tFAnoEdicao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String musico = tFMusico.getText();
                String generoMusica = cbGenero.getSelectedItem().toString();
                String editora = tFEditora.getText();
                String gravadora = tFGravadora.getText();
                String produtor = tFProdutor.getText();
                double preco = Double.parseDouble(tFPreco.getText());
                int anoEdicao = Integer.parseInt(tFAnoEdicao.getText());

                DiscoController.adicionarDiscos(musico, generoMusica, gravadora, preco, produtor, editora, anoEdicao);

                limparCaixas();
                limparTabela();
                listar();
                JOptionPane.showMessageDialog(this, "Disco adicionado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preço e Ano devem ser números válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar disco: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnNovoDisco) {
            limparCaixas();
        }

        if (e.getSource() == btnListarDisco) {
            limparTabela();
            try {
                listar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao listar discos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnAtualizarDisco) {
            if (txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o Código do disco a atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int ID_DC = Integer.parseInt(txtCodigo.getText());
                String musico = tFMusico.getText();
                String generoMusica = cbGenero.getSelectedItem().toString();
                String editora = tFEditora.getText();
                String gravadora = tFGravadora.getText();
                String produtor = tFProdutor.getText();
                double preco = Double.parseDouble(tFPreco.getText());
                int anoEdicao = Integer.parseInt(tFAnoEdicao.getText());

                DiscoController.actualizarDiscos(ID_DC, musico, generoMusica, gravadora, preco, produtor, editora, anoEdicao);

                limparCaixas();
                limparTabela();
                listar();
                JOptionPane.showMessageDialog(this, "Disco atualizado com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código, Preço e Ano devem ser números válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar disco: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnApagarDiscos) {
            if (txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o Código do disco a apagar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar este disco?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar != JOptionPane.YES_OPTION) return;

            try {
                int codigo = Integer.parseInt(txtCodigo.getText());

                DiscoController.removerDisco(codigo);
                limparTabela();
                listar();
                JOptionPane.showMessageDialog(this, "Disco removido com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Código deve ser um número válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover disco: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }

    public void listar() throws IOException, ClassNotFoundException, SQLException {
        DefaultTableModel listarNaTabela = (DefaultTableModel) table.getModel();
        ArrayList<DiscoCompacto> listaDiscos = DiscoController.listarDiscos();

        for (DiscoCompacto disco : listaDiscos) {
            int codigo = disco.getID_DC();
            String musico = disco.getMusico();
            String generoMusica = disco.getGeneroMusica();
            String editora = disco.getEditora();
            String gravadora = disco.getGravadora();
            String produtor = disco.getProdutor();
            double preco = disco.getPreco();
            int anoEdicao = disco.getAnoEdicao();
            int anoExistencia = disco.existenciaDisco();

            listarNaTabela.addRow(new Object[]{
                codigo, musico, generoMusica, preco, editora,
                produtor, gravadora, anoEdicao, anoExistencia
            });
        }
    }

    public void limparTabela() {
        DefaultTableModel listar = (DefaultTableModel) table.getModel();
        while (table.getRowCount() > 0) {
            listar.removeRow(0);
        }
    }

    public void limparCaixas() {
        txtCodigo.setText("");
        textTitulo.setText("");
        tFMusico.setText("");
        tFEditora.setText("");
        cbGenero.setModel(new DefaultComboBoxModel<>(GENEROS.values()));
        tFGravadora.setText("");
        tFAnoEdicao.setText("");
        tFProdutor.setText("");
        tFPreco.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtCodigo.setText(table.getValueAt(row, 0).toString());
            tFMusico.setText(table.getValueAt(row, 1).toString());
            tFEditora.setText(table.getValueAt(row, 4).toString());
            tFProdutor.setText(table.getValueAt(row, 5).toString());
            tFGravadora.setText(table.getValueAt(row, 6).toString());
            tFPreco.setText(table.getValueAt(row, 3).toString());
            tFAnoEdicao.setText(table.getValueAt(row, 7).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
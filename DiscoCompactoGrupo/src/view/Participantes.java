package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.CantorController;
import controller.CompositorController;
import controller.MusicoController;
import model.Cantor;
import model.Compositor;
import model.Musico;

public class Participantes extends JDialog implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;

    private Set<Integer> compositoresSelecionados = new HashSet<>();
    private Set<Integer> musicosSelecionados = new HashSet<>();
    private Set<Integer> cantoresSelecionados = new HashSet<>();

    private DefaultTableModel tabelaCompModel, tabelaMusicModel, tabelaCantorModel;
    private JTable tabelaComp, tabelaMusico, tabelaCantor;
    private JButton btnAdicionarComp, btnAdicionarMusico, btnCancelar, btnSalvar,
            btnAdicionarCantor, btnLimparMusico, btnLimparCompositor, btnLimparCantor;
    private CompositorController compositorController;
    private MusicoController musicoController;
    private CantorController cantorController;

    public Participantes(CompositorController compositorController,
            MusicoController musicoController,
            CantorController cantorController) {

        this.compositorController = compositorController;
        this.musicoController = musicoController;
        this.cantorController = cantorController;

        setTitle("Participantes");
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setModal(true);

        // ====================
        //      TOP CONTAINER
        // ====================

        JPanel topContainer = criarTopContainer();
        add(topContainer, BorderLayout.NORTH);

        // ========================
        //      CENTER CONTAINER
        // ========================

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel container = new JPanel(new GridLayout(1, 3, 20, 0));

        // ====================
        // BOX 1 - Compositores
        // ====================

        JTextField pesquisarComp = new JTextField();
        btnAdicionarComp = new JButton("Adicionar");
        JPanel box1 = criarBoxHeader(
                "Compositores",
                "Selecione todos os compositores que participam do disco.",
                pesquisarComp, btnAdicionarComp);

        tabelaCompModel = criarModeloTabela(new String[]{"", "Nome do Compositor", "", "id"});
        tabelaComp = new JTable(tabelaCompModel);
        configurarTabela(tabelaComp);
        box1.add(new JScrollPane(tabelaComp) {{
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }}, BorderLayout.CENTER);

        btnLimparCompositor = new JButton("Desmarcar tudo");
        btnLimparCompositor.addActionListener(this);
        JPanel acoesBox1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoesBox1.setBackground(Color.WHITE);
        acoesBox1.add(btnLimparCompositor);
        box1.add(acoesBox1, BorderLayout.SOUTH);

        container.add(box1);

        // ===============
        // BOX 2 - Músicos
        // ===============

        JTextField pesquisarMusico = new JTextField();
        btnAdicionarMusico = new JButton("Adicionar");
        JPanel box2 = criarBoxHeader("Músicos", "Selecione todos os músicos que participam do disco.",
                pesquisarMusico, btnAdicionarMusico);

        tabelaMusicModel = criarModeloTabela(new String[]{"", "Nome do Músico", "", "Id"});
        tabelaMusico = new JTable(tabelaMusicModel);
        configurarTabela(tabelaMusico);
        box2.add(new JScrollPane(tabelaMusico) {{
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }}, BorderLayout.CENTER);

        btnLimparMusico = new JButton("Desmarcar tudo");
        btnLimparMusico.addActionListener(this);
        JPanel acoesBox2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoesBox2.setBackground(Color.WHITE);
        acoesBox2.add(btnLimparMusico);
        box2.add(acoesBox2, BorderLayout.SOUTH);

        container.add(box2);

        // ===================================
        // BOX 3 - Cantores
        // ===================================

        JTextField pesquisarCantor = new JTextField();
        btnAdicionarCantor = new JButton("Adicionar");
        JPanel box3 = criarBoxHeader("Cantores", "Selecione todos os cantores que participam do disco.",
                pesquisarCantor, btnAdicionarCantor);

        tabelaCantorModel = criarModeloTabela(new String[]{"", "Nome do Cantor", "", "Id"});
        tabelaCantor = new JTable(tabelaCantorModel);
        configurarTabela(tabelaCantor);
        box3.add(new JScrollPane(tabelaCantor) {{
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }}, BorderLayout.CENTER);

        btnLimparCantor = new JButton("Desmarcar tudo");
        btnLimparCantor.addActionListener(this);
        JPanel acoesBox3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoesBox3.setBackground(Color.WHITE);
        acoesBox3.add(btnLimparCantor);
        box3.add(acoesBox3, BorderLayout.SOUTH);

        container.add(box3);

        centerPanel.add(container, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // ========================
        //      BOTTOM CONTAINER
        // ========================

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        bottomPanel.setBackground(Color.WHITE);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);

        btnSalvar = new JButton("Confirmar Seleção");
        btnSalvar.addActionListener(this);
        btnSalvar.setBackground(new Color(19, 175, 119));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(btnCancelar);
        bottomPanel.add(btnSalvar);

        add(bottomPanel, BorderLayout.SOUTH);

        btnAdicionarComp.addActionListener(this);
        btnAdicionarMusico.addActionListener(this);
        btnAdicionarCantor.addActionListener(this);

        adicionarFiltroPesquisa(pesquisarComp, tabelaCompModel, "compositor");
        adicionarFiltroPesquisa(pesquisarMusico, tabelaMusicModel, "musico");
        adicionarFiltroPesquisa(pesquisarCantor, tabelaCantorModel, "cantor");

        carregarCompositores();
        carregarMusicos();
        carregarCantores();
    }

    private void configurarTabela(JTable tabela) {
        tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(0).setMaxWidth(30);
        tabela.getColumnModel().getColumn(2).setMinWidth(0);
        tabela.getColumnModel().getColumn(2).setMaxWidth(0);
        tabela.getColumnModel().getColumn(2).setWidth(0);
        tabela.getColumnModel().getColumn(3).setMinWidth(0);
        tabela.getColumnModel().getColumn(3).setMaxWidth(0);
        tabela.getColumnModel().getColumn(3).setWidth(0);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setReorderingAllowed(false);
    }

    private JPanel criarTopContainer() {
        JPanel topContainer = new JPanel();
        topContainer.setBackground(new Color(19, 175, 119));
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
        topContainer.setPreferredSize(new Dimension(0, 90));

        JLabel titulo = new JLabel("Informações da Banda e Participantes");
        titulo.setFont(titulo.getFont().deriveFont(16f).deriveFont(java.awt.Font.BOLD));
        titulo.setForeground(Color.WHITE);

        JLabel descricao = new JLabel("Selecione a banda e defina os compositores, músicos e cantores.");
        descricao.setFont(descricao.getFont().deriveFont(12f));
        descricao.setForeground(new Color(230, 230, 230));

        topContainer.add(titulo);
        topContainer.add(Box.createVerticalStrut(5));
        topContainer.add(descricao);
        return topContainer;
    }

    private JPanel criarBoxHeader(String titulo, String descricao, JTextField campoPesquisa, JButton botaoAdicionar) {
        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JPanel boxTitleContainer = new JPanel();
        boxTitleContainer.setLayout(new BoxLayout(boxTitleContainer, BoxLayout.Y_AXIS));
        boxTitleContainer.setBackground(Color.WHITE);

        JPanel boxLabel = new JPanel();
        boxLabel.setLayout(new BoxLayout(boxLabel, BoxLayout.Y_AXIS));
        boxLabel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(14f).deriveFont(java.awt.Font.BOLD));

        JLabel lblDescricao = new JLabel(descricao);
        lblDescricao.setFont(lblDescricao.getFont().deriveFont(11f));
        lblDescricao.setForeground(Color.GRAY);

        boxLabel.add(lblTitulo);
        boxLabel.add(Box.createVerticalStrut(5));
        boxLabel.add(lblDescricao);

        JPanel acoes = new JPanel(new GridBagLayout());
        acoes.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 5, 5);

        campoPesquisa.setPreferredSize(new Dimension(180, 34));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        acoes.add(campoPesquisa, gbc);

        botaoAdicionar.setPreferredSize(new Dimension(110, 34));
        botaoAdicionar.setBackground(new Color(19, 175, 119));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setBorderPainted(false);
        botaoAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        acoes.add(botaoAdicionar, gbc);

        boxTitleContainer.add(boxLabel);
        boxTitleContainer.add(Box.createVerticalStrut(10));
        boxTitleContainer.add(acoes);

        box.add(boxTitleContainer, BorderLayout.NORTH);
        return box;
    }

    private DefaultTableModel criarModeloTabela(String[] colunas) {
        return new DefaultTableModel(colunas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0;
            }
        };
    }

    private void adicionarFiltroPesquisa(JTextField campoPesquisa, DefaultTableModel model, String tipo) {
        campoPesquisa.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = campoPesquisa.getText().toLowerCase().trim();
                
                // Salvar seleções atuais
                salvarSelecoesAtuais(model, tipo);
                
                // Recarregar dados
                switch (tipo) {
                    case "compositor":
                        carregarCompositores();
                        break;
                    case "musico":
                        carregarMusicos();
                        break;
                    case "cantor":
                        carregarCantores();
                        break;
                }
                
                // Aplicar filtro
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    String nome = model.getValueAt(i, 1).toString().toLowerCase();
                    if (!texto.isEmpty() && !nome.contains(texto)) {
                        model.removeRow(i);
                    }
                }
            }
        });
    }

    private void salvarSelecoesAtuais(DefaultTableModel model, String tipo) {
        Set<Integer> selecionados;
        switch (tipo) {
            case "compositor":
                selecionados = compositoresSelecionados;
                break;
            case "musico":
                selecionados = musicosSelecionados;
                break;
            case "cantor":
                selecionados = cantoresSelecionados;
                break;
            default:
                return;
        }
        
        selecionados.clear();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean marcado = (Boolean) model.getValueAt(i, 0);
            int id = (Integer) model.getValueAt(i, 3);
            if (marcado) {
                selecionados.add(id);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionarComp) {
            new CadastrarCompositorDialog().setVisible(true);
            carregarCompositores();
        }
        if (e.getSource() == btnAdicionarMusico) {
            new CadastrarMusicoDialog().setVisible(true);
            carregarMusicos();
        }
        if (e.getSource() == btnAdicionarCantor) {
            new CadastrarCantorDialog().setVisible(true);
            carregarCantores();
        }
        if (e.getSource() == btnLimparMusico) {
            desmarcarMusico();
        }
        if (e.getSource() == btnLimparCompositor) {
            desmarcarCompositor();
        }
        if (e.getSource() == btnLimparCantor) {
            desmarcarCantor();
        }
        if (e.getSource() == btnSalvar) {
            salvarSelecoes();
            this.dispose();
        }
        if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void salvarSelecoes() {
        // Salvar compositores
        compositoresSelecionados.clear();
        for (int i = 0; i < tabelaCompModel.getRowCount(); i++) {
            boolean marcado = (Boolean) tabelaCompModel.getValueAt(i, 0);
            int id = (Integer) tabelaCompModel.getValueAt(i, 3);
            if (marcado) {
                compositoresSelecionados.add(id);
            }
        }
        
        // Salvar músicos
        musicosSelecionados.clear();
        for (int i = 0; i < tabelaMusicModel.getRowCount(); i++) {
            boolean marcado = (Boolean) tabelaMusicModel.getValueAt(i, 0);
            int id = (Integer) tabelaMusicModel.getValueAt(i, 3);
            if (marcado) {
                musicosSelecionados.add(id);
            }
        }
        
        // Salvar cantores
        cantoresSelecionados.clear();
        for (int i = 0; i < tabelaCantorModel.getRowCount(); i++) {
            boolean marcado = (Boolean) tabelaCantorModel.getValueAt(i, 0);
            int id = (Integer) tabelaCantorModel.getValueAt(i, 3);
            if (marcado) {
                cantoresSelecionados.add(id);
            }
        }
    }

    public void carregarCompositores() {
        salvarSelecoesAtuais(tabelaCompModel, "compositor");
        
        tabelaCompModel.setRowCount(0);
        List<Compositor> compositores = compositorController.listarCompositor();
        for (Compositor compositor : compositores) {
            boolean estavaMarcado = compositoresSelecionados.contains(compositor.getCodigoCompositor());
            tabelaCompModel.addRow(new Object[]{
                    estavaMarcado,
                    compositor.getNomeCompositor() + " " + compositor.getApelidoCompositor(),
                    "Compositor",
                    compositor.getCodigoCompositor()
            });
        }
    }

    public void carregarMusicos() {
        salvarSelecoesAtuais(tabelaMusicModel, "musico");
        
        tabelaMusicModel.setRowCount(0);
        List<Musico> musicos = musicoController.listarMusicos();

        for (Musico musico : musicos) {
            boolean estavaMarcado = musicosSelecionados.contains(musico.getCodigoMusico());
            tabelaMusicModel.addRow(new Object[]{
                    estavaMarcado,
                    musico.getNomeMusico() + " " + musico.getApelidoMusico(),
                    musico.getInstrumento(),
                    musico.getCodigoMusico()
            });
        }
    }

    public void carregarCantores() {
        salvarSelecoesAtuais(tabelaCantorModel, "cantor");
        
        tabelaCantorModel.setRowCount(0);
        List<Cantor> cantores = cantorController.listarCantor();

        for (Cantor cantor : cantores) {
            boolean estavaMarcado = cantoresSelecionados.contains(cantor.getCodigoCantor());
            tabelaCantorModel.addRow(new Object[]{
                    estavaMarcado,
                    cantor.getNomeCantor() + " " + cantor.getApelidoCantor(),
                    "Vocal",
                    cantor.getCodigoCantor()
            });
        }
    }

    public void desmarcarCompositor() {
        compositoresSelecionados.clear();
        for (int i = 0; i < tabelaCompModel.getRowCount(); i++) {
            tabelaCompModel.setValueAt(false, i, 0);
        }
    }

    public void desmarcarMusico() {
        musicosSelecionados.clear();
        for (int i = 0; i < tabelaMusicModel.getRowCount(); i++) {
            tabelaMusicModel.setValueAt(false, i, 0);
        }
    }

    public void desmarcarCantor() {
        cantoresSelecionados.clear();
        for (int i = 0; i < tabelaCantorModel.getRowCount(); i++) {
            tabelaCantorModel.setValueAt(false, i, 0);
        }
    }

    public int totalSelecionados() {
        return compositoresSelecionados.size() + musicosSelecionados.size() + cantoresSelecionados.size();
    }

    public int compositorContador() {
        return compositoresSelecionados.size();
    }

    public int musicoContador() {
        return musicosSelecionados.size();
    }

    public int cantorContador() {
        return cantoresSelecionados.size();
    }

    public List<Compositor> getCompositoresSelecionados() {
        List<Compositor> resultado = new ArrayList<>();
        List<Compositor> todos = compositorController.listarCompositor();

        for (Compositor compositor : todos) {
            if (compositoresSelecionados.contains(compositor.getCodigoCompositor())) {
                resultado.add(compositor);
            }
        }
        return resultado;
    }

    public List<Musico> getMusicoSelecionados() {
        List<Musico> resultado = new ArrayList<>();
        List<Musico> todos = musicoController.listarMusicos();

        for (Musico musico : todos) {
            if (musicosSelecionados.contains(musico.getCodigoMusico())) {
                resultado.add(musico);
            }
        }
        return resultado;
    }

    public List<Cantor> getCantoresSelecionados() {
        List<Cantor> resultado = new ArrayList<>();
        List<Cantor> todos = cantorController.listarCantor();

        for (Cantor cantor : todos) {
            if (cantoresSelecionados.contains(cantor.getCodigoCantor())) {
                resultado.add(cantor);
            }
        }
        return resultado;
    }
}
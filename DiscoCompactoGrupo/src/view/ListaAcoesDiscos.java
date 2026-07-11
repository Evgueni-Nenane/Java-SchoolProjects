package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.DiscoController;
import controller.LogsController;
import model.DiscoCompacto;
import model.Genero;
import model.NivelAcesso;
import model.Sessao;
import resources.EstilizarTabela;

public class ListaAcoesDiscos extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel tabelaModelo;
    private JTable tabela;
    private DiscoController discoController;
    private JButton btnRemover, btnVerDetalhes, btnEditar;
    private LogsController logController;
    
    public ListaAcoesDiscos(DiscoController discoController, LogsController logController) {
        this.discoController = discoController;
        this.logController = logController;
        
        JPanel removerPanel = new JPanel(new BorderLayout());

        // Topbar
        JPanel titulo = new JPanel(new BorderLayout());
        titulo.setBackground(new Color(254, 254, 254));
        titulo.setPreferredSize(new Dimension(0, 50));

        JPanel parteDescritiva = new JPanel();
        parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
        parteDescritiva.setBackground(titulo.getBackground());
        JLabel nome = new JLabel("Discos Cadastrados no Sistema");
        nome.setFont(new Font("Montserrat", 16, Font.BOLD));
        parteDescritiva.add(Box.createHorizontalStrut(20));
        parteDescritiva.add(Box.createVerticalGlue());
        parteDescritiva.add(nome);
        parteDescritiva.add(Box.createVerticalGlue());

        
        JPanel partePesquisa = new JPanel();
        partePesquisa.setBackground(titulo.getBackground());

        JLabel pesquisar = new JLabel("Pesquisar");
        JTextField txtPesquisa = new JTextField();
        txtPesquisa.setPreferredSize(new Dimension(200, 30));
        
        partePesquisa.add(pesquisar);
        partePesquisa.add(txtPesquisa);
        
        txtPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = txtPesquisa.getText().toLowerCase().trim();
                tabelaModelo.setRowCount(0);

                List<DiscoCompacto> discos = discoController.listarDiscos();
                for (DiscoCompacto d : discos) {
                    String generosStr = formatarGeneros(d.getGeneroMusical());
                    
                    if (texto.isEmpty() ||
                        d.getTitulo().toLowerCase().contains(texto) ||
                        generosStr.toLowerCase().contains(texto) ||
                        String.valueOf(d.getPreco()).contains(texto) ||
                        String.valueOf(d.getAnoEdicao()).contains(texto)) {

                        tabelaModelo.addRow(new Object[]{
                            d.getCodigoDisco(),
                            d.getTitulo(),
                            generosStr,
                            d.getPreco(),
                            d.getAnoEdicao()
                        });
                    }
                }
            }
        });

        titulo.add(parteDescritiva, BorderLayout.WEST);
        titulo.add(partePesquisa, BorderLayout.EAST);

        removerPanel.add(titulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Código","Título", "Gênero", "Preço", "Ano Edição"};
        tabelaModelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(tabelaModelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        EstilizarTabela.aplicar(tabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        removerPanel.add(scroll, BorderLayout.CENTER);

        // Botão remover
        btnRemover = new JButton("Remover Selecionado");
        btnRemover.addActionListener(this);
        
        btnEditar = new JButton("Editar Disco");
        btnEditar.addActionListener(this);
        
        btnVerDetalhes = new JButton("Ver Detalhes");
        btnVerDetalhes.addActionListener(this);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnVerDetalhes);
        removerPanel.add(painelBotoes, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(removerPanel, BorderLayout.CENTER);

        carregarDiscos();
    }

    private String formatarGeneros(List<Genero> generos) {
        if (generos == null || generos.isEmpty()) {
            return "Nenhum gênero";
        }
        
        List<String> nomesGeneros = new ArrayList<>();

        for (Genero genero : generos) {
            nomesGeneros.add(genero.getNomeGenero());
        }

        return String.join(", ", nomesGeneros);
    }

    public void carregarDiscos() {
        tabelaModelo.setRowCount(0);
        List<DiscoCompacto> discos = discoController.listarDiscos();
        for (DiscoCompacto disco : discos) {
            tabelaModelo.addRow(new Object[]{
                disco.getCodigoDisco(),
                disco.getTitulo(),
                formatarGeneros(disco.getGeneroMusical()), 
                disco.getPreco(),
                disco.getAnoEdicao()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnRemover) {
            if(Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR) {
                JOptionPane.showMessageDialog(null, "Sem permissão suficiente", "Erro de permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int linhaSelecionada = tabela.getSelectedRow();

            if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um disco!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja remover este disco?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

            if(confirmacao == JOptionPane.YES_OPTION) {

                int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
                boolean sucesso = discoController.removerDisco(codigo);

                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Disco removido com sucesso!");
                    carregarDiscos();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao remover disco!");
                }
            }
        }
        if(e.getSource() == btnVerDetalhes) {
            int linhaSelecionada = tabela.getSelectedRow();
            
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um disco", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
            DiscoCompacto discoCompleto = discoController.buscarDiscoCompleto(codigo);
            
            
            new DiscoCompletoDialog(discoCompleto).setVisible(true);
        }
        if (e.getSource() == btnEditar) {
            if(Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR) {
                JOptionPane.showMessageDialog(null, "Sem permissão suficiente", "Erro de permissão", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int linhaSelecionada = tabela.getSelectedRow();
            
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um disco", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
            DiscoCompacto disco = discoController.buscarDiscoCompleto(codigo);
            
            new EditarDiscoDialog(discoController, disco, logController).setVisible(true);
            carregarDiscos();
        }
    }

}
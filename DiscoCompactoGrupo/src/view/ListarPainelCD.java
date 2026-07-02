package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DiscoController;
import model.DiscoCompacto;

public class ListarPainelCD extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel tabelaModelo;
    private JTable tabela;
    private DiscoController discoController;

    public ListarPainelCD(DiscoController discoController) {
        this.discoController = discoController;

        JPanel listaPanel = new JPanel(new BorderLayout());

        // Topbar
        JPanel titulo = new JPanel(new BorderLayout());
        titulo.setBackground(new Color(254, 254, 254));
        titulo.setPreferredSize(new Dimension(0, 50));

        JPanel parteDescritiva = new JPanel();
        parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
        parteDescritiva.setBackground(titulo.getBackground());
        JLabel nome = new JLabel("Lista de Discos");
        parteDescritiva.add(Box.createHorizontalStrut(20));
        parteDescritiva.add(Box.createVerticalGlue());
        parteDescritiva.add(nome);
        parteDescritiva.add(Box.createVerticalGlue());

        titulo.add(parteDescritiva, BorderLayout.WEST);
        listaPanel.add(titulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Código", "Título", "Gênero", "Preço", "Ano Edição", "Existência do Disco"};
        tabelaModelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(tabelaModelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        listaPanel.add(scroll, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(listaPanel, BorderLayout.CENTER);
        carregarDiscos();
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                carregarDiscos();
            }
        });
    }

    public void carregarDiscos() {
        tabelaModelo.setRowCount(0);
        List<DiscoCompacto> discos = discoController.listarDiscos();
        for (DiscoCompacto disco : discos) {
            tabelaModelo.addRow(new Object[]{
                disco.getCodigoDisco(),
                disco.getTitulo(),
                disco.getGeneroMusical(),
                disco.getPreco(),
                disco.getAnoEdicao(),
                disco.discoExistencia(disco.getAnoEdicao())
            });
        }
    }
}
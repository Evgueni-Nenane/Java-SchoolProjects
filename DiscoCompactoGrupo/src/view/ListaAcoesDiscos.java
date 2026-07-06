package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import controller.CantorController;
import controller.CompositorController;
import controller.DiscoController;
import controller.EditoraController;
import controller.GravadoraController;
import controller.MusicoController;
import controller.ProdutorController;
import model.DiscoCompacto;

public class ListaAcoesDiscos extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel tabelaModelo;
    private JTable tabela;
    private DiscoController discoController;
    private JButton btnRemover, btnVerDetalhes, btnEditar;
    private CompositorController compositorController;
    private CantorController cantorController;
    private MusicoController musicoController;
	private ProdutorController produtorController;
	private GravadoraController gravadoraController;
	private EditoraController editoraController;
	
    public ListaAcoesDiscos(DiscoController discoController) {
        this.discoController = discoController;
        this.compositorController = compositorController;
        this.cantorController = cantorController;
        this.musicoController = musicoController;
        
        JPanel removerPanel = new JPanel(new BorderLayout());

        // Topbar
        JPanel titulo = new JPanel(new BorderLayout());
        titulo.setBackground(new Color(254, 254, 254));
        titulo.setPreferredSize(new Dimension(0, 50));

        JPanel parteDescritiva = new JPanel();
        parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
        parteDescritiva.setBackground(titulo.getBackground());
        JLabel nome = new JLabel("Remover Discos");
        parteDescritiva.add(Box.createHorizontalStrut(20));
        parteDescritiva.add(Box.createVerticalGlue());
        parteDescritiva.add(nome);
        parteDescritiva.add(Box.createVerticalGlue());

        titulo.add(parteDescritiva, BorderLayout.WEST);
        removerPanel.add(titulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Código", "Título", "Gênero", "Preço", "Ano Edição"};
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

    public void carregarDiscos() {
        tabelaModelo.setRowCount(0);
        List<DiscoCompacto> discos = discoController.listarDiscos();
        for (DiscoCompacto disco : discos) {
            tabelaModelo.addRow(new Object[]{
                disco.getCodigoDisco(),
                disco.getTitulo(),
                disco.getGeneroMusical(),
                disco.getPreco(),
                disco.getAnoEdicao()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnRemover) {
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
			int linhaSelecionada = tabela.getSelectedRow();
			
			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione um disco", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			
			new EditarDiscoDialog(discoController, compositorController, musicoController, cantorController, produtorController, gravadoraController, editoraController).setVisible(true);
		}
    }

}
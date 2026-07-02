package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.LogsController;
import model.Logs;

public class PainelLogs extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaModelo;
	private LogsController logsController;
	private JTable tabela;

	public PainelLogs(LogsController logsController) {
		this.logsController = logsController;
		
		JPanel suspenderPanel = new JPanel();
		suspenderPanel.setLayout(new BorderLayout());

		JPanel titulo = new JPanel(new BorderLayout());
		titulo.setBackground(Color.WHITE);
		titulo.setPreferredSize(new Dimension(0, 50));

		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setBackground(titulo.getBackground());

		JLabel nome = new JLabel("Logs do Sistema");
		parteDescritiva.add(Box.createHorizontalStrut(20));
		parteDescritiva.add(Box.createVerticalGlue());
		parteDescritiva.add(nome);
		parteDescritiva.add(Box.createVerticalGlue());

		JPanel partePesquisa = new JPanel();
		partePesquisa.setBackground(titulo.getBackground());

		JLabel pesquisar = new JLabel("Pesquisar");
		JTextField txtPesquisa = new JTextField();
		txtPesquisa.setPreferredSize(new Dimension(200, 30));

		JLabel filtro = new JLabel("Icone");
		filtro.setPreferredSize(new Dimension(30, 30));
		filtro.setMinimumSize(new Dimension(30, 30));
		filtro.setMaximumSize(new Dimension(30, 30));
		filtro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		partePesquisa.add(pesquisar);
		partePesquisa.add(txtPesquisa);
		partePesquisa.add(filtro);

		titulo.add(parteDescritiva, BorderLayout.WEST);
		titulo.add(partePesquisa, BorderLayout.EAST);
		suspenderPanel.add(titulo, BorderLayout.NORTH);

		JPanel tabelaPanel = new JPanel();

		String[] colunas = { "Codigo", "Nome Completo", "E-mail Corporativo", "Perfil", "Acção","Data" };
		tabelaModelo = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			
		tabela = new JTable(tabelaModelo);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);

		tabelaPanel.add(tabela);

		JScrollPane scroll = new JScrollPane(tabela);

		suspenderPanel.add(scroll, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(suspenderPanel, BorderLayout.CENTER);
		carregarLogs();
		
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                carregarLogs();
            }
        });
	}
	
	public void carregarLogs() {
		tabelaModelo.setRowCount(0);
		List<Logs> logs = logsController.listarLogs();
		for (Logs log : logs) {
			tabelaModelo.addRow(new Object[] {
					log.getCodigo(),
					log.getNome() + " "+ log.getApelido(),
					log.getEmail(),
					log.getPerfil(),
					log.getAccao(),
					log.getDataHora()
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO
	}
 }

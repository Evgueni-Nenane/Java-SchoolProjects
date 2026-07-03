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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.UtilizadorController;
import model.Utilizador;

public class PainelListaUsers extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaModelo;
	private UtilizadorController utilizadorController;

	public PainelListaUsers(UtilizadorController utilizadorController) {
		this.utilizadorController = utilizadorController;
		
		JPanel listaPanel = new JPanel();
		listaPanel.setLayout(new BorderLayout());

		JPanel titulo = new JPanel(new BorderLayout());
		titulo.setBackground(Color.WHITE);
		titulo.setPreferredSize(new Dimension(0, 50));

		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setBackground(titulo.getBackground());

		JLabel nome = new JLabel("Lista de Utilizadores");
		JLabel descricao = new JLabel("Lista de utilizadores e suas permissões.");
		parteDescritiva.add(Box.createHorizontalStrut(20));
		parteDescritiva.add(Box.createVerticalGlue());
		parteDescritiva.add(nome);
		parteDescritiva.add(descricao);
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
		txtPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = txtPesquisa.getText().toLowerCase().trim();
		        tabelaModelo.setRowCount(0);

		        List<Utilizador> utilizadores = utilizadorController.listarUtilizador();
		        for (Utilizador u : utilizadores) {
		            if (texto.isEmpty() ||
		                u.getNome().toLowerCase().contains(texto) ||
		                String.valueOf(u.getApelido()).toLowerCase().contains(texto) ||
		                u.getEmail().toLowerCase().contains(texto) ||
		                String.valueOf(u.getGenero()).toLowerCase().contains(texto) ||
		                String.valueOf(u.getPerfil()).toLowerCase().contains(texto) ||
		                String.valueOf(u.getContacto()).contains(texto) ||
		                String.valueOf(u.getCodigo()).contains(texto)) {

		                tabelaModelo.addRow(new Object[]{
		                    u.getCodigo(),
		                    u.getNome() + " " + u.getApelido(),
		                    u.getEmail(),
		                    u.getGenero(),
		                    u.getPerfil(),
		                    u.getContacto()
		                });
		            }
		        }
		    }
		});

		titulo.add(parteDescritiva, BorderLayout.WEST);
		titulo.add(partePesquisa, BorderLayout.EAST);
		listaPanel.add(titulo, BorderLayout.NORTH);

		JPanel tabelaPanel = new JPanel();

		String[] colunas = { "Codigo", "Nome Completo", "E-mail Corporativo", "Género", "Permissões", "Telefone"};
		tabelaModelo = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			
		JTable tabela = new JTable(tabelaModelo);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);

		tabelaPanel.add(tabela);

		JScrollPane scroll = new JScrollPane(tabela);

		listaPanel.add(scroll, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(listaPanel, BorderLayout.CENTER);
		carregarUsers();
		
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                carregarUsers();
            }
        });
	}
	
	public void carregarUsers() {
		tabelaModelo.setRowCount(0);
		List<Utilizador> utilizadores = utilizadorController.listarUtilizador();
		for (Utilizador utilizador : utilizadores) {
			tabelaModelo.addRow(new Object[] {
					utilizador.getCodigo(),
					utilizador.getNome() + " "+ utilizador.getApelido(),
					utilizador.getEmail(),
					utilizador.getGenero(),
					utilizador.getPerfil(),
					utilizador.getContacto()
			});
		}
	}
 }
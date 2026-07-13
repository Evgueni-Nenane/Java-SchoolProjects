package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

import controller.MusicoController;
import model.Instrumento;
import model.Musico;
import model.NivelAcesso;
import model.Sessao;
import resources.EstilizarBotao;
import resources.EstilizarTabela;

public class ListaAcoesMusico extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaModelo;
	private MusicoController musicoController;
	private JButton btnAdicionar, btnEditar, btnRemover;
	private JTable tabela;

	public ListaAcoesMusico() {
		musicoController = new MusicoController();
		
		JPanel suspenderPanel = new JPanel();
		suspenderPanel.setLayout(new BorderLayout());

		JPanel titulo = new JPanel(new BorderLayout());
		titulo.setBackground(Color.WHITE);
		titulo.setPreferredSize(new Dimension(0, 50));

		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setBackground(titulo.getBackground());

		JLabel nome = new JLabel("Lista de músicos");
        nome.setFont(new Font("Montserrat", Font.BOLD, 16));
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

		        List<Musico> musicos = musicoController.listarMusicos();
		        for (Musico m : musicos) {
		        	List<String> nomes = new ArrayList<>();

	            	for (Instrumento instrumento : m.getInstrumento()) {
	            	    nomes.add(instrumento.getNome());
	            	}
	            	String resultado = String.join(", ", nomes);

		            if (texto.isEmpty() ||
		                m.getNomeMusico().toLowerCase().contains(texto) ||
		                String.valueOf(m.getApelidoMusico()).toLowerCase().contains(texto) ||
		                m.getEmailMusico().toLowerCase().contains(texto) ||
		                String.valueOf(m.getContactoMusico()).contains(texto) ||
		                String.valueOf(resultado).contains(texto) ||
		                String.valueOf(m.getCodigoMusico()).contains(texto)) {

		            	

		                tabelaModelo.addRow(new Object[]{
		                    m.getCodigoMusico(),
		                    m.getNomeMusico() + " " + m.getApelidoMusico(),
		                    resultado,
		                    m.getEmailMusico(),
		                    m.getContactoMusico()
		                });
		            }
		        }
		    }
		});

		titulo.add(parteDescritiva, BorderLayout.WEST);
		titulo.add(partePesquisa, BorderLayout.EAST);
		suspenderPanel.add(titulo, BorderLayout.NORTH);

		JPanel tabelaPanel = new JPanel();

		String[] colunas = {"Codigo","Nome Completo", "Instrumento", "E-mail", "Telefone"};
		tabelaModelo = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			
		tabela = new JTable(tabelaModelo);
		tabela.getColumnModel().getColumn(0).setMinWidth(0);
		tabela.getColumnModel().getColumn(0).setMaxWidth(0);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
		EstilizarTabela.aplicar(tabela);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setRowHeight(30);
		tabelaPanel.add(tabela);

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		suspenderPanel.add(scroll, BorderLayout.CENTER);
        
		btnRemover = new JButton("Remover");
		EstilizarBotao.aplicarTerc(btnRemover);
        btnRemover.addActionListener(this);
        
        btnEditar = new JButton("Editar");
        EstilizarBotao.aplicarSec(btnEditar);
        btnEditar.addActionListener(this);

        btnAdicionar = new JButton("Adicionar");
        EstilizarBotao.aplicarSec(btnAdicionar);
        btnAdicionar.addActionListener(this);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnAdicionar);
        painelBotoes.setPreferredSize(new Dimension(0, 60));
        painelBotoes.setBorder(BorderFactory.createMatteBorder(1, 0,0,0, Color.LIGHT_GRAY));
        painelBotoes.setBackground(Color.WHITE);
        suspenderPanel.add(painelBotoes, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(suspenderPanel, BorderLayout.CENTER);
		carregarMusicos();
		
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                carregarMusicos();
            }
        });
	}
	
	public void carregarMusicos() {
		tabelaModelo.setRowCount(0);
		List<Musico> musicos = musicoController.listarMusicos();
		for (Musico musico : musicos) {
			List<String> nomes = new ArrayList<>();

	        	for (Instrumento instrumento : musico.getInstrumento()) {
	        	    nomes.add(instrumento.getNome());
	        	}
	        	String resultado = String.join(", ", nomes);


			tabelaModelo.addRow(new Object[] {
					musico.getCodigoMusico(),
					musico.getNomeMusico() + " "+ musico.getApelidoMusico(),
					resultado,
					musico.getEmailMusico(),
					musico.getContactoMusico()
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRemover) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR ) {
				JOptionPane.showConfirmDialog(null, "Sem permissão","Sem acesso", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
            int linhaSelecionada = tabela.getSelectedRow();

            if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um músico!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja remover este músico?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

            if(confirmacao == JOptionPane.YES_OPTION) {
                int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
                boolean sucesso = musicoController.removerMusico(codigo);

                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Músico " + (String) tabelaModelo.getValueAt(linhaSelecionada, 1) + " removido!");
                    carregarMusicos();
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível remover músico por estar associado a um disco");
                }
            }
        }	
		if(e.getSource() == btnAdicionar) {
			new CadastrarMusicoDialog().setVisible(true);
			carregarMusicos();
		}
		if (e.getSource() == btnEditar) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR ) {
				JOptionPane.showConfirmDialog(null, "Sem permissão", "Sem acesso", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int linhaSelecionada = tabela.getSelectedRow();
			int codigoMusico = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
			
			Musico musico = musicoController.buscarPorCodigo(codigoMusico);
			new EditarMusicoDialog(musico).setVisible(true);
			carregarMusicos();
		}
		

	}
 }

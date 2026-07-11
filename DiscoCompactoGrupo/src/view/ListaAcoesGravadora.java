package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

import controller.GravadoraController;
import model.Gravadora;
import model.NivelAcesso;
import model.Sessao;
import resources.EstilizarTabela;

public class ListaAcoesGravadora extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaModelo;
	private GravadoraController gravadoraController;
	private JButton btnAdicionar, btnEditar, btnRemover;
	private JTable tabela;

	public ListaAcoesGravadora() {
		gravadoraController = new GravadoraController();
		
		JPanel suspenderPanel = new JPanel();
		suspenderPanel.setLayout(new BorderLayout());

		JPanel titulo = new JPanel(new BorderLayout());
		titulo.setBackground(Color.WHITE);
		titulo.setPreferredSize(new Dimension(0, 50));

		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setBackground(titulo.getBackground());

		JLabel nome = new JLabel("Lista de Gravadoras");
		JLabel descricao = new JLabel("Acções");
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


		partePesquisa.add(pesquisar);
		partePesquisa.add(txtPesquisa);
		
		txtPesquisa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

		    private void filtrar() {
		        String texto = txtPesquisa.getText().toLowerCase().trim();
		        tabelaModelo.setRowCount(0);

		        List<Gravadora> gravadoras = gravadoraController.listarGravadoras();
		        for (Gravadora g : gravadoras) {
		            if (texto.isEmpty() ||
		                g.getNomeGravadora().toLowerCase().contains(texto) ||
		                g.getEmailGravadora().toLowerCase().contains(texto) ||
		                String.valueOf(g.getContactoGravadora()).contains(texto) ||
		                String.valueOf(g.getEnderecoGravadora()).contains(texto) ||
		                String.valueOf(g.getCodigoGravadora()).contains(texto)) {

		                tabelaModelo.addRow(new Object[]{
		                    g.getCodigoGravadora(),
		                    g.getNomeGravadora(),
		                    g.getContactoGravadora(),
		                    g.getEmailGravadora(),
		                    g.getEnderecoGravadora()
		                });
		            }
		        }
		    }
		});

		titulo.add(parteDescritiva, BorderLayout.WEST);
		titulo.add(partePesquisa, BorderLayout.EAST);
		suspenderPanel.add(titulo, BorderLayout.NORTH);

		JPanel tabelaPanel = new JPanel();

		String[] colunas = {"Codigo","Nome", "Contacto", "E-mail", "Endereço"};
		tabelaModelo = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			
		tabela = new JTable(tabelaModelo);
		EstilizarTabela.aplicar(tabela);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setRowHeight(30);
		tabelaPanel.add(tabela);

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		suspenderPanel.add(scroll, BorderLayout.CENTER);
        
		btnRemover = new JButton("Remover");
        btnRemover.addActionListener(this);
        
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnAdicionar);
        suspenderPanel.add(painelBotoes, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(suspenderPanel, BorderLayout.CENTER);
		carregarGravadoras();
		
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                carregarGravadoras();
            }
        });
	}
	
	public void carregarGravadoras() {
		tabelaModelo.setRowCount(0);
		List<Gravadora> gravadoras = gravadoraController.listarGravadoras();
		for (Gravadora gravadora : gravadoras) {
			tabelaModelo.addRow(new Object[] {
					gravadora.getCodigoGravadora(),
					gravadora.getNomeGravadora(),
					gravadora.getContactoGravadora(),
					gravadora.getEmailGravadora(),
					gravadora.getEnderecoGravadora()
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRemover) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR) {
				JOptionPane.showConfirmDialog(null,
		                "Sem permissão",
		                "Sem acesso",
		                JOptionPane.YES_NO_OPTION);
				return;
			}
			
            int linhaSelecionada = tabela.getSelectedRow();

            if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma gravadora!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja remover esta gravadora?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

            if(confirmacao == JOptionPane.YES_OPTION) {
                int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
                boolean sucesso = gravadoraController.removerGravadora(codigo);

                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Gravadora " + (String) tabelaModelo.getValueAt(linhaSelecionada, 1) + " removido!");
                    carregarGravadoras();
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível remover gravadora por estar associado a um disco");
                }
            }
        }	
		if(e.getSource() == btnAdicionar) {
			new CadastrarGravadoraDialog().setVisible(true);
			carregarGravadoras();
		}
		if (e.getSource() == btnEditar) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR) {
				JOptionPane.showConfirmDialog(null,
		                "Sem permissão",
		                "Sem acesso",
		                JOptionPane.YES_NO_OPTION);
				return;
			}
			int linhaSelecionada = tabela.getSelectedRow();
			int codigoGravadora = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
			
			Gravadora gravadora = gravadoraController.buscarPorCodigo(codigoGravadora);
			new EditarGravadoraDialog(gravadora).setVisible(true);
			carregarGravadoras();
		}
		

	}
 }

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

import controller.ProdutorController;
import model.NivelAcesso;
import model.Produtor;
import model.Sessao;
import resources.EstilizarTabela;

public class ListaAcoesProdutor extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaModelo;
	private ProdutorController produtorController;
	private JButton btnAdicionar, btnEditar, btnRemover;
	private JTable tabela;

	public ListaAcoesProdutor() {
		produtorController = new ProdutorController();
		
		JPanel suspenderPanel = new JPanel();
		suspenderPanel.setLayout(new BorderLayout());

		JPanel titulo = new JPanel(new BorderLayout());
		titulo.setBackground(Color.WHITE);
		titulo.setPreferredSize(new Dimension(0, 50));

		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setBackground(titulo.getBackground());

		JLabel nome = new JLabel("Lista de Produtores");
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

		        List<Produtor> produtores = produtorController.listarProdutor();
		        for (Produtor p : produtores) {
		            if (texto.isEmpty() ||
		                p.getNomeProdutor().toLowerCase().contains(texto) ||
		                p.getApelidoProdutor().toLowerCase().contains(texto) ||
		                String.valueOf(p.getContactoProdutor()).contains(texto) ||
		                String.valueOf(p.getEmailProdutor()).contains(texto) ||
		                String.valueOf(p.getCodigoProdutor()).contains(texto)) {

		                tabelaModelo.addRow(new Object[]{
		                    p.getCodigoProdutor(),
		                    p.getNomeCompleto(),
		                    p.getContactoProdutor(),
		                    p.getEmailProdutor()
		                });
		            }
		        }
		    }
		});

		titulo.add(parteDescritiva, BorderLayout.WEST);
		titulo.add(partePesquisa, BorderLayout.EAST);
		suspenderPanel.add(titulo, BorderLayout.NORTH);

		JPanel tabelaPanel = new JPanel();

		String[] colunas = {"Codigo","Nome", "Contacto", "E-mail"};
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
		carregarProdutores();
		
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                carregarProdutores();
            }
        });
	}
	
	public void carregarProdutores() {
		tabelaModelo.setRowCount(0);
		List<Produtor> produtores = produtorController.listarProdutor();
		for (Produtor p : produtores) {
			tabelaModelo.addRow(new Object[] {
					p.getCodigoProdutor(),
                    p.getNomeCompleto(),
                    p.getContactoProdutor(),
                    p.getEmailProdutor()
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRemover) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR ) {
				JOptionPane.showConfirmDialog(null,
		                "Sem permissão",
		                "Sem acesso",
		                JOptionPane.YES_NO_OPTION);
				return;
			}
			
            int linhaSelecionada = tabela.getSelectedRow();

            if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um produtor!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja remover este produtor?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

            if(confirmacao == JOptionPane.YES_OPTION) {
                int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
                boolean sucesso = produtorController.removerProdutor(codigo);

                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Produtor " + (String) tabelaModelo.getValueAt(linhaSelecionada, 1) + " removido!");
                    carregarProdutores();
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível remover este produtor por estar associado a um disco");
                }
            }
        }	
		if(e.getSource() == btnAdicionar) {
			new CadastrarProdutorDialog().setVisible(true);
			carregarProdutores();
		}
		if (e.getSource() == btnEditar) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() == NivelAcesso.OPERADOR ) {
				JOptionPane.showConfirmDialog(null,
		                "Sem permissão",
		                "Sem acesso",
		                JOptionPane.YES_NO_OPTION);
				return;
			}
			
			int linhaSelecionada = tabela.getSelectedRow();
			
			if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um produtor!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
			
			int codigoProdutor = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);			
			Produtor produtor = produtorController.buscarPorCodigo(codigoProdutor);
			new EditarProdutorDialog(produtor).setVisible(true);
			carregarProdutores();
		}
		

	}
 }

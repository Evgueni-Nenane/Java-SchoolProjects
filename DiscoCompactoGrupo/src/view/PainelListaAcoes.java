package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
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

import controller.FicheiroTxt;
import controller.UtilizadorController;
import dao.LoginDAO;
import model.Utilizador;

public class PainelListaAcoes extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelaModelo;
	private UtilizadorController utilizadorController;
	private JButton btnRemover, btnResetSenha;
	private JTable tabela;
	

	public PainelListaAcoes(UtilizadorController utilizadorController) {
		this.utilizadorController = utilizadorController;
		
		JPanel suspenderPanel = new JPanel();
		suspenderPanel.setLayout(new BorderLayout());

		JPanel titulo = new JPanel(new BorderLayout());
		titulo.setBackground(Color.WHITE);
		titulo.setPreferredSize(new Dimension(0, 50));

		JPanel parteDescritiva = new JPanel();
		parteDescritiva.setLayout(new BoxLayout(parteDescritiva, BoxLayout.Y_AXIS));
		parteDescritiva.setBackground(titulo.getBackground());

		JLabel nome = new JLabel("Lista de Utilizadores");
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

		String[] colunas = { "Codigo", "Nome Completo", "E-mail Corporativo", "Género", "Permissões", "Telefone",
				"Status" };
		tabelaModelo = new DefaultTableModel(colunas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			
		tabela = new JTable(tabelaModelo);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setRowHeight(30);
		tabelaPanel.add(tabela);

		JScrollPane scroll = new JScrollPane(tabela);

		suspenderPanel.add(scroll, BorderLayout.CENTER);
        btnRemover = new JButton("Remover Selecionado");
        btnRemover.addActionListener(this);
        btnResetSenha = new JButton("Resetar Senha");
        btnResetSenha.addActionListener(this);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnResetSenha);
        suspenderPanel.add(painelBotoes, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(suspenderPanel, BorderLayout.CENTER);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRemover) {
            int linhaSelecionada = tabela.getSelectedRow();

            if(linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um/a utilizador/a!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja remover este/a utilizador/a?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

            if(confirmacao == JOptionPane.YES_OPTION) {
                int codigo = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
                boolean sucesso = utilizadorController.suspenderUtilizador(codigo);

                if(sucesso) {
                    JOptionPane.showMessageDialog(null, "Utilizador/a " + (String) tabelaModelo.getValueAt(linhaSelecionada, 1) + " suspenso/a!");
                    carregarUsers();
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao suspender o utilizador/a!");
                }
            }
        }	
		if(e.getSource() == btnResetSenha) {
			 int linhaSelecionada = tabela.getSelectedRow();

	            if(linhaSelecionada == -1) {
	                JOptionPane.showMessageDialog(null, "Selecione um/a utilizador/a!", "Aviso", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            
	            int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja resetar senha deste/a utilizador/a?", "Confirmar Reset de Senha",
	                    JOptionPane.YES_NO_OPTION);
	            
	            if(confirmacao == JOptionPane.YES_OPTION) {
	                int codigoUser = (int) tabelaModelo.getValueAt(linhaSelecionada, 0);
	                
	                String nomeCompleto = (String) tabelaModelo.getValueAt(linhaSelecionada, 1);
	                String parteEmail = (String) tabelaModelo.getValueAt(linhaSelecionada, 2);
	                String contacto = (String) tabelaModelo.getValueAt(linhaSelecionada, 5);
	                
	                String senha = nomeCompleto.charAt(0) + "" + parteEmail.charAt(1) + "214";
	                boolean sucesso = LoginDAO.resetarSenha(codigoUser, senha);
	                Utilizador userSenhaReset = new Utilizador(nomeCompleto, contacto, senha);
	                if(sucesso) {
	                		if(sucesso) {
	                			try {
	                				FicheiroTxt.guardarResetTxt(userSenhaReset);
	                			} catch (IOException e1) {
	                				e1.printStackTrace();
	                			}
	                			JOptionPane.showMessageDialog(null, "Utilizador cadastrado com sucesso!");
	        	        } else {
	        	            JOptionPane.showMessageDialog(null, "Erro ao cadastrar utilizador!");
	        	        }
	                    JOptionPane.showMessageDialog(null, "Senha do utilizador/a " + (String) tabelaModelo.getValueAt(linhaSelecionada, 1) + " resetada!");
	                    carregarUsers();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Erro ao resetar senha do/a utilizador/a!");
	                }
	            }
		}	
	}
 }

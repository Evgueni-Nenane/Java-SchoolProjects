package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DiscoController;
import controller.EditoraController;
import controller.GravadoraController;
import controller.LogsController;
import model.NivelAcesso;
import model.Sessao;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel painelPrincipal;

	private JButton btnRegistar, btnListar, btnBandas, btnArtistas;
	private JButton btnEstatistica, btnExportar, btnUtilizador, btnLogs, btnSair;

	private CardLayout cardPrincipal;
	private JPanel painelCentral;
	
	private DiscoController discoController;
	private EditoraController editoraController;
	private GravadoraController gravadoraController;
	private LogsController logsController;
	
	/**
	 * Create the frame.
	 */
	public Dashboard() {
		
		discoController = new DiscoController();
		editoraController = new EditoraController();
		gravadoraController = new GravadoraController();
		logsController = new LogsController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.setBackground(new Color(246, 247, 249));
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);

		// Sidebar v Menu Principal
		JPanel sidebarPanel = new JPanel();
		painelPrincipal.add(sidebarPanel, BorderLayout.WEST);
		sidebarPanel.setPreferredSize(new Dimension(200, 0));

		// Topbar Menu de Cima
		JPanel topbarPanel = new JPanel();
		painelPrincipal.add(topbarPanel, BorderLayout.NORTH);
		topbarPanel.setPreferredSize(new Dimension(0, 60));

		// Card Principal

		cardPrincipal = new CardLayout();
		painelCentral = new JPanel(cardPrincipal);
		painelCentral.setBackground(new Color(246, 247, 249));
		painelPrincipal.add(painelCentral, BorderLayout.CENTER);

		// ====================
		// Side Bar
		// ====================

		sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(50);
		verticalStrut.setBackground(new Color(254, 254, 254));
		sidebarPanel.add(verticalStrut);
		topbarPanel.setBackground(verticalStrut.getBackground());
		topbarPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));

		sidebarPanel.setBackground(verticalStrut.getBackground());
		sidebarPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)),
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));

		sidebarPanel.add(Box.createHorizontalGlue());
		// Menu de Discos
		JLabel txtDiscos = new JLabel("Discos");
		sidebarPanel.add(txtDiscos);
		txtDiscos.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnRegistar = new JButton("Registar");
		btnRegistar.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnRegistar);
		btnRegistar.setPreferredSize(new Dimension(130, 30));
		btnRegistar.setMinimumSize(new Dimension(130, 30));
		btnRegistar.setMaximumSize(new Dimension(130, 30));
		btnRegistar.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnListar = new JButton("Listar");
		btnListar.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnListar);
		btnListar.setPreferredSize(new Dimension(130, 30));
		btnListar.setMinimumSize(new Dimension(130, 30));
		btnListar.setMaximumSize(new Dimension(130, 30));
		sidebarPanel.add(Box.createVerticalStrut(10));

		// Menu Intervenientes
		JLabel txtIntervenientes = new JLabel("Intervenientes");
		sidebarPanel.add(txtIntervenientes);
		txtIntervenientes.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnBandas = new JButton("Gravadora");
		btnBandas.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBandas.addActionListener(this);
		sidebarPanel.add(btnBandas);
		btnBandas.setPreferredSize(new Dimension(130, 30));
		btnBandas.setMinimumSize(new Dimension(130, 30));
		btnBandas.setMaximumSize(new Dimension(130, 30));
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnArtistas = new JButton("Editora");
		btnArtistas.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnArtistas.addActionListener(this);
		sidebarPanel.add(btnArtistas);
		btnArtistas.setPreferredSize(new Dimension(130, 30));
		btnArtistas.setMinimumSize(new Dimension(130, 30));
		btnArtistas.setMaximumSize(new Dimension(130, 30));
		sidebarPanel.add(Box.createVerticalStrut(10));

		// Menu Relatórios
		JLabel txtRelatorios = new JLabel("Relatórios");
		sidebarPanel.add(txtRelatorios);
		txtRelatorios.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnEstatistica = new JButton("Estatisticas");
		btnEstatistica.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnEstatistica);
		btnEstatistica.setPreferredSize(new Dimension(130, 30));
		btnEstatistica.setMinimumSize(new Dimension(130, 30));
		btnEstatistica.setMaximumSize(new Dimension(130, 30));
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnExportar = new JButton("Exportar");
		btnExportar.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnExportar);
		btnExportar.setPreferredSize(new Dimension(130, 30));
		btnExportar.setMinimumSize(new Dimension(130, 30));
		btnExportar.setMaximumSize(new Dimension(130, 30));
		sidebarPanel.add(Box.createVerticalStrut(10));

		// Menu Administradores
		JLabel txtAdmin = new JLabel("Administração");
		sidebarPanel.add(txtAdmin);
		txtAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnUtilizador = new JButton("Utilizador");
		btnUtilizador.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnUtilizador);
		btnUtilizador.setPreferredSize(new Dimension(130, 30));
		btnUtilizador.setMinimumSize(new Dimension(130, 30));
		btnUtilizador.setMaximumSize(new Dimension(130, 30));
		btnUtilizador.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnLogs = new JButton("Acessar Logs");
		btnLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnLogs);
		btnLogs.setPreferredSize(new Dimension(130, 30));
		btnLogs.setMinimumSize(new Dimension(130, 30));
		btnLogs.setMaximumSize(new Dimension(130, 30));
		btnLogs.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(50));

		// Botao Sair
		btnSair = new JButton("Sair");
		btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSair.setPreferredSize(new Dimension(130, 30));
		btnSair.setMinimumSize(new Dimension(130, 30));
		btnSair.setMaximumSize(new Dimension(130, 30));
		btnSair.addActionListener(this);
		sidebarPanel.add(btnSair);

		// ===================
		// 		Top Bar
		// ===================
		topbarPanel.setLayout(new BorderLayout());
		JLabel lblSistema = new JLabel("Sistema de Gestão de Discos");
		lblSistema.setBackground(verticalStrut.getBackground());

		topbarPanel.add(lblSistema, BorderLayout.WEST);

		JPanel innerUser = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JLabel fotoUser = new JLabel();
		fotoUser.setPreferredSize(new Dimension(40, 40));
		fotoUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		innerUser.add(fotoUser);
		JPanel infoUser = new JPanel();
		infoUser.setBackground(Color.black);
		infoUser.setBackground(verticalStrut.getBackground());
		infoUser.setLayout(new BoxLayout(infoUser, BoxLayout.Y_AXIS));

		JLabel lblNomeUser = new JLabel();
		lblNomeUser.setText(Sessao.getUtilizadorLogado().getUser_name());
		JLabel lblEmailUser = new JLabel();
		lblEmailUser.setText(Sessao.getUtilizadorLogado().getPerfil().name());
		
		infoUser.add(lblNomeUser);
		infoUser.add(lblEmailUser);

		innerUser.add(infoUser);
		innerUser.setBackground(verticalStrut.getBackground());
		topbarPanel.add(innerUser, BorderLayout.CENTER);
		
		
		PainelDisco painelDisco = new PainelDisco(cardPrincipal, painelCentral, discoController, editoraController, gravadoraController, logsController);
		painelDisco.setBackground(new Color(246, 247, 249));
		painelCentral.add(painelDisco, "Discos");
		
		PainelUtilizadores painelUtilizadores = new PainelUtilizadores();
		painelUtilizadores.setBackground(new Color(246, 247, 249));
		painelPrincipal.setBackground(new Color(246, 247, 249));
		painelCentral.add(painelUtilizadores, "Utilizador");
		
		PainelLogs painelLogs = new PainelLogs(logsController);
		painelUtilizadores.setBackground(new Color(246, 247, 249));
		painelCentral.add(painelLogs, "Logs_Sistema");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUtilizador) {
			if (Sessao.getUtilizadorLogado().getPerfil() != NivelAcesso.ADMINISTRADOR) {
				JOptionPane.showMessageDialog(null, "Sem permissão suficientes", "Erro de Permissão", JOptionPane.WARNING_MESSAGE);
				return;
			}
			cardPrincipal.show(painelCentral, "Utilizador");
		}
		if (e.getSource() == btnRegistar) {
			cardPrincipal.show(painelCentral, "Discos");
		}
		if (e.getSource() == btnListar) {
			cardPrincipal.show(painelCentral, "Discos");
		}
		if (e.getSource() == btnBandas) {
			new CadastrarGravadoraDialog().setVisible(true);
		}
		if (e.getSource() == btnArtistas) {
			new CadastrarEditoraDialog(editoraController).setVisible(true);
		}
		if (e.getSource() == btnSair) {
			dispose();
		}
		if (e.getSource() == btnLogs) {
			if (Sessao.getUtilizadorLogado().getPerfil() != NivelAcesso.AUDITOR) {
				JOptionPane.showMessageDialog(null, "Sem permissão suficiente", "Erro de Permissão", JOptionPane.WARNING_MESSAGE);
				return;
			}
			cardPrincipal.show(painelCentral, "Logs_Sistema");
		}

	}

}

package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.DiscoController;
import controller.EditoraController;
import controller.GravadoraController;
import controller.LogsController;
import model.Logs;
import model.NivelAcesso;
import model.Sessao;
import resources.EstilizarBotao;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel painelPrincipal;

	private JButton btnRegistar, btnArtistas, btnProducao;
	private JButton  btnExportar, btnUtilizador, btnLogs, btnSair;

	private CardLayout cardPrincipal;
	private JPanel painelCentral;
	
	private DiscoController discoController;
	private EditoraController editoraController;
	private GravadoraController gravadoraController;
	private LogsController logsController;
	private Logs log;
	
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
		EstilizarBotao.aplicar(btnRegistar);
		btnRegistar.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnRegistar);
		btnRegistar.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));

		// Menu Intervenientes
		JLabel txtIntervenientes = new JLabel("Intervenientes");
		sidebarPanel.add(txtIntervenientes);
		txtIntervenientes.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));
		
		btnArtistas = new JButton("Artistas");
		EstilizarBotao.aplicar(btnArtistas);
		btnArtistas.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnArtistas);
		btnArtistas.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnProducao = new JButton("Produção");
		EstilizarBotao.aplicar(btnProducao);
		btnProducao.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnProducao);
		btnProducao.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));
		
		// Menu Relatórios
		JLabel txtRelatorios = new JLabel("Relatórios");
		sidebarPanel.add(txtRelatorios);
		txtRelatorios.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnExportar = new JButton("Exportar");
		EstilizarBotao.aplicar(btnExportar);
		btnExportar.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnExportar);
		btnExportar.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));

		// Menu Administradores
		JLabel txtAdmin = new JLabel("Administração");
		sidebarPanel.add(txtAdmin);
		txtAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnUtilizador = new JButton("Utilizador");
		EstilizarBotao.aplicar(btnUtilizador);
		btnUtilizador.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnUtilizador);
		btnUtilizador.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(10));

		btnLogs = new JButton("Acessar Logs");
		EstilizarBotao.aplicar(btnLogs);
		btnLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnLogs);
		btnLogs.addActionListener(this);
		sidebarPanel.add(Box.createVerticalStrut(50));

		// Botao Sair
		btnSair = new JButton("Sair");
		EstilizarBotao.aplicar(btnSair);
		btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSair.addActionListener(this);
		sidebarPanel.add(btnSair);

		// ===================
		// 		Top Bar
		// ===================
		topbarPanel.setLayout(new BorderLayout());
		JLabel lblSistema = new JLabel("Sistema de Gestão de Discos");
		lblSistema.setFont(new Font("Montserrat", Font.BOLD, 16));
		lblSistema.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
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
		lblNomeUser.setText(Sessao.getUtilizadorLogado().getNome() + " "+ Sessao.getUtilizadorLogado().getApelido());
		JLabel lblEmailUser = new JLabel();
		lblEmailUser.setText(Sessao.getUtilizadorLogado().getPerfil().getNome());
		
		if (Sessao.getUtilizadorLogado().getFoto() != null) {
			ImageIcon icon = new ImageIcon(Sessao.getUtilizadorLogado().getFoto());
			Image imagemEscalada = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			fotoUser.setIcon(new ImageIcon(imagemEscalada));
		}
		
		infoUser.add(lblNomeUser);
		infoUser.add(lblEmailUser);
	
		innerUser.add(infoUser);
		innerUser.setBackground(verticalStrut.getBackground());
		topbarPanel.add(innerUser, BorderLayout.CENTER);
		

		
		PainelDisco painelDisco = new PainelDisco(cardPrincipal, painelCentral, discoController, editoraController, gravadoraController, logsController);
		painelDisco.setBackground(new Color(246, 247, 249));
		painelCentral.add(painelDisco, "Discos");
		
		PainelArtistas painelArtista = new PainelArtistas(logsController);
		painelArtista.setBackground(new Color(246, 247, 249));
		painelCentral.add(painelArtista, "Artistas");
		
		PainelProducao painelProducao = new PainelProducao(logsController);
		painelProducao.setBackground(new Color(246, 247, 249));
		painelCentral.add(painelProducao, "Producao");
		
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
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() != NivelAcesso.ADMINISTRADOR) {
				JOptionPane.showMessageDialog(null, "Sem permissão suficientes", "Erro de Permissão", JOptionPane.WARNING_MESSAGE);
				return;
			}
			cardPrincipal.show(painelCentral, "Utilizador");
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Acessou o painel users", horaAgora);
			logsController.inserirLog(log);
		}
		if (e.getSource() == btnArtistas) {
			cardPrincipal.show(painelCentral, "Artistas");
		}
		if (e.getSource() == btnProducao) {
			cardPrincipal.show(painelCentral, "Producao");
		}
		if (e.getSource() == btnExportar) {
			DialogExportar dialog = new DialogExportar(this);
			dialog.setVisible(true);
		}
		if (e.getSource() == btnRegistar) {
			cardPrincipal.show(painelCentral, "Discos");
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Acessou o painel de discos", horaAgora);
			logsController.inserirLog(log);
		}
		if (e.getSource() == btnSair) {
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Saiu do sistema", horaAgora);
			logsController.inserirLog(log);
			dispose();
		}
		if (e.getSource() == btnLogs) {
			if (Sessao.getUtilizadorLogado().getPerfil().getCodigoNivel() != NivelAcesso.AUDITOR) {
				JOptionPane.showMessageDialog(null, "Sem permissão suficiente", "Erro de Permissão", JOptionPane.WARNING_MESSAGE);
				return;
			}
			cardPrincipal.show(painelCentral, "Logs_Sistema");
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Acessou aos Logs do Sistema", horaAgora);
			logsController.inserirLog(log);
		}

	}

}

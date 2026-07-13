package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JPanel;
import controller.LogsController;
import controller.UtilizadorController;
import model.Logs;
import model.Sessao;
import resources.EstilizarBotao;

public class PainelUtilizadores extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton btnCadastrarUsers, btnSuspenderUser;
	private UtilizadorController utilizadorController;
	private LogsController logController;
	private Logs log;

	public PainelUtilizadores() {
		this.utilizadorController = new UtilizadorController();
		this.logController = new LogsController();
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel mainTopBar = new JPanel();
		mainTopBar.setPreferredSize(new Dimension(0, 50));

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		mainPanel.add(cardPanel, BorderLayout.CENTER);

		btnCadastrarUsers = new JButton("Cadastrar Utilizadores");
		EstilizarBotao.aplicarSec(btnCadastrarUsers);
		btnCadastrarUsers.addActionListener(this);

		btnSuspenderUser = new JButton("Informações de Utilizador");
		EstilizarBotao.aplicarSec(btnCadastrarUsers);
		btnSuspenderUser.addActionListener(this);

		mainTopBar.add(btnCadastrarUsers);
		mainTopBar.add(btnSuspenderUser);
		mainTopBar.setBackground(new Color(254, 254, 254));

		mainPanel.add(mainTopBar, BorderLayout.NORTH);
		mainPanel.add(cardPanel, BorderLayout.CENTER);

		cardPanel.add(new PainelCadastroUsers(utilizadorController, logController), "Cadastro");
		cardPanel.add(new PainelListaAcoes(utilizadorController, logController), "Suspender User");

		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCadastrarUsers) {
			cardLayout.show(cardPanel, "Cadastro");
			LocalDateTime horaAgora = LocalDateTime.now();
			log = new Logs(
					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Acessou o painel cadastrar users", horaAgora);
			logController.inserirLog(log);
		}
		if (e.getSource() == btnSuspenderUser) {
			cardLayout.show(cardPanel, "Suspender User");
			LocalDateTime horaAgora3 = LocalDateTime.now();
			log = new Logs(
					Sessao.getUtilizadorLogado().getCodigo(), Sessao.getUtilizadorLogado().getNome(),
					Sessao.getUtilizadorLogado().getApelido(), Sessao.getUtilizadorLogado().getPerfil().getNome(),
					Sessao.getUtilizadorLogado().getEmail(), "Acessou o painel acções de users", horaAgora3);
			logController.inserirLog(log);
		}
	}
}

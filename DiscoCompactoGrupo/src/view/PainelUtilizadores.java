package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.UtilizadorController;

public class PainelUtilizadores extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton btnCadastrarUsers, btnSuspenderUser, btnListarUsers;
	private UtilizadorController utilizadorController;

	public PainelUtilizadores() {
		this.utilizadorController = new UtilizadorController();
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel mainTopBar = new JPanel();
		mainTopBar.setPreferredSize(new Dimension(0, 50));

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		mainPanel.add(cardPanel, BorderLayout.CENTER);

		btnCadastrarUsers = new JButton("Cadastrar Utilizadores");
		btnCadastrarUsers.addActionListener(this);

		btnListarUsers = new JButton("Listar Utilizadores");
		btnListarUsers.addActionListener(this);

		btnSuspenderUser = new JButton("Suspender Utilizador");
		btnSuspenderUser.addActionListener(this);

		mainTopBar.add(btnCadastrarUsers);
		mainTopBar.add(btnListarUsers);
		mainTopBar.add(btnSuspenderUser);
		mainTopBar.setBackground(new Color(254, 254, 254));

		mainPanel.add(mainTopBar, BorderLayout.NORTH);
		mainPanel.add(cardPanel, BorderLayout.CENTER);

		cardPanel.add(new PainelCadastroUsers(utilizadorController), "Cadastro");
		cardPanel.add(new PainelListaUsers(utilizadorController), "Lista");
		cardPanel.add(new PainelListaAcoes(utilizadorController), "Suspender User");

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
		}
		if (e.getSource() == btnListarUsers) {
			cardLayout.show(cardPanel, "Lista");
		}
		if (e.getSource() == btnSuspenderUser) {
			cardLayout.show(cardPanel, "Suspender User");
		}
	}
}

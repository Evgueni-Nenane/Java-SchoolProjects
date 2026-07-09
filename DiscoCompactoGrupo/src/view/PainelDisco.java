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

import controller.DiscoController;
import controller.EditoraController;
import controller.GravadoraController;
import controller.LogsController;
import resources.EstilizarBotao;

public class PainelDisco extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnRegistar;
	private CardLayout cardPrincipal;
	private JPanel painelCentral;
	private JPanel menuPrincipal;
	private JButton btnCadastrarDiscos, btnListarDiscos;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	private ListaAcoesDiscos listaAcoesDisco;
	private CadastrarDiscos cadastrarDiscos;
	
	public PainelDisco(CardLayout cardPrincipal, JPanel painelCentral, DiscoController discoController, EditoraController editoraController, GravadoraController gravadoraController, LogsController logController) {
		this.cardPrincipal = cardPrincipal;
		this.painelCentral = painelCentral;
		listaAcoesDisco = new ListaAcoesDiscos(discoController, logController);
		cadastrarDiscos = new CadastrarDiscos(discoController, editoraController, gravadoraController, logController, listaAcoesDisco);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		menuPrincipal = new JPanel(new BorderLayout());
		JPanel topbarPrincipal = new JPanel();
		topbarPrincipal.setBackground(new Color(246, 247, 249));
		topbarPrincipal.setPreferredSize(new Dimension(0, 50));

		btnCadastrarDiscos = new JButton("Cadastrar Discos");
		EstilizarBotao.aplicarSec(btnCadastrarDiscos);
		btnCadastrarDiscos.addActionListener(this);
		
		btnListarDiscos = new JButton("Listar Discos");
		EstilizarBotao.aplicarSec(btnListarDiscos);
		btnListarDiscos.addActionListener(this);

		topbarPrincipal.add(btnCadastrarDiscos);
		topbarPrincipal.add(btnListarDiscos);

		topbarPrincipal.setBackground(new Color(254, 254, 254));
		
		
		cardPanel.add(cadastrarDiscos, "Cadastrar");
		cardPanel.add(listaAcoesDisco, "Lista");

		menuPrincipal.add(topbarPrincipal, BorderLayout.NORTH);
		menuPrincipal.add(cardPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(menuPrincipal, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistar) {
			cardPrincipal.show(painelCentral, "Utilizador");
		}

		if (e.getSource() == btnCadastrarDiscos) {
			cardLayout.show(cardPanel, "Cadastrar");
		}
		if (e.getSource() == btnListarDiscos) {
			cardLayout.show(cardPanel, "Lista");
			listaAcoesDisco.carregarDiscos();
		}
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

}

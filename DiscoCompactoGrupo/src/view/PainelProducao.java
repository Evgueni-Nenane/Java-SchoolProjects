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

import controller.LogsController;
import resources.EstilizarBotao;

public class PainelProducao extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnRegistar;
	private JPanel menuPrincipal;
	private JButton btnListarProdutor, btnListarGravadora, btnListarEditora;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	private ListaAcoesGravadora listaAcoesGravadora;
	private ListaAcoesProdutor listaAcoesProdutor;
	private ListaAcoesEditora listaAcoesEditora;
	
	public PainelProducao(LogsController logController) {
		listaAcoesGravadora = new ListaAcoesGravadora();
		listaAcoesProdutor = new ListaAcoesProdutor();
		listaAcoesEditora = new ListaAcoesEditora();
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		menuPrincipal = new JPanel(new BorderLayout());
		JPanel topbarPrincipal = new JPanel();
		topbarPrincipal.setBackground(new Color(246, 247, 249));
		topbarPrincipal.setPreferredSize(new Dimension(0, 50));

		btnListarProdutor = new JButton("Listar Produtores");
		EstilizarBotao.aplicarSec(btnListarProdutor);
		btnListarProdutor.addActionListener(this);
		
		btnListarGravadora = new JButton("Listar Gravadoras");
		EstilizarBotao.aplicarSec(btnListarGravadora);
		btnListarGravadora.addActionListener(this);

		btnListarEditora = new JButton("Listar Editora");
		EstilizarBotao.aplicarSec(btnListarEditora);
		btnListarEditora.addActionListener(this);
		
		topbarPrincipal.add(btnListarProdutor);
		topbarPrincipal.add(btnListarGravadora);
		topbarPrincipal.add(btnListarEditora);

		topbarPrincipal.setBackground(new Color(254, 254, 254));
		
		
		cardPanel.add(listaAcoesProdutor, "Produtor");
		cardPanel.add(listaAcoesGravadora, "Gravadora");
		cardPanel.add(listaAcoesEditora, "Editora");

		menuPrincipal.add(topbarPrincipal, BorderLayout.NORTH);
		menuPrincipal.add(cardPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(menuPrincipal, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistar) {
		}

		if (e.getSource() == btnListarProdutor) {
			cardLayout.show(cardPanel, "Produtor");
		}
		if (e.getSource() == btnListarGravadora) {
			cardLayout.show(cardPanel, "Gravadora");
		}
		if (e.getSource() == btnListarEditora) {
			cardLayout.show(cardPanel, "Editora");
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

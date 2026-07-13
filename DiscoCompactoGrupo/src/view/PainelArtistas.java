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

public class PainelArtistas extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnRegistar;
	private JPanel menuPrincipal;
	private JButton btnListarMusico, btnListarCompositor, btnListarCantor;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	private ListaAcoesMusico listaAcoesMusico;
	private ListaAcoesCompositor listaAcoesCompositor;
	private ListaAcoesCantor listaAcoesCantor;
	
	public PainelArtistas(LogsController logController) {
		listaAcoesCompositor = new ListaAcoesCompositor();
		listaAcoesMusico = new ListaAcoesMusico();
		listaAcoesCantor= new ListaAcoesCantor();
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		menuPrincipal = new JPanel(new BorderLayout());
		JPanel topbarPrincipal = new JPanel();
		topbarPrincipal.setBackground(new Color(246, 247, 249));
		topbarPrincipal.setPreferredSize(new Dimension(0, 50));

		btnListarMusico = new JButton("Listar Musicos");
		EstilizarBotao.aplicarSec(btnListarMusico);
		btnListarMusico.addActionListener(this);
		
		btnListarCompositor = new JButton("Listar Compositores");
		EstilizarBotao.aplicarSec(btnListarCompositor);
		btnListarCompositor.addActionListener(this);

		btnListarCantor = new JButton("Listar Cantores");
		EstilizarBotao.aplicarSec(btnListarCantor);
		btnListarCantor.addActionListener(this);
		topbarPrincipal.add(btnListarMusico);
		topbarPrincipal.add(btnListarCompositor);
		topbarPrincipal.add(btnListarCantor);

		topbarPrincipal.setBackground(new Color(254, 254, 254));
		
		
		cardPanel.add(listaAcoesMusico, "Musico");
		cardPanel.add(listaAcoesCompositor, "Compositor");
		cardPanel.add(listaAcoesCantor, "Cantor");

		menuPrincipal.add(topbarPrincipal, BorderLayout.NORTH);
		menuPrincipal.add(cardPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(menuPrincipal, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistar) {
		}

		if (e.getSource() == btnListarMusico) {
			cardLayout.show(cardPanel, "Musico");
		}
		if (e.getSource() == btnListarCantor) {
			cardLayout.show(cardPanel, "Cantor");
		}
		if (e.getSource() == btnListarCompositor) {
			cardLayout.show(cardPanel, "Compositor");
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

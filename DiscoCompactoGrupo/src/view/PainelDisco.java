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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.DiscoController;
import controller.EditoraController;
import controller.GravadoraController;
import controller.LogsController;
import model.Logs;
import model.NivelAcesso;
import model.Sessao;

public class PainelDisco extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnRegistar;
	private CardLayout cardPrincipal;
	private JPanel painelCentral;
	private JPanel menuPrincipal;
	private JButton btnCadastrarDiscos, btnRemoverDiscos, btnListarDiscos;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	private DiscoController discoController;
	private EditoraController editoraController;
	private GravadoraController gravadoraController;
	private Logs log;
	private LogsController logController;
	
	public PainelDisco(CardLayout cardPrincipal, JPanel painelCentral, DiscoController discoController, EditoraController editoraController, GravadoraController gravadoraController, LogsController logController) {
		this.cardPrincipal = cardPrincipal;
		this.painelCentral = painelCentral;
		this.discoController = discoController;
		this.editoraController = editoraController;
		this.gravadoraController = gravadoraController;
		this.logController = logController;
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		menuPrincipal = new JPanel(new BorderLayout());
		JPanel topbarPrincipal = new JPanel();
		topbarPrincipal.setBackground(new Color(246, 247, 249));
		topbarPrincipal.setPreferredSize(new Dimension(0, 50));

		btnCadastrarDiscos = new JButton("Cadastrar Discos");
		btnCadastrarDiscos.addActionListener(this);

		btnListarDiscos = new JButton("Listar Discos");
		btnListarDiscos.addActionListener(this);

		btnRemoverDiscos = new JButton("Remover Discos");
		btnRemoverDiscos.addActionListener(this);

		topbarPrincipal.add(btnCadastrarDiscos);
		topbarPrincipal.add(btnListarDiscos);
		topbarPrincipal.add(btnRemoverDiscos);

		topbarPrincipal.setBackground(new Color(254, 254, 254));

		cardPanel.add(new CadastrarDiscos(discoController, editoraController, gravadoraController, logController), "Cadastrar");
		cardPanel.add(new ListarPainelCD(discoController), "Listar");
		cardPanel.add(new ListaAcoesDiscos(discoController), "Remover");

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
		if (e.getSource() == btnListarDiscos) {
			cardLayout.show(cardPanel, "Listar");
		}
		if (e.getSource() == btnCadastrarDiscos) {
			cardLayout.show(cardPanel, "Cadastrar");
			System.out.println("Cadastrar Disco");
		}
		if (e.getSource() == btnRemoverDiscos) {
			if(Sessao.getUtilizadorLogado().getPerfil() == NivelAcesso.OPERADOR) {
				JOptionPane.showMessageDialog(null, "Sem permissão suficiente", "Erro de permissão", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cardLayout.show(cardPanel, "Remover");
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

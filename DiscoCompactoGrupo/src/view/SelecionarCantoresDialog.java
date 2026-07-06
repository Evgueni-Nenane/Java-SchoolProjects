package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import controller.CantorController;
import model.Cantor;

public class SelecionarCantoresDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private CantorController cantorController;
	private List<Cantor> cantoresSelecionados;
	private DefaultTableModel model;
	private JTable tabela;
	private JButton btnSelecionar, btnCancelar;
	
	public SelecionarCantoresDialog(CantorController cantorController, List<Cantor> selecionados) {
		this.cantorController = cantorController;
		this.cantoresSelecionados = new ArrayList<>(selecionados);
		
		setTitle("Selecionar Cantores");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setModal(true);
		setLayout(new BorderLayout());
		
		String[] colunas = {"Selecionar", "Nome", "Apelido"};
		model = new DefaultTableModel(colunas, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0) return Boolean.class;
				return String.class;
			}
		};
		
		tabela = new JTable(model);
		tabela.setRowHeight(25);
		
		carregarCantores();
		
		JScrollPane scroll = new JScrollPane(tabela);
		add(scroll, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnSelecionar = new JButton("Selecionar");
		btnCancelar = new JButton("Cancelar");
		btnSelecionar.addActionListener(this);
		btnCancelar.addActionListener(this);
		bottomPanel.add(btnSelecionar);
		bottomPanel.add(btnCancelar);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void carregarCantores() {
		model.setRowCount(0);
		List<Cantor> cantores = cantorController.listarCantor();
		
		for (Cantor c : cantores) {
			boolean selecionado = cantoresSelecionados.stream()
					.anyMatch(s -> s.getCodigoCantor() == c.getCodigoCantor());
			model.addRow(new Object[]{
				selecionado,
				c.getNomeCantor(),
				c.getApelidoCantor()
			});
		}
	}
	
	public List<Cantor> getCantoresSelecionados() {
		return cantoresSelecionados;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSelecionar) {
			cantoresSelecionados.clear();
			List<Cantor> todos = cantorController.listarCantor();
			
			for (int i = 0; i < model.getRowCount(); i++) {
				boolean selecionado = (boolean) model.getValueAt(i, 0);
				if (selecionado) {
					cantoresSelecionados.add(todos.get(i));
				}
			}
			dispose();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
}
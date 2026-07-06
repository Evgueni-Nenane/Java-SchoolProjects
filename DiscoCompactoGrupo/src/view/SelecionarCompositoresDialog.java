package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import controller.CompositorController;
import model.Compositor;

public class SelecionarCompositoresDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private CompositorController compositorController;
	private List<Compositor> compositoresSelecionados;
	private DefaultTableModel model;
	private JTable tabela;
	private JButton btnSelecionar, btnCancelar;
	
	public SelecionarCompositoresDialog(CompositorController compositorController, List<Compositor> selecionados) {
		this.compositorController = compositorController;
		this.compositoresSelecionados = new ArrayList<>(selecionados);
		
		setTitle("Selecionar Compositores");
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
		
		carregarCompositores();
		
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
	
	private void carregarCompositores() {
		model.setRowCount(0);
		List<Compositor> compositores = compositorController.listarCompositor();
		
		for (Compositor c : compositores) {
			boolean selecionado = compositoresSelecionados.stream()
					.anyMatch(s -> s.getCodigoCompositor() == c.getCodigoCompositor());
			model.addRow(new Object[]{
				selecionado,
				c.getNomeCompositor(),
				c.getApelidoCompositor()
			});
		}
	}
	
	public List<Compositor> getCompositoresSelecionados() {
		return compositoresSelecionados;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSelecionar) {
			compositoresSelecionados.clear();
			List<Compositor> todos = compositorController.listarCompositor();
			
			for (int i = 0; i < model.getRowCount(); i++) {
				boolean selecionado = (boolean) model.getValueAt(i, 0);
				if (selecionado) {
					compositoresSelecionados.add(todos.get(i));
				}
			}
			dispose();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
}
package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import controller.MusicoController;
import model.Musico;

public class SelecionarMusicosDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private MusicoController musicoController;
	private List<Musico> musicosSelecionados;
	private DefaultTableModel model;
	private JTable tabela;
	private JButton btnSelecionar, btnCancelar;
	
	public SelecionarMusicosDialog(MusicoController musicoController, List<Musico> selecionados) {
		this.musicoController = musicoController;
		this.musicosSelecionados = new ArrayList<>(selecionados);
		
		setTitle("Selecionar Músicos");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setModal(true);
		setLayout(new BorderLayout());
		
		String[] colunas = {"Selecionar", "Nome", "Instrumento"};
		model = new DefaultTableModel(colunas, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0) return Boolean.class;
				return String.class;
			}
		};
		
		tabela = new JTable(model);
		tabela.setRowHeight(25);
		
		carregarMusicos();
		
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
	
	private void carregarMusicos() {
		model.setRowCount(0);
		List<Musico> musicos = musicoController.listarMusicos();
		
		for (Musico m : musicos) {
			boolean selecionado = musicosSelecionados.stream()
					.anyMatch(s -> s.getCodigoMusico() == m.getCodigoMusico());
			model.addRow(new Object[]{
				selecionado,
				m.getNomeMusico(),
				m.getInstrumento()
			});
		}
	}
	
	public List<Musico> getMusicosSelecionados() {
		return musicosSelecionados;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSelecionar) {
			musicosSelecionados.clear();
			List<Musico> todos = musicoController.listarMusicos();
			
			for (int i = 0; i < model.getRowCount(); i++) {
				boolean selecionado = (boolean) model.getValueAt(i, 0);
				if (selecionado) {
					musicosSelecionados.add(todos.get(i));
				}
			}
			dispose();
		} else if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
}
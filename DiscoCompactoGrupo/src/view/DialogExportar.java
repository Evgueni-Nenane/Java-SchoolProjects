package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.*;

import dao.DiscoDAO;
import model.DiscoCompacto;

public class DialogExportar extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnExportar, btnCancelar;
	private DiscoDAO discoDAO;


	public DialogExportar(JFrame grande) {
	    super(grande, "Exportar Dados", true);

	    // Cria o DAO
	    this.discoDAO = new DiscoDAO();

	    setSize(350, 180);
	    setLocationRelativeTo(grande);
	    setLayout(new BorderLayout());
	    setResizable(false);

	    JPanel conteudo = new JPanel();
	    conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
	    conteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    JLabel lbl = new JLabel("Exportar lista de discos para .txt");
	    conteudo.add(lbl);
	    conteudo.add(Box.createVerticalStrut(20));

	    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.addActionListener(this);

	    btnExportar = new JButton("Exportar");
	    btnExportar.addActionListener(this);

	    botoes.add(btnCancelar);
	    botoes.add(btnExportar);

	    add(conteudo, BorderLayout.CENTER);
	    add(botoes, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			this.dispose(); // fecha o diálogo
		}
		if (e.getSource() == btnExportar) {
			exportarParaTxt();
		}
	}

	private void exportarParaTxt() {

	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Guardar ficheiro de exportação");
	    fileChooser.setSelectedFile(new File("Discos.txt"));

	    int resultado = fileChooser.showSaveDialog(this);

	    if (resultado != JFileChooser.APPROVE_OPTION) {
	        return;
	    }

	    File ficheiro = fileChooser.getSelectedFile();

	    try (PrintWriter writer = new PrintWriter(new FileWriter(ficheiro))) {

	        // Vai buscar todos os discos à base de dados
	        List<DiscoCompacto> discos = discoDAO.listarTodos();

	        writer.println("============= LISTA DE DISCOS =============");
	        writer.println();

	        // Percorre a lista de discos
	        for (DiscoCompacto disco : discos) {

	            writer.println("Código: " + disco.getCodigoDisco());
	            writer.println("Título: " + disco.getTitulo());
	            writer.println("Género: " + disco.getGeneroMusical());
	            writer.println("Preço: " + disco.getPreco() + " MT");
	            writer.println("Ano de edição: " + disco.getAnoEdicao());
	            writer.println("Idade do disco: "
	                    + disco.discoExistencia(disco.getAnoEdicao()) + " anos");

	            writer.println("-------------------------------------------");
	        }

	        JOptionPane.showMessageDialog(
	                this,
	                "Exportação concluída!\n\nFicheiro guardado em:\n"
	                        + ficheiro.getAbsolutePath());

	        dispose();

	    } catch (IOException ex) {

	        JOptionPane.showMessageDialog(
	                this,
	                "Erro ao exportar:\n" + ex.getMessage(),
	                "Erro",
	                JOptionPane.ERROR_MESSAGE);

	    }
	}
}
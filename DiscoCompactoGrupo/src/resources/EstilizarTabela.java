package resources;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;

public class EstilizarTabela {

	    public static void aplicar(JTable tabela){
	        tabela.setRowHeight(30);
	        tabela.setFont(new Font("Arial", Font.PLAIN, 13));
	        tabela.setShowGrid(true);
	        tabela.setGridColor(Color.LIGHT_GRAY);

	        tabela.getTableHeader()
	              .setFont(new Font("Arial", Font.BOLD, 13));
	    }
	}

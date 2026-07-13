package resources;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class EstilizarBotao implements MouseListener {
   
	public static void aplicar(JButton botao){
	    	botao.setBackground(new Color(19, 175, 119));
	    	botao.setForeground(Color.white);
	    	botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
	    	botao.setPreferredSize(new Dimension(130, 30));
		botao.setMinimumSize(new Dimension(130, 30));
		botao.setMaximumSize(new Dimension(130, 30));
		
    		botao.setFocusPainted(false);
    		botao.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    		botao.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent evt) {
                    botao.setBackground(new Color(15, 204, 170));
                    botao.setForeground(Color.WHITE);
                    botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    botao.setBackground(new Color(19, 175, 119));
                    botao.setForeground(Color.WHITE);
                    botao.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
    	
    }
    public static void aplicarSec(JButton botao) {
	    	botao.setBackground(new Color(16, 185, 129));
	    	botao.setForeground(Color.WHITE);
	    	botao.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    	botao.setFocusPainted(false);
    }
    public static void aplicarTerc(JButton botao) {
	    	botao.setBackground(new Color(240, 240, 240));
	    	botao.setForeground(Color.BLACK);
	    	botao.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    	botao.setFocusPainted(false);
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

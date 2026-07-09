package controller;

import java.io.*;

import model.Utilizador;

import javax.swing.*;

public class FicheiroTxt {


	public static void guardarTxt(Utilizador u) throws IOException {

		String userHome = System.getProperty("user.home");
		File documentosOneDrive = new File(userHome + File.separator + "OneDrive" + File.separator + "Documents"+ File.separator + "Credenciais");
		File documentosNormal = new File(userHome + File.separator + "Documents"+ File.separator + "Credenciais");

		File dirFinal;
		if (documentosOneDrive.getParentFile().exists()) {
			dirFinal = documentosOneDrive;
		} else {
			dirFinal = documentosNormal;
		}
		

		if (!dirFinal.exists()) {
			if (!dirFinal.mkdir()) {
				JOptionPane.showMessageDialog(null, "Directório não encontrado!",
						"Sem directório", JOptionPane.WARNING_MESSAGE);
				return;
			}			
		}
			FileWriter file = new FileWriter(new File(dirFinal,u.getNome()+u.getApelido()+ "_Credenciais"+".txt"), true);
			PrintWriter pw = new PrintWriter(file);
			pw.printf(u.toString()+"\n\n");
			
			pw.close();
			file.close();
	}
	public static void guardarResetTxt(Utilizador u) throws IOException {

		String userHome = System.getProperty("user.home");
		File documentosOneDrive = new File(userHome + File.separator + "OneDrive" + File.separator + "Documents"+ File.separator + "Credenciais_Resets");
		File documentosNormal = new File(userHome + File.separator + "Documents"+ File.separator + "Credenciais_Resets");

		File dirFinal;
		if (documentosOneDrive.getParentFile().exists()) {
			dirFinal = documentosOneDrive;
		} else {
			dirFinal = documentosNormal;
		}
		

		if (!dirFinal.exists()) {
			if (!dirFinal.mkdir()) {
				JOptionPane.showMessageDialog(null, "Directório não encontrado!",
						"Sem directório", JOptionPane.WARNING_MESSAGE);
				return;
			}			
		}
			FileWriter file = new FileWriter(new File(dirFinal,u.getNomeCompleto()+ "Reset_Cred"+".txt"), true);
			PrintWriter pw = new PrintWriter(file);
			pw.printf(u.toStringReset()+"\n\n");
			
			pw.close();
			file.close();
	}
}
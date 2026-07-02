package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.DBConnector;
import model.Utilizador;

public class FicheiroTxt {

   
	public static void guardarTxt(Utilizador u) throws IOException {

	    FileWriter fw = new FileWriter("Credenciais.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);

	    bw.write(u.toString());
	    bw.newLine();

	    bw.close();
	    fw.close();
	}
	public static void guardarResetTxt(Utilizador u) throws IOException {

	    FileWriter fw = new FileWriter("Credenciais.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);

	    bw.write(u.toStringReset());
	    bw.newLine();

	    bw.close();
	    fw.close();
	}
//	public void guardarBD(Utilizador u) throws Exception {
//
//	    Connection conn = DBConnector.DBConnect();
//
//	    String sql = "INSERT INTO utilizadores "
//	            + "(nome,apelido,usuario,senha,contacto)"
//	            + " VALUES (?,?,?,?,?)";
//
//	    PreparedStatement ps = conn.prepareStatement(sql);
//
//	    ps.setString(1, u.getNome());
//	    ps.setString(2, u.getApelido());
//	    ps.setString(3, u.getUser_name());
//	    ps.setString(4, u.getSenha());
//	    ps.setString(5, u.getContacto());
//	    
//	    ps.executeUpdate();
//
//	    ps.close();
//	    conn.close();
//	}
}
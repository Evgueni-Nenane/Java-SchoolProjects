package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.NivelAcesso;
import model.Sessao;
import model.Utilizador;

public class LoginDAO {

	public static boolean login(String username, String senha) {
	    String sql = "SELECT * FROM Utilizador WHERE username = ? AND senha = ?";
	    try {
	        Connection conn = DBConnector.DBConnect();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, senha);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) {
	        	NivelAcesso perfil = NivelAcesso.valueOf(rs.getString("Perfil"));
	            Utilizador userSessao = new Utilizador(rs.getBytes("foto"), rs.getString("nome"), rs.getString("apelido"), rs.getString("username"), perfil, rs.getString("email"));
	            Sessao.iniciarSessao(userSessao);
	            return true;
	        }
	        return false;
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static boolean isPrimeiroAcesso(String username, String senha) {
		String sql = "SELECT primeiro_acesso FROM utilizador WHERE UserName = ? AND Senha = ?";
		try {
			Connection conn = DBConnector.DBConnect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
	        if(rs.next()) {
	            return rs.getBoolean("primeiro_acesso");
	        }
	        return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean atualizarSenha(String username, String senhaAntiga, String novaSenha) {

	    String sql = "UPDATE utilizador SET senha = ?, primeiro_acesso = 0 WHERE username = ? AND senha = ?";

	    try (Connection conn = DBConnector.DBConnect();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, novaSenha);
	        ps.setString(2, username);
	        ps.setString(3, senhaAntiga);
	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static boolean resetarSenha(int codigoUser, String senha) {

	    String sql = "UPDATE utilizador SET senha = ?, primeiro_acesso = 1 WHERE codigo_user = ?";

	    try (Connection conn = DBConnector.DBConnect();
	        PreparedStatement ps = conn.prepareStatement(sql)) {
	    		ps.setString(1, senha);
	        ps.setInt(2, codigoUser);
	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NivelAcesso;

public class NivelAcessoDAO {
	
	public boolean inserir(String nome) {
		String sql = "INSERT INTO NivelAcesso (Nome) VALUES (?)";
		
		try {
			Connection conn = DBConnector.DBConnect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<NivelAcesso> listarNiveis() {
		List<NivelAcesso> niveis = new ArrayList<>();
		String sql = "SELECT * FROM NivelAcesso";
		
		try (Connection conn = DBConnector.DBConnect();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				NivelAcesso perfil = new NivelAcesso(rs.getString("NomeNivel"));
				niveis.add(perfil);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return niveis;
	}
}

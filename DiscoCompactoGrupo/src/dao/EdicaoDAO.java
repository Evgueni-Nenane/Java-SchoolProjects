package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Edicao;

public class EdicaoDAO {
	   public boolean inserir(Edicao edicao) {
	        String sql = "INSERT INTO Edicao (Data_Edicao, Codigo_Disco, Codigo_Editora) VALUES (?, ?, ?)";
	        try {
	            Connection conn = DBConnector.DBConnect();
	            PreparedStatement ps = conn.prepareStatement(sql);
	            java.sql.Date sqlData = new java.sql.Date(edicao.getDataEdicao().getTime());
	            ps.setDate(1, sqlData);
	            ps.setInt(2, edicao.getCodigoDisco());
	            ps.setInt(3, edicao.getCodigoEditora());
	            ps.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Edicao;

public class EdicaoDAO {
	   public boolean inserir(Edicao edicao) {
	        String sql = "INSERT INTO Edicao (Data_Edicao, Codigo_DC, Codigo_Editora) VALUES (?, ?, ?)";
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
	   
	   public Edicao buscarPorCodigoDisco(int codigoDisco) {

	        String sql = "SELECT * FROM Edicao WHERE Codigo_DC = ?";

	        try {
	            Connection conn = DBConnector.DBConnect();
	            PreparedStatement ps = conn.prepareStatement(sql);

	            ps.setInt(1, codigoDisco);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {

	                return new Edicao(
	                    rs.getInt("Codigo_DC"),
	                    rs.getInt("Codigo_Editora"),
	                    rs.getDate("Data_Edicao")
	                );
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
	
}

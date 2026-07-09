package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Gravadora;

public class GravadoraDAO {

    public int inserir(Gravadora gravadora) {
        String sql = "INSERT INTO Gravadora (Nome_Gravadora, Email_Gravadora, Endereco_Gravadora, Contacto_Gravadora) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, gravadora.getNomeGravadora());
            ps.setString(2, gravadora.getEmailGravadora());
            ps.setString(3, gravadora.getEnderecoGravadora());
            ps.setString(4, gravadora.getContactoGravadora());
            ps.executeUpdate();
            int codigoGravadora = -1;
            ResultSet generatedKeys = ps.getGeneratedKeys(); 
            if (generatedKeys.next()) {
            	codigoGravadora = generatedKeys.getInt(1);
            }
            return codigoGravadora;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Gravadora> listarTodos() {
        List<Gravadora> gravadoras = new ArrayList<>();
        String sql = "SELECT * FROM Gravadora";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Gravadora gravadora = new Gravadora(
                    rs.getInt("Codigo_Gravadora"),
                    rs.getString("Nome_Gravadora"),
                    rs.getString("Email_Gravadora"),
                    rs.getString("Endereco_Gravadora"),
                    rs.getString("Contacto_Gravadora")
                );
                gravadoras.add(gravadora);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return gravadoras;
    }

    public List<Gravadora> listarPorCodigo(int codigODisco) {
        List<Gravadora> gravadoras = new ArrayList<>();
        String sql = "SELECT g.* FROM Gravadora g INNER JOIN GravadoraDisco gd "
        		+ "ON g.Codigo_Gravadora = gd.Codigo_Gravadora WHERE gd.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigODisco);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Gravadora gravadora = new Gravadora(
                    rs.getInt("Codigo_Gravadora"),
                    rs.getString("Nome_Gravadora"),
                    rs.getString("Contacto_Gravadora"),
                    rs.getString("Endereco_Gravadora"),
                    rs.getString("Email_Gravadora")
                );
                gravadoras.add(gravadora);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return gravadoras;
    }
    
    public boolean inserRelacaoDiscoGravadora(int codigoDisco, int codigoGravadora) {
    	String sql = "INSERT INTO GravadoraDisco (Codigo_DC, Codigo_Gravadora) VALUES (?,?)";
    	try {
    		Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, codigoDisco);
    		ps.setInt(2, codigoGravadora);
    		ps.executeUpdate();
    		
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }

}



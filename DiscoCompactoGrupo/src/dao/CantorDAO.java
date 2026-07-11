package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cantor;

public class CantorDAO {

    public int inserir(Cantor Cantor) {
        String sql = "INSERT INTO Cantor (Nome_Cantor, Apelido_Cantor, Contacto_Cantor, Email_Cantor) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Cantor.getNomeCantor());
            ps.setString(2, Cantor.getApelidoCantor());
            ps.setString(3, Cantor.getContactoCantor());
            ps.setString(4, Cantor.getEmailCantor());
            ps.executeUpdate();
            int codigoCantor = -1;
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
            	codigoCantor = generatedKeys.getInt(1);
            }
            return codigoCantor;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<Cantor> listarTodos() {
        List<Cantor> cantores = new ArrayList<>();
        String sql = "SELECT * FROM Cantor";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Cantor cantor = new Cantor(
                    rs.getInt("Codigo_Cantor"),
                    rs.getString("Nome_Cantor"),
                    rs.getString("Apelido_Cantor"),
                    rs.getString("Contacto_Cantor"),
                    rs.getString("Email_Cantor")
                );
                cantores.add(cantor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cantores;
    }
    
    public List<Cantor> listarPorCodigo(int codigoDisco) {
        List<Cantor> cantores = new ArrayList<>();
        String sql = "SELECT ca.* FROM Cantor ca INNER JOIN Cantor_DC cad "
        		+ "ON ca.Codigo_Cantor = cad.Codigo_Cantor WHERE cad.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Cantor cantor = new Cantor(
                    rs.getInt("Codigo_Cantor"),
                    rs.getString("Nome_Cantor"),
                    rs.getString("Apelido_Cantor"),
                    rs.getString("Contacto_Cantor"),
                    rs.getString("Email_Cantor")
                );
                cantores.add(cantor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cantores;
    }
    
    public Cantor buscarPorCodigo(int codigoCantor) {
    		Cantor cantor= new Cantor();
    		String sql = "SELECT * FROM Cantor "
    				+ "WHERE Codigo_Cantor = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoCantor);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	cantor = new Cantor(
            			rs.getInt("Codigo_Cantor"),
            			rs.getString("Nome_Cantor"),
            			rs.getString("Apelido_Cantor"),
            			rs.getString("Contacto_Cantor"),
            			rs.getString("Email_Cantor")
                    );

                return cantor;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
		return cantor;
    }
    
    public boolean atualizar(Cantor cantor) {
    	String sql = "UPDATE Cantor SET email_cantor = ?, contacto_cantor = ? "
    			+ "WHERE Codigo_Cantor = ? ";
    	try (Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql)) {
    		ps.setString(1, cantor.getEmailCantor());
    		ps.setString(2, cantor.getContactoCantor());
    		ps.setInt(3, cantor.getCodigoCantor());
    		ps.executeUpdate();
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean temRelacionamento(int codigo) {
    	String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM Cantor_DC "
    			+ " WHERE Codigo_Cantor = ?";
    	try {
    		Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, codigo);
    		ResultSet rs = ps.executeQuery();
    		if (rs.next()) {
    			int quantidade = rs.getInt("Quantidade");
    			
    			if (quantidade > 0) {
    				return true;
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public boolean remover(int codigo) {
    	if (temRelacionamento(codigo)) {
    		return false;
    	}
    	
    	String sql = "DELETE FROM Cantor WHERE codigo_cantor = ?";
    	try {
    		Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, codigo);
    		
    		int linhasAfectadas = ps.executeUpdate();
    		return linhasAfectadas > 0;
    	} catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean inserRelacaoDiscoCantor(int codigoDisco, int codigoCantor) {
    	String sql = "INSERT INTO Cantor_DC (Codigo_Cantor, Codigo_DC) VALUES (?,?)";
    	try {
    		Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, codigoCantor);
    		ps.setInt(2, codigoDisco);
    		ps.executeUpdate();
    		
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    
}

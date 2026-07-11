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
    
    public Gravadora buscarPorCodigo(int codigoGravadora) {
		Gravadora gravadora = new Gravadora();
		String sql = "SELECT * FROM Gravadora "
				+ "WHERE Codigo_Gravadora = ?";
    try {
        Connection conn = DBConnector.DBConnect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, codigoGravadora);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	gravadora = new Gravadora(
        			 	rs.getInt("Codigo_Gravadora"),
        			 	rs.getString("Nome_Gravadora"),
        			 	rs.getString("Contacto_Gravadora"),
        			 	rs.getString("Endereco_Gravadora"),
        			 	rs.getString("Email_Gravadora")
        			);

            return gravadora;
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
		return gravadora;
    }
    
    public boolean atualizar(Gravadora gravadora) {
    	String sql = "UPDATE Gravadora SET endereco_gravadora = ?, email_gravadora = ?, contacto_gravadora = ? "
    			+ "WHERE Codigo_gravadora = ? ";
    	try (Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql)) {
    		ps.setString(1, gravadora.getEnderecoGravadora());
    		ps.setString(2, gravadora.getEmailGravadora());
    		ps.setString(3, gravadora.getContactoGravadora());
    		ps.setInt(4, gravadora.getCodigoGravadora());
    		ps.executeUpdate();
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean temRelacionamento(int codigo) {
    	String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM GravadoraDisco "
    			+ " WHERE Codigo_Gravadora = ?";
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
    	
    	String sql = "DELETE FROM Gravadora WHERE codigo_gravadora = ?";
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



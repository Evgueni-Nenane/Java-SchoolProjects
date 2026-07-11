package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Produtor;

public class ProdutorDAO {

    public int inserir(Produtor produtor) {
        String sql = "INSERT INTO Produtor (Nome_Prod, Apelido_Produtor, Contacto_Prod, Email_Prod) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, produtor.getNomeProdutor());
            ps.setString(2, produtor.getApelidoProdutor());
            ps.setString(3, produtor.getContactoProdutor());
            ps.setString(4, produtor.getEmailProdutor());
            ps.executeUpdate();
            int codigoProdutor = -1;
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
            	codigoProdutor = generatedKeys.getInt(1);
            }
            return codigoProdutor;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<Produtor> listarTodos() {
        List<Produtor> produtores = new ArrayList<>();
        String sql = "SELECT * FROM Produtor";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Produtor Produtor = new Produtor(
                    rs.getInt("Codigo_Prod"),
                    rs.getString("Nome_Prod"),
                    rs.getString("Apelido_Produtor"),
                    rs.getString("Contacto_Prod"),
                    rs.getString("Email_Prod")
                );
                produtores.add(Produtor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return produtores;
    }
    
    public List<Produtor> listarPorCodigo(int codigoDisco) {
        List<Produtor> produtores = new ArrayList<>();
        String sql = "SELECT p.* FROM Produtor p INNER JOIN ProDC pd ON p.Codigo_Prod = pd.Codigo_Produtor WHERE pd.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Produtor produtor = new Produtor(
                    rs.getInt("Codigo_Prod"),
                    rs.getString("Nome_Prod"),
                    rs.getString("Apelido_Produtor"),
                    rs.getString("Contacto_Prod"),
                    rs.getString("Email_Prod")
                );
                produtores.add(produtor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return produtores;
    }
    
    public Produtor buscarPorCodigo(int codigoProdutor) {
		Produtor produtor = new Produtor();
		String sql = "SELECT * FROM Produtor "
				+ "WHERE Codigo_Prod = ?";
    try {
        Connection conn = DBConnector.DBConnect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, codigoProdutor);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	produtor = new Produtor(
        				rs.getInt("Codigo_Prod"),
        				rs.getString("Nome_Prod"),
        				rs.getString("Apelido_Produtor"),
        				rs.getString("Contacto_Prod"),
        				rs.getString("Email_Prod")
        			);

            return produtor;
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
		return produtor;
    }
    
    public boolean atualizar(Produtor produtor) {
    	String sql = "UPDATE Produtor SET email_prod = ?, contacto_prod = ? "
    			+ "WHERE Codigo_prod = ? ";
    	try (Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql)) {
    		ps.setString(1, produtor.getEmailProdutor());
    		ps.setString(2, produtor.getContactoProdutor());
    		ps.setInt(3, produtor.getCodigoProdutor());
    		ps.executeUpdate();
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean temRelacionamento(int codigo) {
    	String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM ProDC "
    			+ " WHERE Codigo_Produtor = ?";
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
    	
    	String sql = "DELETE FROM Produtor WHERE codigo_prod = ?";
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
    
    
    public boolean inserRelacaoDiscoProdutor(int codigoDisco, int codigoProdutor) {
    	String sql = "INSERT INTO ProDC (Codigo_DC, Codigo_Produtor) VALUES (?,?)";
    	try {
    		Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, codigoDisco);
    		ps.setInt(2, codigoProdutor);
    		ps.executeUpdate();
    		
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Compositor;

public class CompositorDAO {

    public int inserir(Compositor compositor) {
        String sql = "INSERT INTO Compositor (Nome_Compositor, Apelido_Compositor, Contacto_Compositor, Email_Compositor) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, compositor.getNomeCompositor());
            ps.setString(2, compositor.getApelidoCompositor());
            ps.setString(3, compositor.getContactoCompositor());
            ps.setString(4, compositor.getEmailCompositor());
            ps.executeUpdate();
            int codigoCompositor = -1;
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
            	codigoCompositor = generatedKeys.getInt(1);
            }
            return codigoCompositor;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<Compositor> listarTodos() {
        List<Compositor> compositores = new ArrayList<>();
        String sql = "SELECT * FROM Compositor";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Compositor compositor = new Compositor(
                    rs.getInt("Codigo_Compositor"),
                    rs.getString("Nome_Compositor"),
                    rs.getString("Apelido_Compositor"),
                    rs.getString("Contacto_Compositor"),
                    rs.getString("Email_Compositor")
                );
                compositores.add(compositor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return compositores;
    }
    
    public List<Compositor> listarPorCodigo(int codigoDisco) {
        List<Compositor> compositores = new ArrayList<>();
        String sql = "SELECT c.* FROM Compositor c INNER JOIN Compositor_DC cd"
        		+ " ON c.Codigo_Compositor = cd.Codigo_Compositor WHERE cd.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Compositor compositor = new Compositor(
                    rs.getInt("Codigo_Compositor"),
                    rs.getString("Nome_Compositor"),
                    rs.getString("Apelido_Compositor"),
                    rs.getString("Contacto_Compositor"),
                    rs.getString("Email_Compositor")
                );
                compositores.add(compositor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return compositores;
    }
    
    public Compositor buscarPorCodigo(int codigoCompositor) {
        Compositor compositor = new Compositor();
    		String sql = "SELECT * FROM Compositor "
    				+ "WHERE Codigo_Compositor = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoCompositor);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                compositor = new Compositor(
                    rs.getInt("Codigo_Compositor"),
                    rs.getString("Nome_Compositor"),
                    rs.getString("Apelido_Compositor"),
                    rs.getString("Contacto_Compositor"),
                    rs.getString("Email_Compositor")
                );
                return compositor;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
		return compositor;
    }
    
    public boolean atualizar(Compositor compositor) {
    	String sql = "UPDATE Compositor SET email_compositor = ?, contacto_compositor = ? "
    			+ "WHERE Codigo_compositor = ? ";
    	try (Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql)) {
    		ps.setString(1, compositor.getEmailCompositor());
    		ps.setString(2, compositor.getContactoCompositor());
    		ps.setInt(3, compositor.getCodigoCompositor());
    		ps.executeUpdate();
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean temRelacionamento(int codigo) {
    		String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM Compositor_DC "
    			+ " WHERE Codigo_Compositor = ?";
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
	    	
	    	String sql = "DELETE FROM Compositor WHERE codigo_compositor = ?";
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
    
    public boolean inserRelacaoDiscoCompositor(int codigoDisco, int codigoCompositor) {
	    	String sql = "INSERT INTO Compositor_DC (Codigo_Compositor, Codigo_DC) VALUES (?,?)";
	    	try {
	    		Connection conn = DBConnector.DBConnect();
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setInt(1, codigoCompositor);
	    		ps.setInt(2, codigoDisco);
	    		ps.executeUpdate();
	    		
	    		return true;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		return false;
	    	}
    }
}

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Instrumento;
import model.Musico;

public class MusicoDAO {

    public int inserir(Musico musico) {
        String sql = "INSERT INTO Musico (Nome_Musico, Apelido_Musico, Contacto_Musico ,Email_Musico) VALUES ( ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, musico.getNomeMusico());
            ps.setString(2, musico.getApelidoMusico());
            ps.setString(3, musico.getContactoMusico());
            ps.setString(4, musico.getEmailMusico());
            ps.executeUpdate();
            int codigoMusico = -1;
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
            	codigoMusico = generatedKeys.getInt(1);
            }
            return codigoMusico;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public List<Musico> listarTodos() {
        List<Musico> musicos = new ArrayList<>();
        String sql = "SELECT * FROM Musico";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Musico musico = new Musico(
                    rs.getInt("Codigo_Musico"),
                    rs.getString("Nome_Musico"),
                    rs.getString("Apelido_Musico"),
                    buscarInstrumentosDoMusico(rs.getInt("Codigo_Musico")),
                    rs.getString("Contacto_Musico"),
                    rs.getString("Email_Musico")
                );
                musicos.add(musico);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return musicos;
    }
    public List<Musico> listarPorCodigo(int codigoDisco) {
        List<Musico> musicos = new ArrayList<>();
        String sql = "SELECT m.* FROM Musico m INNER JOIN Musico_DC md"
        		+ " ON m.Codigo_Musico = md.Codigo_Musico WHERE md.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Musico musico = new Musico(
                    rs.getInt("Codigo_Musico"),
                    rs.getString("Nome_Musico"),
                    rs.getString("Apelido_Musico"),
                    buscarInstrumentosDoMusico(rs.getInt("Codigo_Musico")),
                    rs.getString("Contacto_Musico"),
                    rs.getString("Email_Musico")
                );
                musicos.add(musico);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return musicos;
    }
    
    public Musico buscarPorCodigo(int codigoMusico) {
		Musico musico = new Musico();
		String sql = "SELECT * FROM Musico "
				+ "WHERE Codigo_Musico = ?";
    try {
        Connection conn = DBConnector.DBConnect();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, codigoMusico);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	musico = new Musico(
        				rs.getInt("Codigo_Musico"),
                     rs.getString("Nome_Musico"),
                     rs.getString("Apelido_Musico"),
                     buscarInstrumentosDoMusico(rs.getInt("Codigo_Musico")),
                     rs.getString("Contacto_Musico"),
                     rs.getString("Email_Musico")
        			);

            return musico;
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
		return musico;
    }

    public List<Instrumento> buscarInstrumentosDoMusico(int codigoMusico) {
        List<Instrumento> instrumentos = new ArrayList<>();

        String sql = "SELECT i.Codigo, i.NomeInstrumento " +
                     "FROM Instrumento i " +
                     "INNER JOIN Musico_Instrumento mi " +
                     "ON i.Codigo = mi.Codigo_Instr " +
                     "WHERE mi.Codigo_Musico = ?";

        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, codigoMusico);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Instrumento instrumento = new Instrumento(
                    rs.getInt("Codigo"),
                    rs.getString("NomeInstrumento")
                );

                instrumentos.add(instrumento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instrumentos;
    }
    
    public boolean atualizar(Musico musico) {
    	String sql = "UPDATE Musico SET Instrumento = ?, email_musico = ?, contacto_musico = ? "
    			+ "WHERE Codigo_Musico = ? ";
    	try (Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql)) {
    		ps.setString(2, musico.getEmailMusico());
    		ps.setString(3, musico.getContactoMusico());
    		ps.setInt(4, musico.getCodigoMusico());
    		ps.executeUpdate();
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean temRelacionamento(int codigo) {
    	String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM Musico_DC "
    			+ " WHERE Codigo_Musico = ?";
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
	    	 String sql1 = "DELETE FROM Musico_Instrumento WHERE Codigo_Musico = ?";
	    	    String sql2 = "DELETE FROM Musico WHERE Codigo_Musico = ?";
	
	    	    try {
	    	        Connection conn = DBConnector.DBConnect();
	
	    	        PreparedStatement ps1 = conn.prepareStatement(sql1);
	    	        ps1.setInt(1, codigo);
	    	        ps1.executeUpdate();
	
	    	        PreparedStatement ps2 = conn.prepareStatement(sql2);
	    	        ps2.setInt(1, codigo);
	    	        int linhasAfetadas = ps2.executeUpdate(); 
	
	    	        return linhasAfetadas > 0;
	    	} catch(SQLException e) {
	    		e.printStackTrace();
	    		return false;
	    	}
    }
    
    
    public boolean inserRelacaoDiscoMusico(int codigoDisco, int codigoMusico) {
    	String sql = "INSERT INTO Musico_DC (Codigo_Musico, Codigo_DC) VALUES (?,?)";
    	try {
    		Connection conn = DBConnector.DBConnect();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, codigoMusico);
    		ps.setInt(2, codigoDisco);
    		ps.executeUpdate();
    		
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}

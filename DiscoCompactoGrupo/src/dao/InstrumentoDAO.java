package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Instrumento;

public class InstrumentoDAO {
	public boolean inserir(Instrumento instrumento) {
		String sql = "INSERT INTO Instrumento (codigo, nomeInstrumento) VALUES (?,?)";
		
		try {
			Connection conn = DBConnector.DBConnect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, instrumento.getCodigo());
			ps.setString(2, instrumento.getNome());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Instrumento> listarTodos() {
        List<Instrumento> instrumentos = new ArrayList<>();
        String sql = "SELECT * FROM Instrumento";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Instrumento instrumento = new Instrumento(
                    rs.getInt("Codigo"),
                    rs.getString("NomeInstrumento")
                );
                instrumentos.add(instrumento);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return instrumentos;
    }
    
    public Instrumento listarPorCodigo(int codigoInstrumento) {
        Instrumento instrumento = new Instrumento();
        String sql = "SELECT i.* FROM Instrumento i INNER JOIN Musico_Instrumento mi"
        		+ " ON i.Codigo = mi.Codigo_Instr WHERE mi.Codigo_Musico = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoInstrumento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                instrumento = new Instrumento(
                    rs.getInt("Codigo"),
                    rs.getString("Nome")
                );
                return instrumento;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return instrumento;
    }
	
	
	public boolean temRelacionamento(int codigo) {
    		String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM Musico_Instrumento "
    			+ " WHERE Codigo_Instr = ?";
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
	    	
	    	String sql = "DELETE FROM Instrumento WHERE Codigo = ?";
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
    
    public boolean inserRelacaoMusicoInstrumento(int codigoMusico, int codigoInstrumento) {
	    	String sql = "INSERT INTO Musico_Instrumento (Codigo_Instr, Codigo_Musico) VALUES (?,?)";
	    	try {
	    		Connection conn = DBConnector.DBConnect();
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setInt(1, codigoInstrumento);
	    		ps.setInt(2, codigoMusico);
	    		ps.executeUpdate();
	    		
	    		return true;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		return false;
	    	}
    }
}

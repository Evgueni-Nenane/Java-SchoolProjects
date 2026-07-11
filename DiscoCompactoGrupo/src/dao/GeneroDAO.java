package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Genero;

public class GeneroDAO {
	public boolean inserir(Genero genero) {
		String sql = "INSERT INTO Genero (codigo_genero, nome_genero) VALUES (?,?)";

		try {
			Connection conn = DBConnector.DBConnect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, genero.getCodigoGenero());
			ps.setString(2, genero.getNomeGenero());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Genero> listarTodos() {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM Genero";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Genero genero= new Genero(
                    rs.getInt("Codigo_Genero"),
                    rs.getString("Nome_Genero")
                );
                generos.add(genero);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return generos;
    }

    public Genero listarPorCodigo(int codigoGenero) {
        Genero genero = new Genero();
        String sql = "SELECT * FROM Genero WHERE Codigo_Genero = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoGenero);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                genero = new Genero(
                    rs.getInt("Codigo_Genero"),
                    rs.getString("Nome_Genero")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return genero;
    }

    public List<Genero> listarPorDisco(int codigoDisco) {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT g.Codigo_Genero, g.Nome_Genero"
                + " FROM Genero g INNER JOIN Disco_Genero dg"
                + " ON g.Codigo_Genero = dg.Codigo_Genero"
                + " WHERE dg.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Genero genero = new Genero(
                    rs.getInt("Codigo_Genero"),
                    rs.getString("Nome_Genero")
                );
                generos.add(genero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generos;
    }

	public boolean temRelacionamento(int codigo) {
    		String sql = "SELECT COUNT(*) AS Quantidade"
    			+ " FROM Disco_Genero "
    			+ " WHERE Codigo_Genero = ?";
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

	    	String sql = "DELETE FROM Genero WHERE Codigo_Genero = ?";
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

    public boolean inserRelacaoGeneroDisco(int codigoDisco, int codigoGenero) {
	    	String sql = "INSERT INTO Disco_Genero (Codigo_DC, Codigo_Genero) VALUES (?,?)";
	    	try {
	    		Connection conn = DBConnector.DBConnect();
	    		PreparedStatement ps = conn.prepareStatement(sql);
	    		ps.setInt(1, codigoDisco);
	    		ps.setInt(2, codigoGenero);
	    		ps.executeUpdate();

	    		return true;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		return false;
	    	}
    }

    public boolean removerRelacoesPorDisco(int codigoDisco) {
        String sql = "DELETE FROM Disco_Genero WHERE Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
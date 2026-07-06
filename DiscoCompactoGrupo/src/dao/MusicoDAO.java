package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Musico;

public class MusicoDAO {

    public int inserir(Musico musico) {
        String sql = "INSERT INTO Musico (Nome_Musico, Apelido_Musico, Instrumento, Email_Musico) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, musico.getNomeMusico());
            ps.setString(2, musico.getApelidoMusico());
            ps.setString(3, musico.getInstrumento());
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
                    rs.getString("Instrumento"),
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
                    rs.getString("Instrumento"),
                    rs.getString("Email_Musico")
                );
                musicos.add(musico);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return musicos;
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

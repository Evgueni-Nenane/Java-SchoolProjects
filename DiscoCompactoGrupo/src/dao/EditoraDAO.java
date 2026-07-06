package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Editora;

public class EditoraDAO {

    public boolean inserir(Editora editora) {
        String sql = "INSERT INTO Editora (Nome_Editora, Email_Editora, Endereco) VALUES (?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, editora.getNomeEditora());
            ps.setString(2, editora.getEmailEditora());
            ps.setString(3, editora.getEndereco());
            ps.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Editora> listarTodos() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM Editora";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Editora editora = new Editora(
                    rs.getInt("Codigo_Editora"),
                    rs.getString("Nome_Editora"),
                    rs.getString("Email_Editora"),
                    rs.getString("Endereco")
                );
                editoras.add(editora);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return editoras;
    }
    
    public List<Editora> listarPorCodigo(int codigoDisco) {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT e.* FROM Editora e INNER JOIN Edicao ed"
        		+ " ON e.Codigo_Editora = ed.Codigo_Editora WHERE ed.Codigo_DC = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Editora editora = new Editora(
                    rs.getInt("Codigo_Editora"),
                    rs.getString("Nome_Editora"),
                    rs.getString("Email_Editora"),
                    rs.getString("Endereco")
                );
                editoras.add(editora);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return editoras;
    }
}
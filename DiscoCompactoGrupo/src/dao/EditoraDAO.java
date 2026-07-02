package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Editora;

public class EditoraDAO {

    public boolean inserir(Editora editora) {
        String sql = "INSERT INTO Editora (Codigo_Editora, Nome_Editora, Email_Editora, Endereco) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, editora.getCodigoEditora());
            ps.setString(2, editora.getNomeEditora());
            ps.setString(3, editora.getEmailEditora());
            ps.setString(4, editora.getEndereco());
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
}
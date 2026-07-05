package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Musico;

public class MusicoDAO {

    public boolean inserir(Musico Musico) {
        String sql = "INSERT INTO Musico (Codigo_Musico, Nome_Musico, Apelido_Musico, Contacto_Musico, Email_Musicor) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Musico.getCodigoMusico());
            ps.setString(2, Musico.getNomeMusico());
            ps.setString(3, Musico.getApelidoMusico());
            ps.setString(4, Musico.getContactoMusico());
            ps.setString(5, Musico.getEmailMusico());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
}

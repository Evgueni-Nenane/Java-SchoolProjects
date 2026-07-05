package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cantor;

public class CantorDAO {

    public boolean inserir(Cantor Cantor) {
        String sql = "INSERT INTO Cantor (Codigo_Cantor, Nome_Cantor, Apelido_Cantor, Contacto_Cantor, Email_Cantor) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Cantor.getCodigoCantor());
            ps.setString(2, Cantor.getNomeCantor());
            ps.setString(3, Cantor.getApelidoCantor());
            ps.setString(4, Cantor.getContactoCantor());
            ps.setString(5, Cantor.getEmailCantor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Cantor> listarTodos() {
        List<Cantor> cantores = new ArrayList<>();
        String sql = "SELECT * FROM Cantor";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Cantor cantor = new Cantor(
                    rs.getInt("Codigo_Cantor"),
                    rs.getString("Nome_Cantor"),
                    rs.getString("Apelido_Cantor"),
                    rs.getString("Contacto_Cantor"),
                    rs.getString("Email_Cantor")
                );
                cantores.add(cantor);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cantores;
    }
    
    
}

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Gravadora;

public class GravadoraDAO {

    public boolean inserir(Gravadora gravadora) {
        String sql = "INSERT INTO Gravadora (Codigo_Gravadora, Nome_Gravadora, Email_Gravadora, Endereco_Gravadora, Contacto_Gravadora) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, gravadora.getCodigoGravadora());
            ps.setString(2, gravadora.getNomeGravadora());
            ps.setString(3, gravadora.getEmailGravadora());
            ps.setString(4, gravadora.getEnderecoGravadora());
            ps.setString(5, gravadora.getContactoGravadora());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Gravadora> listarTodos() {
        List<Gravadora> gravadoras = new ArrayList<>();
        String sql = "SELECT * FROM Gravadora";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Gravadora gravadora = new Gravadora(
                    rs.getInt("Codigo_Gravadora"),
                    rs.getString("Nome_Gravadora"),
                    rs.getString("Email_Gravadora"),
                    rs.getString("Endereco_Gravadora"),
                    rs.getString("Contacto_Gravadora")
                );
                gravadoras.add(gravadora);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return gravadoras;
    }
}


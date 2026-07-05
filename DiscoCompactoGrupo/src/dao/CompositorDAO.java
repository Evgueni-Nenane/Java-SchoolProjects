package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Compositor;

public class CompositorDAO {

    public boolean inserir(Compositor compositor) {
        String sql = "INSERT INTO Compositor (Codigo_Compositor, Nome_Compositor, Apelido_Compositor, Contacto_Compositor, Email_Compositor) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, compositor.getCodigoCompositor());
            ps.setString(2, compositor.getNomeCompositor());
            ps.setString(3, compositor.getApelidoCompositor());
            ps.setString(4, compositor.getContactoCompositor());
            ps.setString(5, compositor.getEmailCompositor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
}

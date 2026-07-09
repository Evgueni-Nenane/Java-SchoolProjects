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

	    String verificar = "SELECT COUNT(*) FROM Genero WHERE Nome_Genero = ?";
	    String inserir = "INSERT INTO Genero (Nome_Genero) VALUES (?)";

	    try {
	        Connection conn = DBConnector.DBConnect();
	        PreparedStatement psVerificar = conn.prepareStatement(verificar);
	        psVerificar.setString(1, genero.getNomeGenero());
	        ResultSet rs = psVerificar.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            return false;
	        }
	        PreparedStatement psInserir = conn.prepareStatement(inserir);
	        psInserir.setString(1, genero.getNomeGenero());
	        psInserir.executeUpdate();
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
                Genero genero = new Genero(
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
}
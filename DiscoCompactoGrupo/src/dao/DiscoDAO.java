package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DiscoCompacto;
import model.Generos;

public class DiscoDAO {

    // Cadastrar disco
    public boolean inserir(DiscoCompacto disco) {
        String sql = "INSERT INTO Disco_Compacto (Codigo_Disco, Titulo, Genero, Preco, Ano_Edicao) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, disco.getCodigoDisco());
            ps.setString(2, disco.getTitulo());
            ps.setString(3, disco.getGeneroMusical().toString());
            ps.setDouble(4, disco.getPreco());
            ps.setInt(5, disco.getAnoEdicao());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar discos
    public List<DiscoCompacto> listarTodos() {
        List<DiscoCompacto> discos = new ArrayList<>();
        String sql = "SELECT * FROM Disco_Compacto";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
		    String titulo;
		    if(rs.getString("Titulo") != null) {
		        titulo = rs.getString("Titulo");
		    } else {
		        titulo = "";
		    }
		    
		    String genero;
		    if(rs.getString("Genero") != null) {
		        genero = rs.getString("Genero");
		    } else {
		        genero = "";
		    }
		
		    DiscoCompacto disco = new DiscoCompacto(
	        rs.getInt("Codigo_Disco"),
	        titulo,
	        Generos.valueOf(genero),
	        rs.getDouble("Preco"),
	        rs.getInt("Ano_Edicao"),
	        null, null, null, null
    );
		    discos.add(disco);
}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discos;
    }

    // Remover disco
    public boolean remover(int codigoDisco) {
        String sql = "DELETE FROM Disco_Compacto WHERE Codigo_Disco = ?";
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
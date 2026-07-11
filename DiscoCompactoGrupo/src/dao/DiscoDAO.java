package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DiscoCompacto;
import model.Edicao;

public class DiscoDAO {

    public int inserir(DiscoCompacto disco) {
        String sql = "INSERT INTO Disco_Compacto (Titulo, Preco, Ano_Edicao) VALUES (?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, disco.getTitulo());
            ps.setDouble(2, disco.getPreco());
            ps.setInt(3, disco.getAnoEdicao());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            int codigoDisco = -1;
            if (generatedKeys.next()) {
                codigoDisco = generatedKeys.getInt(1);
            }
            return codigoDisco;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<DiscoCompacto> listarTodos() {
        List<DiscoCompacto> discos = new ArrayList<>();
        String sql = "SELECT d.Codigo_Disco, d.Titulo, d.Preco, d.Ano_Edicao"
        		+ " FROM Disco_Compacto d";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            	DiscoCompacto disco = new DiscoCompacto(
            			rs.getInt("Codigo_Disco"),
            		    rs.getString("Titulo"),
            		    new ArrayList<>(),
            		    rs.getDouble("Preco"),
            		    rs.getInt("Ano_Edicao"),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>()
            	);
                discos.add(disco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discos;
    }

    public DiscoCompacto buscarPorCodigo(int codigoDisco) {
    	String sql = "SELECT d.Codigo_Disco, d.Titulo, d.Preco, d.Ano_Edicao"
    			+ " FROM Disco_Compacto d"
    			+ " WHERE d.Codigo_Disco = ?";
    	try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            		return new DiscoCompacto(
            		    rs.getInt("Codigo_Disco"),
            		    rs.getString("Titulo"),
            		    new ArrayList<>(),
            		    rs.getDouble("Preco"),
            		    rs.getInt("Ano_Edicao"),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>()
            		);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DiscoCompacto buscarPorCodigoComEdicao(int codigoDisco) {

    	String sql = "SELECT d.Codigo_Disco, d.Titulo, d.Preco, d.Ano_Edicao, e.Codigo_Editora, e.Data_Edicao"
    			+ " FROM Disco_Compacto d LEFT JOIN Edicao e ON d.Codigo_Disco = e.Codigo_DC"
    			+ " WHERE d.Codigo_Disco = ?";

        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

            		DiscoCompacto disco = new DiscoCompacto(
            		    rs.getInt("Codigo_Disco"),
            		    rs.getString("Titulo"),
            		    new ArrayList<>(),
            		    rs.getDouble("Preco"),
            		    rs.getInt("Ano_Edicao"),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>(),
            		    new ArrayList<>()
            		);

            		Edicao edicao = new Edicao(
            		    rs.getInt("Codigo_Disco"),
            		    rs.getInt("Codigo_Editora"),
            		    rs.getDate("Data_Edicao")
            		);
            		disco.setEdicao(edicao);
            		return disco;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean atualizar(DiscoCompacto disco) {
        String sql = "UPDATE Disco_Compacto SET Titulo = ?, Preco = ?, Ano_Edicao = ? WHERE Codigo_Disco = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, disco.getTitulo());
            ps.setDouble(2, disco.getPreco());
            ps.setInt(3, disco.getAnoEdicao());
            ps.setInt(4, disco.getCodigoDisco());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(int codigoDisco) {
        try {
            Connection conn = DBConnector.DBConnect();

            String[] tabelasPonte = { "compositor_DC", "musico_DC", "cantor_DC", "edicao", "gravadoradisco", "prodc", "disco_genero" };
            for (String tabela : tabelasPonte) {
                PreparedStatement psRelacao = conn.prepareStatement(
                    "DELETE FROM " + tabela + " WHERE Codigo_DC = ?");
                psRelacao.setInt(1, codigoDisco);
                psRelacao.executeUpdate();
            }

            PreparedStatement ps = conn.prepareStatement("DELETE FROM Disco_Compacto WHERE Codigo_Disco = ?");
            ps.setInt(1, codigoDisco);
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

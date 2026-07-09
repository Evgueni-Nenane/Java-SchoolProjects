package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DiscoCompacto;
import model.Edicao;
import model.Genero;

public class DiscoDAO {

    // Cadastrar disco
    public int inserir(DiscoCompacto disco) {
        String sql = "INSERT INTO Disco_Compacto (Titulo, Codigo_Genero, Preco, Ano_Edicao) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, disco.getTitulo());
            ps.setInt(2, disco.getGeneroMusical().getCodigoGenero());
            ps.setDouble(3, disco.getPreco());
            ps.setInt(4, disco.getAnoEdicao());
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

    // Listar discos
    public List<DiscoCompacto> listarTodos() {
        List<DiscoCompacto> discos = new ArrayList<>();
        String sql = "SELECT d.Codigo_Disco, d.Titulo, d.Preco, d.Ano_Edicao, g.Codigo_Genero, g.Nome_Genero"
        		+ " FROM Disco_Compacto d INNER JOIN Genero g "
        		+ "ON d.Codigo_Genero = g.Codigo_Genero";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Genero genero = new Genero(
            		    rs.getInt("Codigo_Genero"),
            		    rs.getString("Nome_Genero")
            		);

            	DiscoCompacto disco = new DiscoCompacto(
            			rs.getInt("Codigo_Disco"),
            		    rs.getString("Titulo"),
            		    genero,
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
    	String sql = "SELECT d.Codigo_Disco, d.Titulo, d.Preco, d.Ano_Edicao, g.Codigo_Genero, g.Nome_Genero"
    			+ " FROM Disco_Compacto d INNER JOIN Genero g "
    			+ " ON d.Codigo_Genero = g.Codigo_Genero"
    			+ " WHERE codigo_Disco = ?";
    	try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	Genero genero = new Genero(
            		    rs.getInt("Codigo_Genero"),
            		    rs.getString("Nome_Genero")
            		);
            		return new DiscoCompacto(
            		    rs.getInt("Codigo_Disco"),
            		    rs.getString("Titulo"),
            		    genero,
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

    	String sql = "SELECT d.Codigo_Disco, d.Titulo, d.Preco, d.Ano_Edicao, g.Codigo_Genero, g.Nome_Genero, e.Codigo_Editora, e.Data_Edicao"
    			+ " FROM Disco_Compacto d INNER JOIN Genero g ON d.Codigo_Genero = g.Codigo_Genero LEFT JOIN"
    			+ " Edicao e ON d.Codigo_Disco = e.Codigo_DC WHERE d.Codigo_Disco = ?";

        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, codigoDisco);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
            	Genero genero = new Genero(
            		    rs.getInt("Codigo_Genero"),
            		    rs.getString("Nome_Genero")
            		);
            		DiscoCompacto disco = new DiscoCompacto(
            		    rs.getInt("Codigo_Disco"),
            		    rs.getString("Titulo"),
            		    genero,
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

            		return disco;            }


        } catch(SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
    public boolean atualizar(DiscoCompacto disco) {
        String sql = "UPDATE Disco_Compacto SET Titulo = ?, Genero = ?, Preco = ?, Ano_Edicao = ? WHERE Codigo_Disco = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, disco.getTitulo());
            ps.setString(2, disco.getGeneroMusical().toString());
            ps.setDouble(3, disco.getPreco());
            ps.setInt(4, disco.getAnoEdicao());
            ps.setInt(5, disco.getCodigoDisco());

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

            String[] tabelasPonte = { "compositor_DC", "musico_DC", "cantor_DC", "edicao", "gravadoradisco", "prodc" };
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
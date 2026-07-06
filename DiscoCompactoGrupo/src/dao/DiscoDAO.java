package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DiscoCompacto;
import model.Generos;

public class DiscoDAO {

    // Cadastrar disco
    public int inserir(DiscoCompacto disco) {
        String sql = "INSERT INTO Disco_Compacto (Titulo, Genero, Preco, Ano_Edicao) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, disco.getTitulo());
            ps.setString(2, disco.getGeneroMusical().toString());
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
        String sql = "SELECT * FROM Disco_Compacto";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String titulo = rs.getString("Titulo") != null ? rs.getString("Titulo") : "";
                String genero = rs.getString("Genero") != null ? rs.getString("Genero") : "";

                DiscoCompacto disco = new DiscoCompacto(
                    rs.getInt("Codigo_Disco"),
                    titulo,
                    Generos.valueOf(genero),
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
        String sql = "SELECT * FROM Disco_Compacto WHERE Codigo_Disco = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoDisco);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DiscoCompacto(
                    rs.getInt("Codigo_Disco"),
                    rs.getString("Titulo"),
                    Generos.valueOf(rs.getString("Genero")),
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

    // Atualizar disco (só os dados próprios; relações tratadas noutros DAOs)
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

    // Remover disco (apaga primeiro as relações nas tabelas ponte, depois o disco)
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
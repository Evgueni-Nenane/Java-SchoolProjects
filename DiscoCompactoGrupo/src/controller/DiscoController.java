package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import model.DiscoCompacto;

public class DiscoController {

    static final LocalDate HOJE = LocalDate.now();
    static final int ANOACTUAL = HOJE.getYear();

    public static void adicionarDiscos(String musico, String generoMusica, String gravadora,
            double preco, String produtor, String editora, int anoEdicao) {

        String sql = "INSERT INTO discocompacto(nome, genero, gravadora, preco, produtor, editora, anoedicao) VALUES (?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.DBConnect();

            if (conn == null) {
                throw new RuntimeException("Não foi possível conectar ao banco de dados.");
            }

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, musico);
            pstmt.setString(2, generoMusica);
            pstmt.setString(3, gravadora);
            pstmt.setDouble(4, preco);
            pstmt.setString(5, produtor);
            pstmt.setString(6, editora);
            pstmt.setInt(7, anoEdicao);

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar disco: " + e.getMessage(), e);
        } finally {
            fecharRecursos(pstmt, null);
        }
    }

    public static ArrayList<DiscoCompacto> listarDiscos() throws IOException {
        ArrayList<DiscoCompacto> discos = new ArrayList<>();
        String sql = "SELECT nome, genero, gravadora, preco, editora, produtor, anoedicao FROM discocompacto";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            conn = DBConnector.DBConnect();

            if (conn == null) {
                return discos;
            }

            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                String musico = rset.getString("nome");
                String generoMusical = rset.getString("genero");
                String gravadora = rset.getString("gravadora");
                double preco = rset.getDouble("preco");
                String editora = rset.getString("editora");
                String produtor = rset.getString("produtor");
                int anoEdicao = rset.getInt("anoedicao");

                discos.add(new DiscoCompacto(musico, generoMusical, gravadora, preco, editora, produtor, anoEdicao));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(pstmt, rset);
        }

        return discos;
    }

    public static void actualizarDiscos(int codigo, String musico, String generoMusica, String gravadora,
            double preco, String produtor, String editora, int anoEdicao) {

        String sql = "UPDATE discocompacto SET nome = ?, genero = ?, gravadora = ?, preco = ?, " +
                     "editora = ?, produtor = ?, anoedicao = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.DBConnect();

            if (conn == null) {
                throw new RuntimeException("Não foi possível conectar ao banco de dados.");
            }

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, musico);
            pstmt.setString(2, generoMusica);
            pstmt.setString(3, gravadora);
            pstmt.setDouble(4, preco);
            pstmt.setString(5, editora);
            pstmt.setString(6, produtor);
            pstmt.setInt(7, anoEdicao);
            pstmt.setInt(8, codigo);

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar disco: " + e.getMessage(), e);
        } finally {
            fecharRecursos(pstmt, null);
        }
    }

    public static void removerDisco(int codigo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnector.DBConnect();

            if (conn == null) {
                throw new RuntimeException("Não foi possível conectar ao banco de dados.");
            }

            pstmt = conn.prepareStatement("DELETE FROM discocompacto WHERE id = ?");
            pstmt.setInt(1, codigo);

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover disco: " + e.getMessage(), e);
        } finally {
            fecharRecursos(pstmt, null);
        }
    }

    private static void fecharRecursos(PreparedStatement pstmt, ResultSet rset) {
        try {
            if (rset != null) rset.close();
            if (pstmt != null) pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
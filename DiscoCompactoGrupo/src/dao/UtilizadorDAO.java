package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NivelAcesso;
import model.Sexo;
import model.Utilizador;

public class UtilizadorDAO {
	
    public boolean inserir(Utilizador utilizador) {
        String sql = "INSERT INTO Utilizador (Nome, Apelido, UserName, Genero, Perfil, Email, Contacto, Senha, Primeiro_Acesso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, utilizador.getNome());
            ps.setString(2, utilizador.getApelido());
            ps.setString(3, utilizador.getUser_name());
            ps.setString(4, (utilizador.getGenero().name()));
            ps.setString(5, (utilizador.getPerfil().name()));
            ps.setString(6, utilizador.getEmail());
            ps.setString(7, utilizador.getContacto());
            ps.setString(8, utilizador.getSenha());
            ps.setBoolean(9, utilizador.isPrimeiroAcesso());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Utilizador> listarTodos() {
        List<Utilizador> utilizadores = new ArrayList<>();
        String sql = "SELECT * FROM Utilizador";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	
            	Sexo sexoEnum = Sexo.valueOf(rs.getString("Genero"));
            	NivelAcesso nivelEnum = NivelAcesso.valueOf(rs.getString("Perfil"));
            	
                Utilizador utilizador = new Utilizador(
	                rs.getInt("Codigo_User"),
	                rs.getString("Nome"),
	                rs.getString("Apelido"),
	                rs.getString("UserName"),
	                sexoEnum,
	                nivelEnum,
	                rs.getString("Email"),
	                rs.getString("Contacto")
                );
                utilizadores.add(utilizador);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return utilizadores;
    }
    
    // Remover disco
    public boolean remover(int codigoUser) {
        String sql = "DELETE FROM Utilizador WHERE Codigo_User = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoUser);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

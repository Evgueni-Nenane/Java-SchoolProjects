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
        String sql = "INSERT INTO Utilizador (Nome, Apelido, UserName, Genero, Codigo_Nivel,"
        		+ " Email, Contacto, Senha, Primeiro_Acesso, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, utilizador.getNome());
            ps.setString(2, utilizador.getApelido());
            ps.setString(3, utilizador.getUser_name());
            ps.setString(4, (utilizador.getGenero().name()));
            ps.setInt(5, (utilizador.getPerfil().getCodigoNivel()));
            ps.setString(6, utilizador.getEmail());
            ps.setString(7, utilizador.getContacto());
            ps.setString(8, utilizador.getSenha());
            ps.setBoolean(9, utilizador.isPrimeiroAcesso());
            ps.setBytes(10, utilizador.getFoto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizarUser(int codigoUser, Utilizador utilizador) {
        String sql = "UPDATE Utilizador SET foto = ?, codigo_nivel = ?, email = ?, contacto = ? WHERE codigo_user = ?";
        
        try (Connection conn = DBConnector.DBConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setBytes(1, utilizador.getFoto());
            ps.setInt(2, utilizador.getPerfil().getCodigoNivel());
            ps.setString(3, utilizador.getEmail());
            ps.setString(4, utilizador.getContacto());
            ps.setInt(5, codigoUser);
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean adicionarFoto(int codigoUser, Utilizador utilizador) {
        String sql = "UPDATE Utilizador SET foto = ? WHERE codigo_user = ?";
        
        try (Connection conn = DBConnector.DBConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setBytes(1, utilizador.getFoto());
            ps.setInt(2, codigoUser);
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public byte[] buscarFoto(int codigoUser) {
        String sql = "SELECT foto FROM Utilizador WHERE Codigo_User = ?";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("foto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Utilizador> listarTodos() {
        List<Utilizador> utilizadores = new ArrayList<>();
        String sql = "SELECT Codigo_User, Nome, Apelido, UserName, Genero, Email, Contacto, NomeNivel, Codigo_Nivel "
        		+ "FROM Utilizador "
        		+ "INNER JOIN NivelAcesso ON Utilizador.Codigo_Nivel = NivelAcesso.CodigoNivel";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	
            	Sexo sexoEnum = Sexo.valueOf(rs.getString("Genero"));
            	NivelAcesso perfil = new NivelAcesso(rs.getInt("Codigo_Nivel"), rs.getString("NomeNivel"));
                Utilizador utilizador = new Utilizador(
	                rs.getInt("Codigo_User"),
	                rs.getString("Nome"),
	                rs.getString("Apelido"),
	                rs.getString("UserName"),
	                sexoEnum,
	                perfil,
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
    
    public Utilizador buscarPorId(int codigoUser) {
        String sql = "SELECT Codigo_User, Nome, Apelido, UserName, Genero, Email, Contacto, Foto, NomeNivel, CodigoNivel"
        		+ " FROM Utilizador"
        		+ " INNER JOIN NivelAcesso ON Utilizador.Codigo_Nivel = NivelAcesso.CodigoNivel"
        		+ " WHERE codigo_user = ?";
        
        try (Connection conn = DBConnector.DBConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, codigoUser);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sexo sexoEnum = Sexo.valueOf(rs.getString("Genero"));
                    NivelAcesso perfil = new NivelAcesso(rs.getInt("CodigoNivel"), rs.getString("NomeNivel"));
                    
                    Utilizador utilizador = new Utilizador(
                        rs.getBytes("foto"),
                        perfil,
                        rs.getString("Email"),
                        rs.getString("Contacto")
                    );
                    
                    utilizador.setNome(rs.getString("Nome"));
                    utilizador.setApelido(rs.getString("Apelido"));
                    utilizador.setUser_name(rs.getString("UserName"));
                    utilizador.setGenero(sexoEnum);
                    utilizador.setCodigo(rs.getInt("Codigo_User")); 
                    utilizador.setFoto(rs.getBytes("Foto"));
                    return utilizador; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

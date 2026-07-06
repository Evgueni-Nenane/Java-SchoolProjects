package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Logs;

public class LogsDAO {
	public boolean inserir(Logs log) {
		String sql = "INSERT INTO Logs (nome, apelido, perfil, email, accao, hora) VALUES (?,?,?,?,?,?)";
		
		try {
			Connection conn = DBConnector.DBConnect();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("Nome: " + log.getNome());
			ps.setString(1, log.getNome());
			ps.setString(2, log.getApelido());
			ps.setString(3, log.getPerfil());
			ps.setString(4, log.getEmail());
			ps.setString(5, log.getAccao());
			ps.setObject(6, log.getDataHora());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
    public List<Logs> listarLogs() {
        List<Logs> logs = new ArrayList<>();
        String sql = "SELECT * FROM Logs";
        try {
            Connection conn = DBConnector.DBConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Logs log = new Logs(
                    rs.getInt("Codigo"),
                    rs.getString("Nome"),
                    rs.getString("Apelido"),
                    rs.getString("Perfil"),
                    rs.getString("Email"),
                    rs.getString("Accao"),
                    (LocalDateTime) rs.getObject("Hora")
                );
                logs.add(log);
                System.out.println(log.toString());
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}

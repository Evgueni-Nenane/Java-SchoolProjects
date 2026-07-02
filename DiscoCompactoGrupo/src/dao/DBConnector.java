package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnector {
	private static final String URL = "jdbc:mysql://localhost:3306/discocompacto";
	private static final String USER = "root";
	private static final String PASSWORD = "System.out.print";
	private static Connection conn = null;

	public static Connection DBConnect() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			}
			return conn;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de Conexão: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
}
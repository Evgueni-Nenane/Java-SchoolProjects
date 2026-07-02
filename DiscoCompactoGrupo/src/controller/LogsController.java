package controller;

import java.util.List;

import dao.LogsDAO;
import model.Logs;

public class LogsController {
	public LogsDAO logsDAO;

	public LogsController() {
		this.logsDAO = new LogsDAO();
	}
	
	public boolean inserirLog(Logs log) {
		return logsDAO.inserir(log);
	}
	
	public List<Logs> listarLogs() {
		return logsDAO.listarLogs();
	}
}
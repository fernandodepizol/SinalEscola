package br.com.sinal.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoFactory {
	private static final String USUARIO = "user";
	private static final String SENHA = "q1w2e3r4";
	private static final String URL = "jdbc:derby://localhost:1527/sinoal";

	public static Connection conectar() throws SQLException{
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		
		return conexao;
	}
	
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conexão Realizada com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("ERRO");
			e.printStackTrace();
		}
	}
	
}

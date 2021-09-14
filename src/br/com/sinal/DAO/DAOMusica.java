package br.com.sinal.DAO;

import java.beans.Statement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import br.com.sinal.dominio.Musica;
import br.com.sinal.hibernate.ConexaoFactory;

public class DAOMusica extends GenericDAO<Musica> {

	
	
	public String buscaMusicaAtual() {
		try {
		String musica = "";
		Scanner in = new Scanner(new FileReader("C:\\Sinal\\arquivo.txt"));
			musica = in.nextLine();
			while (in.hasNextLine()) {
			    String line = in.nextLine();
			    System.out.println(line);
			}
			in.close();
			return musica;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		
	}
	
	public Musica buscaProximoSinal() {
		
		
		
		SimpleDateFormat formatador = new SimpleDateFormat(
				"HH:mm:ss");
		
		Date horaAtual = Calendar.getInstance().getTime();
		
		
		String horaFormatadaAtual = formatador.format(horaAtual);
		
		Date data1 = null;
		try {
			data1 = (Date) formatador.parse(horaFormatadaAtual);
			
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(data1);
		System.out.println(cal1.getTimeZone());
		System.out.println(cal1.getTime());
		//cal1 = formatador.format(cal1);
		
		Musica proximoSinal = new Musica();
		
		long auxDiferenca = 99999999999999999L;
		List<Musica> musicas = new ArrayList<>();
		try {
			musicas = listaSinal();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Musica musica:musicas){
			//System.out.println(musica.getHorario());
			Date horarioMusica = null;
			try {
				horarioMusica = (Date) formatador.parse(musica.getHorario());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(horarioMusica);
			long diferenca = cal2.getTimeInMillis() - cal1.getTimeInMillis();
			
			if(diferenca >= 0){
				if(diferenca < auxDiferenca && diferenca >= 0 && diferenca < 99999999999999999L){
					auxDiferenca = diferenca;
					proximoSinal = musica;
				}
			}
			}
			//caso não tenha mais sinal neste dia, continua no próximo dia
		if(proximoSinal.getHorario() == null){
			Calendar cal2 = Calendar.getInstance();
			
				for(Musica musica : musicas){
					Date horarioMusica = null;
					try {
						horarioMusica = (Date) formatador.parse(musica.getHorario());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					//cal2.add(Calendar.DATE, 1);
					cal2.setTime(horarioMusica);
					if(cal2.get(Calendar.DATE) == cal1.get(Calendar.DATE)){
						cal2.add(Calendar.DATE, 1);
					}
					long diferenca = cal2.getTimeInMillis() - cal1.getTimeInMillis();
					
					if(diferenca >= 0){
						if(diferenca < auxDiferenca && diferenca >= 0 && diferenca < 99999999999999999L){
							auxDiferenca = diferenca;
							proximoSinal = musica;
						}
					}
				}
		}
		
		

		
			return proximoSinal;
		
		
		

	}
	
	
	
	public Musica buscaSinal(Musica sinal)throws SQLException{
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT id, musica, tempo, horario FROM sinal.musica WHERE sinal.musica.id = ? ");
		
		Connection conexao=new ConexaoFactory().conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, sinal.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Musica sinalMusica = new Musica();
		if(resultado.next()){
			
			sinalMusica.setCodigo(resultado.getLong("id"));
			sinalMusica.setHorario(resultado.getString("horario"));
			sinalMusica.setMusica(resultado.getString("musica"));
			sinalMusica.setTempo(resultado.getString("tempo"));
		}else{
			System.out.println("A busca não encontrou nenhum resultado!");
		}
	
		
		return sinalMusica;
	}
	
	
	
	
	public void adicionaSinal(Musica musica) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		
		StringBuilder sql= new StringBuilder();
		
		musica.setCodigo(null);
		
		sql.append("INSERT INTO sinal.musica (ID, MUSICA, TEMPO, HORARIO) VALUES (default, ?, ?, ?)");
		
			
			
	
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			//comando.setString(1, musica.getCodigo().toString());
			comando.setString(1, musica.getMusica());
			comando.setString(2, musica.getTempo());
			comando.setString(3, musica.getHorario());
		
			comando.executeUpdate();
	
	}
	
	
	public void excluiSinal(Musica musica) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		
		StringBuilder sql= new StringBuilder();
		
		
		
		sql.append("DELETE FROM sinal.musica WHERE ID = ?");
		
			
			
	
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			//comando.setString(1, musica.getCodigo().toString());
			comando.setLong(1, musica.getCodigo());
			
		
			comando.executeUpdate();
	
	}
	
	public void atualizaSinal(Musica musica) throws SQLException{
		Connection conexao = ConexaoFactory.conectar();
		
		StringBuilder sql= new StringBuilder();
		
		
		
		sql.append("UPDATE sinal.musica "
				+ "SET MUSICA = ?, HORARIO = ?, TEMPO = ?"
				+ "WHERE ID = ?");
		
			
			
	
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1, musica.getMusica());
			comando.setString(2, musica.getHorario());
			comando.setString(3, musica.getTempo());
			comando.setLong(4, musica.getCodigo());
			
		
			comando.executeUpdate();
	
	}
	
	public void atualizaTodos(String musica){
		try {
			List<Musica> musicas = listaSinal();
			for(Musica musicaSinal : musicas){
				musicaSinal.setMusica(musica);
				atualizaSinal(musicaSinal);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Musica> listaSinal() throws SQLException{
		System.out.println("LISTA SINAL");
		
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT id, musica, horario, tempo FROM SINAL.MUSICA");
			
		
			
			Connection conexao = ConexaoFactory.conectar();
			
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			
	
			ResultSet resultado = comando.executeQuery();
			List<Musica> musicas = new ArrayList<>();
			
			while (resultado.next()){
				Musica musica = new Musica();
				musica.setCodigo(resultado.getLong("id"));
				musica.setMusica(resultado.getString("musica"));
				musica.setHorario(resultado.getString("horario"));
				musica.setTempo(resultado.getString("tempo"));
				musicas.add(musica);
			}
			
			
		/*	for(Musica musicax : musicas){
				System.out.println(musicax.getHorario());
				System.out.println(musicax.getMusica());
				System.out.println(musicax.getTempo());
				System.out.println(musicax.getCodigo());
			}*/
			/*if(resultado.next()){
				return resultado;
			}else{
				return null;
			}*/
			
			return musicas;
	
	}
	
	public static void main(String[] args) {
		Musica musica = new Musica();
		
		//teste primeiro metodo (Adicionar)
		/*musica.setMusica("C:\\Sinal\\teste\\mp3");
		
		musica.setHorario("08:20:00");
		musica.setTempo("20");
		musica.setCodigo(null);*/
		
		
		DAOMusica musicaDAO = new DAOMusica();
		try {
			Musica musica2 = musicaDAO.buscaProximoSinal();
			System.out.println(musica2.getHorario());
			System.out.println(musica2.getCodigo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			musicaDAO.adicionaSinal(musica);
			System.out.println("Sucesso ao salvar a música!!!");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar a música");
			e.printStackTrace();
		}*/
		
		//Teste segundo metodo (Buscar uma musica)
		System.out.println("Rodando...");
		//DAOMusica musicaDAO = new DAOMusica();
		//try {
			
			//ResultSet resultado =musicaDAO.listaSinal();
			
		/*} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		//musica.setCodigo(1L);
		//try {
			//musica = musicaDAO.buscaSinal(musica);
			//System.out.println(musica.getCodigo()+"\n"+musica.getMusica()+"\n"+musica.getHorario()+"\n"+ musica.getTempo());
		//} catch (SQLException e) {
		//	System.out.println("Erro ao buscar o registro.");
		//	e.printStackTrace();
		//}
	}
	public String teste(String teste){
		JOptionPane.showMessageDialog(null, teste);
		return "vai";
	}
}

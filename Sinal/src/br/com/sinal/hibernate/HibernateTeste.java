package br.com.sinal.hibernate;

import org.hibernate.Session;

import br.com.sinal.DAO.DAOMusica;
import br.com.sinal.dominio.Musica;

public class HibernateTeste {

	public static void main(String[] args) {
		
		System.out.println("Iniciando o salvamento da música...");
		
		//Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		/*System.out.println("--Aberto? -->"+ sessao.isOpen());
		System.out.println("--Conectado? --> "+ sessao.isConnected());
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
		System.out.println("--Aberto? -->"+ sessao.isOpen());
		System.out.println("--Conectado? -->"+ sessao.isConnected());*/
		salvarMusica();
	}
	

	public static void salvarMusica(){
		Musica musica = new Musica();
		musica.setMusica("TESTE DE NOME DE MUSICA");
		musica.setTempo("00:01:30");
		musica.setHorario("07:00:00");
		
		DAOMusica daoMusica = new DAOMusica();
		daoMusica.salvar(musica);
		System.out.println("Musica Salva com sucesso!!!");
	}

}

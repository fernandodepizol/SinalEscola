package job;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.sinal.DAO.DAOMusica;
import br.com.sinal.dominio.Musica;

public class SinalJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		DAOMusica daoMusica = new DAOMusica();
		try {
			Musica musica = daoMusica.buscaProximoSinal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}

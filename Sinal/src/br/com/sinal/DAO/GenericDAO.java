package br.com.sinal.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.sinal.hibernate.HibernateUtil;

public class GenericDAO<Entidade> {
	public void salvar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try{
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
			
		}finally {
			sessao.close();
		}
		
	}
}

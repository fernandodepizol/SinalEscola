package br.com.sinal.dominio;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Musica extends GenericDomain{
	
	private String musica;
	private String tempo;
	private String horario;
	
	
	
	
	public String getMusica() {
		return musica;
	}
	public void setMusica(String musica) {
		this.musica = musica;
	}
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
}

package br.com.sinal.Utitilitarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHora {
	Date data;

	public DataHora(Date dataHora){
		this.data = dataHora;
	}
	public String horaFormatada(){
		String horaFormatada = "";
		DateFormat formato = new SimpleDateFormat("HH:mm:ss");
		horaFormatada = formato.format(data);
		return horaFormatada;
	}
	
	public String dataFormatada(){
		String dataFormatada = "";
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatada = formato.format(data);
		return dataFormatada;
	}
	
	@SuppressWarnings("deprecation")
	public String diaDaSemana(){
		String diaDaSemana="";
		switch (data.getDay()){
		case 0:
			diaDaSemana = "Domingo";
			break;
		case 1:
			diaDaSemana = "Segunda-Feira";
			break;
		case 2:
			diaDaSemana = "Terça-Feira";
			break;
		case 3:
			diaDaSemana = "Quarta-Feira";
			break;
		case 4:
			diaDaSemana = "Quinta-Feira";
			break;
		case 5:
			diaDaSemana = "Sexta-Feira";
			break;
		case 6:
			diaDaSemana = "Sábado";
			break;
		default:
			break;
		}
		
		return diaDaSemana;
	}
}

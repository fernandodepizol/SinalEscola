package br.com.sinal.Utitilitarios;

public class StartDerby {


	    public static void main(String[] args) {
	        try {
	            //Executa comando do windows ou linux no rt.exec("comandos");
	            Runtime rt = Runtime.getRuntime();
	            Process protesto = rt.exec("cmd /c cd");
	            Process proc = rt.exec("cmd /c start C:\\PROGRAMACAO_FERNANDO\\ProjetoSinal2\\derby\\bin\\startNetworkServer -h 0.0.0.0");
	            //Process proc = rt.exec("cmd /c start derby\\bin\\startNetworkServer.bat");//Envés de "derby\\bin\\startNetworkServer.bat" pode ser C:\\Tao\\Oquefor
	            //No meu caso descompactei na pasta do meu programa e do start.jar e chamei de derby a pasta do server.
	            proc = rt.exec("cmd.exe /c start java -jar C:\\PROGRAMACAO_FERNANDO\\ProjetoSinal2\\Sinal.jar");//Envés de "SeuPrograma.jar" pode ser C:\\Oquefor\\SeuPrograma.jar
	           //Process proc = rt.exec("cmd /c start derby\\bin\\stopNetworkServer.bat"); para parar o servidor.
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	    }
	
}

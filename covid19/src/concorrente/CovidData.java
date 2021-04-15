package concorrente;

import java.io.File;

public class CovidData {
	String nomeArquivo;
	
	public CovidData(String nomeArquivo) {
		File arq = new File(nomeArquivo);
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean hasNext(){
		return true;
	}
	
	public void lineProcess(String linha) {
		
	}

}

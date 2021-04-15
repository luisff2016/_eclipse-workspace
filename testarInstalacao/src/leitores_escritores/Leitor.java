package leitores_escritores;

/**
 * Classe Leitor - Thread
 * @author sergioluna
 *
 */
public class Leitor extends Thread {
	
	private Jornal jornal;
	
	/**
	 * Método de Inicialização
	 * @param nome
	 * @param jornal
	 */
	public Leitor(String nome, Jornal jornal){
		super(nome);
		this.jornal = jornal;
		this.setPriority(2);
	}
	
	/**
	 * Método Start da thread Leitor
	 */
	@Override
	public void run() {
		System.out.println("Trabalho de leitura solicitado pelo " + Thread.currentThread().getName());
		try {
			jornal.printJob();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
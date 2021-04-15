package leitores_escritores;

/**
 * Classe Escritor - Thread
 * 
 * @author sergioluna
 *
 */
public class Escritor extends Thread {

	private Jornal jornal;

	/**
	 * Método de Inicialização
	 * 
	 * @param nome
	 * @param jornal
	 */
	public Escritor(String nome, Jornal jornal) {
		super(nome);
		this.jornal = jornal;
		this.setPriority(1);
	}

	/**
	 * Start de uma thread escritor
	 */
	@Override
	public void run() {
		System.out.println("Trabalho de escrita solicitado pelo " + Thread.currentThread().getName());
		try {
			jornal.printJob();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
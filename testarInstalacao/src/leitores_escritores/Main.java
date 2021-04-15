package leitores_escritores;

/**
 * Classe que gerencia o problema
 * 
 * @author sergioluna
 *
 */
public class Main {
	// Quantidade de Jornalistas - Constante
	private static final int QUANTIDADE_JORNALISTAS = 3;

	// Array para armazenar os Leitores
	private static Leitor[] leitores;

	// Array para armazenar os escritores
	private static Escritor[] escritores;

	/**
	 * Método principal do projeto Gerencia as principais funcionalidades
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Jornal jornal = new Jornal();

		inicializaJornalistas(jornal);

		try {
			startJornalistas();
			waitingJornalistas();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nOs jornalistas finalizaram seus trabalhos!");

	}

	/**
	 * Método para inicializar os jornalistas
	 * 
	 * @param jornal
	 */
	private static void inicializaJornalistas(Jornal jornal) {

		leitores = new Leitor[QUANTIDADE_JORNALISTAS];
		for (int i = 0; i < QUANTIDADE_JORNALISTAS; i++) {
			leitores[i] = new Leitor("Leitor: " + (i + 1), jornal);
		}

		escritores = new Escritor[QUANTIDADE_JORNALISTAS];
		for (int i = 0; i < QUANTIDADE_JORNALISTAS; i++) {
			escritores[i] = new Escritor("Escritor: " + (i + 1), jornal);
		}
	}

	/**
	 * Método para fazer com os processos aguardem
	 * 
	 * @throws InterruptedException
	 */
	private static void waitingJornalistas() throws InterruptedException {

		for (Leitor leitor : leitores) {
			leitor.join();
		}

		for (Escritor escritor : escritores) {
			escritor.join();
		}
	}

	/**
	 * Realiza o start dos jornalistas
	 */
	private static void startJornalistas() {
		for (Leitor leitor : leitores) {
			leitor.start();
		}

		for (Escritor escritor : escritores) {
			escritor.start();
		}
	}

}

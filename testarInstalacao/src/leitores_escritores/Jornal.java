package leitores_escritores;

import java.util.concurrent.Semaphore;

/**
 * Classe Jornal que implementa a a??o do Leitor e Escritor
 * 
 * @author sergioluna
 *
 */
public class Jornal {
	/**
	 * Sem?foro para gerenciar o acesso do Leitor e Escritor
	 */
	private Semaphore semaforo = new Semaphore(1, true);

	/**
	 * Vari?vel compartilhada pelos processos
	 */
	private int gols = 0;

	/**
	 * M?todo principal do jornal
	 * 
	 * @throws InterruptedException
	 */
	public void printJob() throws InterruptedException {
		try {
			if (Thread.currentThread().getPriority() == 1) {
				semaforo.acquire();
				gols++;
				int duracao = (int) (Math.random() * 5) + 1;
				// System.out.print(Thread.currentThread().getName());
				// System.out.print(" escrevendo por " + duracao + "segundos\n");
				System.out.print("------> " + Thread.currentThread().getName());
				System.out.print(" afirma que o Brasil fez 1 gol! Brasil " + gols + " x 0 Alemanha\n");
				Thread.sleep(duracao * 1000);
			} else {
				semaforo.acquire();
				int duracao = (int) (Math.random() * 5) + 1;
				// System.out.print(Thread.currentThread().getName());
				// System.out.print(" lendo por " + duracao + "segundos\n");
				System.out.print(Thread.currentThread().getName());
				System.out.print(" leu e verificou que o placar est?: Brasil " + gols + " x 0 Alemanha\n");
				Thread.sleep(duracao * 1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}
}
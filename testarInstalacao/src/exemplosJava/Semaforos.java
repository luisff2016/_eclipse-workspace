package exemplosJava;

import java.util.concurrent.Semaphore;

public class Semaforos {

	// private static List<String> bancoDeDados = new ArrayList<String>();
	public static void main(String[] args) throws InterruptedException {
		int numeroDePermicoes = 2;
		int numeroDeProcessos = 10;
		Semaphore sem = new Semaphore(numeroDePermicoes);
		ProcessadorThread[] processos = new ProcessadorThread[numeroDeProcessos];
		for (int i = 0; i < numeroDeProcessos; i++) {
			processos[i] = new ProcessadorThread(sem);
			processos[i].start();
		}
		for (int i = 0; i < numeroDeProcessos; i++) {
			processos[i].join();
		}
		System.out.println("*** FIM da Thread " + Thread.currentThread().getName());
	}

	public static class ProcessadorThread extends Thread {
		Semaphore semaforo;

		public ProcessadorThread(Semaphore semaf) {
			this.semaforo = semaf;

		}

		private void processar() {
			try {
				System.out.println("# ******************* " + Thread.currentThread().getName() + " processando");
				Thread.sleep((long) (Math.random() * 1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void entrarRegiaoNaoCritica() {
			System.out.println("# " + Thread.currentThread().getName() + " em região não crítica");
			processar();
		}

		private void entrarRegiaoCritica() {
			System.out.println("#==> " + Thread.currentThread().getName() + " entrando em região crítica");
			processar();
			System.out.println("#<== " + Thread.currentThread().getName() + " saindo da região crítica");
		}

		public void run() {
			entrarRegiaoNaoCritica();
			try {
				semaforo.acquire();
				entrarRegiaoCritica();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}

	}

}

package atv_Deadlocks;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Exercico1 {
	public static void main(String[] args) throws InterruptedException {

		System.out.println("*** Deadlock: Exercicio1 *** \n");
		
		int numPermissoes = 1;
		Semaphore x = new Semaphore(numPermissoes);
		Semaphore y = new Semaphore(numPermissoes);
		Semaphore z = new Semaphore(numPermissoes);

		Requisitar A = new Requisitar("X", x, "Y", y);
		Requisitar B = new Requisitar("X", x, "Z", z);
		Requisitar C = new Requisitar("Y", y, "Z", z);

		A.start();
		B.start();
		C.start();

		System.out.println(" *** FIM ***");
	}

//	thread A B C
//	recursos X Y Z
	static class Requisitar extends Thread {
		private String rec1;
		private Semaphore sol1;
		private String rec2;
		private Semaphore sol2;

		public Requisitar(String r1, Semaphore sl1, String r2, Semaphore sl2) throws InterruptedException {
			this.rec1 = r1;
			this.sol1 = sl1;
			this.rec2 = r2;
			this.sol2 = sl2;

		}

		public void processamento() {
			try {
				// faz a avaliacao se os recursos estao disponiveis
				if (this.sol1.tryAcquire() && this.sol2.tryAcquire()) {

					System.out.println(Thread.currentThread().getName() + " ==> Requisitando: " + rec1 + " e " + rec2);
					System.out.printf(Thread.currentThread().getName() + " ==> Processando ...................");
				}

			} finally {

				// força a liberação dos recursos
				this.sol2.release();
				this.sol1.release();
				System.out.println("\n" + Thread.currentThread().getName() + " ==> Liberando: " + rec1 + " e " + rec2);
			}
		}

		@Override
		public void run() {
			while (true) {
				intervaloAleatorio();
				System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
				processamento();
			}
		}
	}

	public static void intervaloAleatorio() {
		try {
			Thread.sleep(inteiroAleatorio(1, 5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int inteiroAleatorio(int min, int max) {
		Random aleatorio = new Random();
		int valor = aleatorio.nextInt((max - min) + 1) + min;
		return valor*1000;
	}
}

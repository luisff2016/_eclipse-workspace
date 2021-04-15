package exemplosJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class RegiaoCritica {

	static List<String> bancoDeDados = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("*** Comunicação entre processos: Exercicio1 ***");
		Semaphore semaf = new Semaphore(5);
		Escritor escritor = new Escritor(semaf);
		Thread e0 = new Thread(escritor);
		Thread e1 = new Thread(escritor);

		Leitor leitor = new Leitor();
		Thread l0 = new Thread(leitor);
		Thread l1 = new Thread(leitor);
//		Thread t1 = new Thread(runnable);
//		Thread t2 = new Thread(runnable);
//		Thread t3 = new Thread(runnable);
		// Thread.sleep((long) (Math.random() * 1000));
		// (int) (Math.random() * 100))

		e0.start();
		e1.start();
		l0.start();
		l1.start();
//		t2.start();
//		t3.start();
		e0.join();
		e1.join();
		l0.join();
		l1.join();
		imprimir();

		System.out.println("FIM*********************");

	}

	public static void imprimir() {

		System.out.println(bancoDeDados);
	}

	public static class Escritor extends Thread {
		private static Semaphore semaforo;

		public Escritor(Semaphore b) {
			semaforo = b;
		}

		private static void processar() {
			try {
				System.out.println("Thread #" + Thread.currentThread().getName() + " processando");
				Thread.sleep((long) (Math.random() * 10000));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private static void entrarRegiaoNaoCritica() {
			System.out.println("Thread #" + Thread.currentThread().getName() + " em região não crítica");
			processar();
		}

		private static void entrarRegiaoCritica() {
			System.out.println("Thread #" + Thread.currentThread().getName() + " entrando em região crítica");
			processar();
			System.out.println("Thread #" + Thread.currentThread().getName() + " saindo da região crítica");
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

	public static class Leitor implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("Tamanho apos leitura = " + bancoDeDados.size());
//				if (lista.size() > 0) {
//					lista.remove(0);
//					
//					
//				}
			}

		}
	}

}

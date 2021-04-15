package atv_Processos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercicio1 {
	static int tam = 20;
	private static Lock onLock = new ReentrantLock();

	static String banco[] = new String[tam];
	static int repetir = 100;
	static int limitador = 0;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("*** Comunicação entre processos: Exercicio1 ***");

		limpar();

		// imprimir();

		Escritor escritor = new Escritor();
		Leitor leitor = new Leitor();

		Thread e0 = new Thread(escritor);
		Thread e1 = new Thread(escritor);
		Thread e2 = new Thread(escritor);
		Thread l0 = new Thread(leitor);
		Thread l1 = new Thread(leitor);

		e0.start();
		e1.start();
		e2.start();
		l0.start();
		l1.start();
		
		e0.join();
		e1.join();
		e2.join();
		l0.join();
		l1.join();
		//		imprimir();
		System.out.println(" *** FIM *** ");

	}

	public static void imprimir() {

		for (int i = 0; i < tam; i++) {
			System.out.println(banco[i]);
		}
	}

	public static void limpar() {

		for (int i = 0; i < tam; i++) {
			banco[i] = ".";
		}
	}

	public static class Escritor extends Thread {

		@Override
		public void run() {
			
			while (limitador < repetir) {
				limitador++;
				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int index = (int) (Math.random() * tam);
				onLock.lock();
				banco[index] = Double.toString(Math.random() * tam * 1000);
				System.out.printf(Thread.currentThread().getName() + "\tEscrevendo: posicao(%s) =>\t %s \n", index,
						banco[index]);
				onLock.unlock();

			}

		}

	}

	public static class Leitor extends Thread {

		@Override
		public void run() {

			while (limitador < repetir) {
				limitador++;
				try {
					Thread.sleep((long) (Math.random() * 1500));
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				int index = (int) (Math.random() * tam);
				System.out.printf(Thread.currentThread().getName() + "\tLendo: posicao(%s) => \t\t %s \n", index,
						banco[index]);
			}

		}
	}
}

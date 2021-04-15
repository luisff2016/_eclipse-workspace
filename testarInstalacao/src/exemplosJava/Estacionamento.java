package exemplosJava;

import java.util.concurrent.Semaphore;

public class Estacionamento {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			
			Carro carro = new Carro("Carro #" + i);
			carro.start();
		}
	}

//	import java.util.concurrent.*;
	public static class Carro extends Thread {
		private static Semaphore estacionamento = new Semaphore(4, true);

		public Carro(String nome) {
			super(nome);
		}

		public void run() {
			try {
				estacionamento.acquire();
				System.out.println(getName() + " ocupou vaga.");
				
				sleep((long) (Math.random() * 10));
				System.out.println(getName() + " liberou vaga.");
				estacionamento.release();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

	}

}

package canalRinaldoDev;


public class Synchronized_1 {

	static int i = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Synchronized_1");

		MeuRunnable runnable = new MeuRunnable();
		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);

		t0.start();
		t1.start();
		t2.start();
		t3.start();

	}

	public static void imprimir() {
		// sincronizando um trecho em um metodo static
		synchronized (Synchronized_1.class) {
			i++;
			String name = Thread.currentThread().getName();
			System.out.println(name + ": " + i);
		}
	}

	public static class MeuRunnable implements Runnable {
		// criar objetos para separar as travas do synchronized
		static Object Lock1 = new Object();

		static Object Lock2 = new Object();

		@Override
		// public synchronized void run() { // sincronizando a classe
		public void run() {
			
			// usar racionalmente o sincronismo apenas na area critica
			int j;
			synchronized (this) {
				i++;
				j = i * 2;
			}
			double jElevadoA100 = Math.pow(j, 100);
			double raizDeJ = Math.sqrt(jElevadoA100);
			System.out.println(raizDeJ);
			// imprimir();

			/*
			 * System.out.println("Sincronizando um trecho de codigo"); synchronized (Lock1)
			 * { // sincronizando um trecho de codigo i++; String name =
			 * Thread.currentThread().getName(); System.out.println(name + ": " + i); }
			 * 
			 * synchronized (Lock2) { // sincronizando um trecho de codigo i++; String name
			 * = Thread.currentThread().getName(); System.out.println(name + ": " + i); }
			 */

		}
	}

}

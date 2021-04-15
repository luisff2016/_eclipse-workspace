package atv_ProblemasClassicos;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercicio4 {
//	private static List<String> lista = new ArrayList<String>();

	public static void main(String[] args) {
		System.out.println("*** Problemas Classicos: Exercicio4 *** ");
		System.out.println(" Barbeiro Dorminhoco com no maximo 10 clientes. ***");

		ExecutorService barbeiro1 = Executors.newSingleThreadExecutor();
		ExecutorService cliente1 = Executors.newFixedThreadPool(10);
		Barbeiro b1 = new Barbeiro();
		Cliente c1 = new Cliente();
		barbeiro1.execute(b1);
		cliente1.execute(c1);

		System.out.println(" *** FIM ***");
	}

	static class Semaforo {

		private int value;

		public Semaforo(int v) {
			value = v;
		}

		public synchronized void P() {
			while (value <= 0) {
				try {
					wait();
				} catch (Exception e) {
					System.out.println("Erro na espera");
				}
			}
			value--;
		}

		public synchronized void V() {
			++value;
			notify(); // acordar proccesso
		}
	}

	public static class Cliente extends Thread {

		public void clientes() {
			Barbeiro.mutex.P();

			if (Barbeiro.waiting < Barbeiro.CADEIRAS) {
				Barbeiro.waiting++;
				Barbeiro.clienteA.V();
				Barbeiro.mutex.V();
			} else {
				System.out.println("Salão lotado, volte outro horario!");
				Barbeiro.mutex.V();
			}
		}

		@Override
		public void run() {
			while (true) {
				intervaloAleatorio(3, 7);
				clientes();
				System.out.println("Chegou +1");
				System.out.printf("Tem %d clientes aguardando\n", Barbeiro.waiting);
			}

		}
	}

	public static class Barbeiro extends Thread {
		static final int CADEIRAS = 10;
		static Semaforo clienteA = new Semaforo(0); // 0 Esperando para cortar e 1 cortando
		static Semaforo barbeiro = new Semaforo(0); // 1 Cortando e 0 dormindo
		static Semaforo mutex = new Semaforo(1); // Exclusão mútua
		static int waiting = 0;

		static boolean cortando = false;
		static boolean dormindo = false;

		public void cortarCabelo() {
			System.out.println("Cortando cabelo");
			cortando = true;
			try {
				intervaloAleatorio(1, 10);
				System.out.println("Terminou! Venha o proximo ... ");
				cortando = false;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void barbers() {
			while (true) {
				System.out.println(waiting);
				if (waiting <= 0) {
					System.out.println("Dormindo ... ");
					dormindo = true;
					clienteA.P();
				} else {
					mutex.P();
					waiting--;
					barbeiro.V();
					mutex.V();
					dormindo = false;
					cortarCabelo();

				}
			}
		}

		@Override
		public void run() {
			while (true) {
				barbers();
			}
		}

	}

	public static void intervaloAleatorio(int min, int max) {
		Random aleatorio = new Random();
		int valor = aleatorio.nextInt((max - min) + 1) + min;
		try {
			Thread.sleep(valor * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

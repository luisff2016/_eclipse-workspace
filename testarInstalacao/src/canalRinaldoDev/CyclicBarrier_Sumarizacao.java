package canalRinaldoDev;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class CyclicBarrier_Sumarizacao {

	private static BlockingQueue<Double> resultados = new LinkedBlockingQueue<Double>();

	private static ExecutorService executor;
	private static Runnable r1;
	private static Runnable r2;
	private static Runnable r3;
	private static Runnable r4;

	public static void main(String[] args) {

		Runnable sumarizacao = () -> {
			System.out.println("Somando tudo!");
			double somaFinal = 0;
			somaFinal += resultados.poll();
			somaFinal += resultados.poll();
			somaFinal += resultados.poll();
			somaFinal += resultados.poll();
			System.out.println("Sub-total = " + somaFinal);
			System.out.println("__________________________________");
			sleep();
//			restart();
		};

		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, sumarizacao);

//		executor = Executors.newFixedThreadPool(4);
		executor = Executors.newCachedThreadPool();

		r1 = () -> {
			while (true) {
				resultados.add(432d * 3d);
				System.out.println(Thread.currentThread().getName());
				await(cyclicBarrier);

			}

		};

		r2 = () -> {
//			rodando o restart em cada thread
			while (true) {
				resultados.add(Math.pow(3d, 14d));
				System.out.println(Thread.currentThread().getName());
				await(cyclicBarrier);
			}

		};

		r3 = () -> {
//			rodando o restart em cada thread
			while (true) {
				resultados.add(45 * 127d / 12d);
				System.out.println(Thread.currentThread().getName());
				await(cyclicBarrier);
			}

		};

		r4 = () -> {
			// rodando o restart em cada thread
			while (true) {
				resultados.add(1045d / 5d);
				System.out.println(Thread.currentThread().getName());
				await(cyclicBarrier);
			}

		};

		restart();
//		executor.shutdown();

	}

	private static void sleep() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void await(CyclicBarrier cyclicBarrier) {
		try {
			cyclicBarrier.await();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void restart() {
		sleep();
		executor.submit(r1);
		executor.submit(r2);
		executor.submit(r3);
		executor.submit(r4);

	}

}

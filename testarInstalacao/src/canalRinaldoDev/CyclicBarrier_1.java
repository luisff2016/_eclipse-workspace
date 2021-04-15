package canalRinaldoDev;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class CyclicBarrier_1 {

	private static BlockingQueue<Double> resultados = new LinkedBlockingQueue<Double>();

	public static void main(String[] args) {

		Runnable soma = () -> {
			System.out.println("Somando tudo!");
			double somaFinal = 0;
			somaFinal += resultados.poll();
			somaFinal += resultados.poll();
			somaFinal += resultados.poll();
			System.out.println("TOTAL = " + somaFinal);
		};

		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, soma);

		ExecutorService executor = Executors.newFixedThreadPool(3);

		Runnable r1 = () -> {
//			System.out.println(432d * 3d);
			resultados.add(432d * 3d);
			System.out.println(Thread.currentThread().getName());
			await(cyclicBarrier);
			System.out.println(Thread.currentThread().getName());
//			System.out.println("Primeiro termo");
		};

		Runnable r2 = () -> {
//			System.out.println(Math.pow(3d, 14d));
			resultados.add(Math.pow(3d, 14d));
			System.out.println(Thread.currentThread().getName());
			await(cyclicBarrier);
			System.out.println(Thread.currentThread().getName());
//			System.out.println("Segundo termo");
		};

		Runnable r3 = () -> {
//			System.out.println(45 * 127d / 12d);
			resultados.add(45 * 127d / 12d);
			System.out.println(Thread.currentThread().getName());
			await(cyclicBarrier);
			System.out.println(Thread.currentThread().getName());
//			System.out.println("Terceiro termo");
		};

		executor.submit(r1);
		executor.submit(r2);
		executor.submit(r3);

		executor.shutdown();

	}

	private static void await(CyclicBarrier cyclicBarrier) {
		try {
			cyclicBarrier.await();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package exemplosJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int v[] = {99,9991,2,3,4,5,6,666,6666,87,33,333,0};
		ThreadEx1 threadA = new ThreadEx1("Thread de Ordenação", v);
//		threadA.start();

		System.out.println("**** Uma thread foi iniciada!");
		ExecutorService pool = Executors.newSingleThreadExecutor(); // thread unica
//		ExecutorService pool = Executors.newFixedThreadPool(4); // varias threads1
		pool.execute(threadA);

	}
	
	public static class ThreadEx1 extends Thread {
		private String nome;
		public ThreadEx1(String nome, int v1[]) {
			ordenar(v1);
		}

		@Override
		public void run() {
			System.out.printf("Thread: %s%n", Thread.currentThread().getName());
			System.out.printf("FIM da %s%n", this.nome);
		}
	}
	
	public static void ordenar(int v[]) {
		System.out.println();
//		this.nome = nome;
		int tam = v.length;
		int[] orig = new int[tam];
		int ordenado[] = new int[tam];
		int i, j, menor, aux;
		for (i = 0; i < tam; i++) {
			orig[i] = v[i];
			ordenado[i] = v[i];
		}

		for (i = 0; i < (tam - 1); i++) {
			menor = i;
			for (j = (i + 1); j < ordenado.length; j++) {
				if (ordenado[j] < ordenado[menor])
					menor = j;
			}
			aux = ordenado[i];
			ordenado[i] = ordenado[menor];
			ordenado[menor] = aux;
		}
		System.out.println("v[i] = Original, Ordenado ");
		System.out.println("--------------------------");
		for (i = 0; i < ordenado.length; i++) {
			System.out.printf("v[ %2d ] = %8d, %8d %n", i, orig[i], ordenado[i]);
		}
		
	}

}

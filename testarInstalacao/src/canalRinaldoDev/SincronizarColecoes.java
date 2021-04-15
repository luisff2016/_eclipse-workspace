package canalRinaldoDev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SincronizarColecoes {
	private static List<String> lista = new ArrayList<>();
//	declarando uma lista como colecao sincronizada
//	private static List<String> lista = Collections.synchronizedList(new ArrayList<>());

	public static void main(String[] args) throws InterruptedException {

		// lista sincronizada com restrição de acesso das threads
		lista = Collections.synchronizedList(lista);
		//outros tipos de colecoes sincronizadas
		/*
		 * lista = 
		 * Collections.synchronizedCollection(lista); 
		 * lista = 
		 * Collections.synchronizedMap(lista); 
		 * lista =
		 * Collections.synchronizedSet(lista); 
		 * lista =
		 * Collections.synchronizedSortedSet(lista);
		 */

		// exemplo de execucao
		MeuRunnable runnable = new MeuRunnable();
		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		t0.start();
		t1.start();
		t2.start();
		Thread.sleep(200);
		System.out.println(lista);

	}

	public static class MeuRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			lista.add("Incluir dados: " + Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName() + " Inseriu na lista");

		}

	}

}

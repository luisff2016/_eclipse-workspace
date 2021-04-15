package canalRinaldoDev;

import java.util.concurrent.BlockingQueue;
/*import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;*/
import java.util.concurrent.LinkedBlockingQueue;

public class ColecoesParaConcorrencia {

//	private static List<String> lista = new CopyOnWriteArrayList<String>();
//	private static Map<Integer, String> mapa = new ConcurrentHashMap<Integer, String>();
	private static BlockingQueue<String> fila = new LinkedBlockingQueue<String>();

	public static void main(String[] args) throws InterruptedException {
		// Coleções que são Thread-safe
		// ArrayList<>() ==> CopyOnWriteArrayList<String>(); // faz uma copia do array
		// antes de alterar, usar se tiver muitas leituras e pouca escrita.
		// HashMap<>() ==> ConcurrentHashMap<Integer, String>();

		// exemplo de execucao
		MeuRunnable runnable = new MeuRunnable();
		Thread t0 = new Thread(runnable);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		t0.start();
		t1.start();
		t2.start();
		Thread.sleep(200);
//		System.out.println(lista);
//		System.out.println(mapa);
		System.out.println(fila);
		// para remover elementos do inicio da fila
		fila.poll();
		System.out.println(fila);

	}

	public static class MeuRunnable implements Runnable {

		@Override
		public void run() {

//			lista.add("Incluir dados: " + Thread.currentThread().getName());
//			mapa.put(new Random().nextInt(9), "Curta este video!");
			fila.add("Faça sua tarefas! " + Thread.currentThread().getName());
			

			System.out.println(Thread.currentThread().getName() + " Inseriu na lista");

		}

	}

}

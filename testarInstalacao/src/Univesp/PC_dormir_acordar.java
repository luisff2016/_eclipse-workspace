package Univesp;

import java.util.ArrayList;
import java.util.List;

public class PC_dormir_acordar {
	static List<String> lista = new ArrayList<String>();
	static int LIMITE = 30;
	static int count = 0;

	public static void main(String[] args) {
		System.out.println(lista);
		Thread produtor = new Produtor();
		Thread consumidor = new Consumidor();

		Thread p1 = new Thread(produtor);
		Thread p2 = new Thread(produtor);

		Thread c1 = new Thread(consumidor);
		Thread c2 = new Thread(consumidor);

		p1.start();
		p2.start();

		c1.start();
		c2.start();
		
		System.out.println(lista);

	}

	public static class Produtor extends Thread {

		@Override
		public void run() {

			while (true) {
				
				if (count < LIMITE) {
					String item;
					item = criar_item();
					count++;
					if (count == 1) {
						Thread.interrupted();
					}
					System.out.println("Estoque => " + count + " ==> " + item);
					System.out.println(lista);
				} else {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		private String criar_item() {
			lista.add("PRODUZIDO pela thread: " + Thread.currentThread().getName());
			return lista.get(0);
		}

	}

	public static class Consumidor extends Thread {

		@Override
		public void run() {
			String it;
			while (true) {
				if (count > 0 && !lista.isEmpty()) {
					it = remove_item();
					count++;
					if (count == LIMITE - 1)
						Thread.interrupted();
					consumidor_item(it);
					System.out.println(lista);

				} else {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

		private void consumidor_item(String it) {
			System.out.println("ITEM Consumido: " + it);

		}

		private String remove_item() {
			if (lista.size() > 0) {
				String item_rmv = lista.get(0);
				lista.remove(0);
				return item_rmv;	
			}
			Thread.interrupted();
			return "Falhou!";
			
		}
	}

}

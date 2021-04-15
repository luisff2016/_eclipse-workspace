package atv_Processos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercicio2 {

	private static List<String> lista = new ArrayList<String>();

	static int tam = 10;
	private static Lock onLock = new ReentrantLock();

	static int repetir = 30;
	static int limitador = 0;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("*** Comunicação entre processos: Exercicio2 ***");

		Gravar addArray = new Gravar();			// adicionar uma posicao na lista com um dado randomico
		Visualizar getArray = new Visualizar();	// visualizar o conteudo de uma posicao aleatoria
		Excluir delArray = new Excluir();		// deletar o primeiro elemento da lista
		Atualizar upArray = new Atualizar();	// atualizar o conteudo de uma posicao aleatoria

		Thread a0 = new Thread(addArray);
		Thread g0 = new Thread(getArray);
		Thread d0 = new Thread(delArray);
		Thread u0 = new Thread(upArray);

		Thread a1 = new Thread(addArray);
		Thread g1 = new Thread(getArray);
		Thread d1 = new Thread(delArray);
		Thread u1 = new Thread(upArray);

		Thread g2 = new Thread(getArray);

		a0.start();
		g0.start();
		d0.start();
		u0.start();
		a1.start();
		g1.start();
		d1.start();
		u1.start();
		g2.start();

		a0.join();
		g0.join();
		d0.join();
		u0.join();
		a1.join();
		g1.join();
		d1.join();
		u1.join();
		g2.join();

		System.out.println("Lista Final = " + lista + "\n");

		System.out.println(" *** FIM ***");

	}

	public static class Gravar extends Thread {

		@Override
		public void run() {
			while (limitador < repetir) {
				limitador++;
				try {
					Thread.sleep((long) (Math.random() * 100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				onLock.lock();
				int index = ((int) (Math.random() * tam * 1000));
				lista.add(Integer.toString(index));
				System.out.printf(Thread.currentThread().getName() + "\tDado gravando ==> %d \n", index);
				System.out.println("\t\tLista apos Gravacao = " + lista + "\n");
				onLock.unlock();
			}
		}

	}

	public static class Visualizar extends Thread {

		@Override
		public void run() {
			while (limitador < repetir) {
				limitador++;
				try {
					Thread.sleep((long) (Math.random() * 200));
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				if (lista.size() > 0) {
					System.out.printf(Thread.currentThread().getName() + "\tUltimo elemento => \t %s \n\n",
							lista.get(lista.size() - 1));
				}

			}

		}
	}

	public static class Excluir extends Thread {

		@Override
		public void run() {
			while (limitador < repetir) {
				limitador++;
				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (lista.size() > 0) {
					onLock.lock();
					lista.remove(0);
					System.out.printf(Thread.currentThread().getName() + "\tRemovendo o primeiro da lista! \n");
					System.out.println("\t\tLista apos Exclusao = " + lista + "\n");
					onLock.unlock();
				} else {
					System.out.println("\n\t\t *** Lista está vazia! *** \n");
				}

			}

		}
	}

	public static class Atualizar extends Thread {

		@Override
		public void run() {
			while (limitador < repetir) {
				limitador++;
				try {
					Thread.sleep((long) (Math.random() * 500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!lista.isEmpty()) {
					int index = (int) (Math.random() * lista.size());

					if (lista.get(index) != null) {

						onLock.lock();
						lista.set(index, Integer.toString((int) (Math.random() * tam * 1000)));
						System.out.printf(Thread.currentThread().getName() + "\tAtualizacao da posicao (%d) \n", index);
						System.out.println("\t\tLista apos Atualizacao = " + lista + "\n");
						onLock.unlock();
					}
				}

			}

		}
	}

}

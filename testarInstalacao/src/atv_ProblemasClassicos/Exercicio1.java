package atv_ProblemasClassicos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Exercicio1 {

	private static List<String> lista = new ArrayList<String>();
	private static Semaphore mutex = new Semaphore(1);
	private static Semaphore db = new Semaphore(1);
	private static int rc = 0;

	public static void main(String[] args) {
		System.out.println("*** Problemas Classicos: Exercicio1 (ArrayList com thread safe) ***");

		AddList a1 = new AddList(db, mutex);
		a1.start();

		AddList a2 = new AddList(db, mutex);
		a2.start();

		ReadList r1 = new ReadList(db, mutex, rc);
		r1.start();

		ReadList r2 = new ReadList(db, mutex, rc);
		r2.start();

		ReadList r3 = new ReadList(db, mutex, rc);
		r3.start();

		DelList d1 = new DelList(db, mutex);
		d1.start();

		DelList d2 = new DelList(db, mutex);
		d2.start();

		UpList u1 = new UpList(db, mutex);
		u1.start();

		UpList u2 = new UpList(db, mutex);
		u2.start();

		System.out.println(" *** FIM ***");

	}

	public static class AddList extends Thread {
		Semaphore db;
		Semaphore mutex;

		public AddList(Semaphore db, Semaphore mutex) {
			super();
			this.db = db;
			this.mutex = mutex;
		}

		@Override
		public void run() {
			while (true) {
				tempoAleatorio();
				int index = valorAleatorio();
				try {
					this.db.acquire();
					lista.add(Integer.toString(index));
					System.out.printf(Thread.currentThread().getName() + "\t: Adicionando ==> %d \n", index);
					System.out.println("\t\tLista apos Gravacao = " + lista + "\n");
					this.db.release();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
					System.out.println("ERRO AddList ==> " + ie.toString());
				}
			}
		}
	}

	public static class ReadList extends Thread {
		public ReadList(Semaphore mutex, Semaphore db, int rc) {
			super();
			this.mutex = mutex;
			this.db = db;
			this.rc = rc;
		}

		Semaphore mutex;
		Semaphore db;
		int rc;

		@Override
		public void run() {
			while (true) {
				try {
					tempoAleatorio();
					mutex.acquire();
					this.rc = this.rc + 1;
					if (this.rc == 1) {
						this.db.acquire();
					}
					this.mutex.release();
					if (!lista.isEmpty()) {
						System.out.printf(Thread.currentThread().getName() + "\t: Primeiro elemento ==> %s \n",
								lista.get(0));
					} else {
						System.out.println("\t\tNão dá pra ler uma lista vazia!");
					}

					mutex.acquire();
					this.rc = this.rc - 1;
					if (rc == 0) {
						this.db.release();
					}
				} catch (InterruptedException ie) {
					ie.printStackTrace();
					System.out.println("ERRO ReadList ==> " + ie.toString());
				} finally {
					this.mutex.release();
				}
			}
		}
	}

	public static class DelList extends Thread {
		Semaphore db;
		Semaphore mutex;

		public DelList(Semaphore db, Semaphore mutex) {
			super();
			this.db = db;
			this.mutex = mutex;
		}

		@Override
		public void run() {
			while (true) {
				String removido = "";
				tempoAleatorio();
				try {
					this.db.acquire();
					if (lista.size() > 0) {
						removido = lista.get(0);
						lista.remove(0);
						System.out.printf(Thread.currentThread().getName() + "\t: Removido ==> %s \n", removido);
					} else {
						System.out.println("\t\tNão é possivel remover um elemento de uma lista vazia!");
					}
					

				} catch (InterruptedException ie) {
					ie.printStackTrace();
					System.out.println("ERRO DelList ==> " + ie.toString());
				} finally {
					this.db.release();
				}
			}
		}
	}

	public static class UpList extends Thread {
		Semaphore db;
		Semaphore mutex;

		public UpList(Semaphore db, Semaphore mutex) {
			super();
			this.db = db;
			this.mutex = mutex;
		}

		@Override
		public void run() {
			while (true) {
				String anterior = "";
				String atual = "";
				tempoAleatorio();
				try {
					this.db.acquire();
					if (lista.size() > 0) {
						anterior = lista.get(0);
						atual = Integer.toString(valorAleatorio());
						lista.set(0, atual);
						System.out.printf(Thread.currentThread().getName() + "\t: Atualizando de ==> %s para %s \n",
								anterior, atual);
					} else {
						System.out.println("\t\tLista está vazia, não tem elemento para alterar!");
					}

				} catch (InterruptedException ie) {
					ie.printStackTrace();
					System.out.println("ERRO UpList ==> " + ie.toString());
				} finally {
					this.db.release();
				}

			}
		}
	}

	public static void tempoAleatorio() {
//		System.out.println(Thread.currentThread().getName() + " ... dormindo ...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static int valorAleatorio() {
//		System.out.println(Thread.currentThread().getName() + " ... calculando ...");
		return ((int) (Math.random() * 1000));

	}

}


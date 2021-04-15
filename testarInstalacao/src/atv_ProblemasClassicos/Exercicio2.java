package atv_ProblemasClassicos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Exercicio2 {
	private static List<String> lista = new ArrayList<String>();
	private static Semaphore mutex = new Semaphore(1);
	private static Semaphore db = new Semaphore(1);
	private static int rc = 0;
	static ExecutorService leitura;
	static ExecutorService escrita;
	

	public static void main(String[] args) {
		System.out.println("*** Problemas Classicos: Exercicio2 *** ");
		System.out.println(" Banco de Dados com ate 10 acessos para leitura. ***");
		
//		gerenciamento das threads
		leitura = Executors.newFixedThreadPool(10);
		escrita = Executors.newCachedThreadPool();
		
//		threads de leitura
		for (int i = 0; i < 20; i++) {
			LerDB ler = new LerDB(db, mutex, rc);
			leitura.execute(ler);
		}
		
//		threads de inclusao, alteracao e exclusao
		for (int i = 0; i < 3; i++) {
			AdicionarDB escrever = new AdicionarDB(db, mutex);
			escrita.execute(escrever);
		}
		DeletarDB deletar = new DeletarDB(db, mutex);
		AtualizarDB atualizar = new AtualizarDB(db, mutex);
		escrita.execute(deletar);
		escrita.execute(atualizar);

		System.out.println(" *** FIM ***");
	}

	public static class AdicionarDB extends Thread {
		Semaphore db;
		Semaphore mutex;

		public AdicionarDB(Semaphore db, Semaphore mutex) {
			super();
			this.db = db;
			this.mutex = mutex;
		}

		@Override
		public void run() {
			while (true) {
				tempoAleatorioEscrita();
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

	public static class LerDB extends Thread {
		public LerDB(Semaphore mutex, Semaphore db, int rc) {
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
					tempoAleatorioLeitura();
					mutex.acquire();
					this.rc = this.rc + 1;
					if (this.rc == 1) {
						this.db.acquire();
					}
					this.mutex.release();
					if (lista.size() > 0) {
						System.out.printf(Thread.currentThread().getName() + "\n Lendo ==> %s \n",
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

	public static class DeletarDB extends Thread {
		Semaphore db;
		Semaphore mutex;

		public DeletarDB(Semaphore db, Semaphore mutex) {
			super();
			this.db = db;
			this.mutex = mutex;
		}

		@Override
		public void run() {
			while (true) {
				String removido = "";
				tempoAleatorioEscrita();
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

	public static class AtualizarDB extends Thread {
		Semaphore db;
		Semaphore mutex;

		public AtualizarDB(Semaphore db, Semaphore mutex) {
			super();
			this.db = db;
			this.mutex = mutex;
		}

		@Override
		public void run() {
			while (true) {
				String anterior = "";
				String atual = "";
				tempoAleatorioEscrita();
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

	public static void tempoAleatorioLeitura() {
//		System.out.println(Thread.currentThread().getName() + " ... dormindo ...");
		try {
			Thread.sleep((long) (Math.random() * 3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public static void tempoAleatorioEscrita() {
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

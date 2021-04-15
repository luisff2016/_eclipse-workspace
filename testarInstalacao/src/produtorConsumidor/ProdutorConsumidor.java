package produtorConsumidor;

public class ProdutorConsumidor {

	static Produto prod = new Produto();

	public static void main(String[] args) throws InterruptedException {
	
		Produtor p1 = new Produtor(prod);
		Produtor p2 = new Produtor(prod);
		p1.start();
		p2.start();

		Consumidor c1 = new Consumidor(prod);
		Consumidor c2 = new Consumidor(prod);
		c1.start();
		c2.start();

		p1.join();
		p2.join();
		c1.join();
		c2.join();
		System.out.println("FIM*********************");

	}

	public static class Consumidor extends Thread {
		Produto produto;

		Consumidor(Produto produto) {
			this.produto = produto;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				if (produto.getQtd() > 0)
					produto.consumir(this.getName());

			}
		}

	}

	public static class Produto {
		public int quantidade;

		Produto() {
			this.quantidade = 1;
		}

		public synchronized int getQtd() {
			return quantidade;
		}

		public synchronized void produzir(String idThread, int valor) {
			this.quantidade = valor;
			System.out.println("Produtor: " + idThread + "\t Estoque: " + this.quantidade);
		}

		public synchronized void consumir(String idThread) {
			this.quantidade--;
			System.out.println("Consumidor: " + idThread + "\t Estoque: " + this.quantidade);
		}

	}

	public static class Produtor extends Thread {

		Produto produto;

		Produtor(Produto produto) {
			this.produto = produto;
		}

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				produto.produzir(this.getName(), i);
			}
			try {
				sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

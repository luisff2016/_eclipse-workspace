package atv_Thread;

import java.util.Scanner;

public class Exercicio1 {
	
	public static void main(String[] args) throws InterruptedException {
		new Exercicio1();
	}

	public Exercicio1() throws InterruptedException {

		Scanner ler = new Scanner(System.in);
		System.out.println("*** Leitura de um vetor de inteiros ***");
		int n = 10; // tamanho do vetor de inteiros
		int v[] = new int[n]; // declaração do vetor "v"
		int i; // índice ou posição
		boolean repetir = true;

		while (repetir) {
			System.out.println("Digitar os valores para ordenar:");
			// Entrada de valores no vetor
			for (i = 0; i < n; i++) {
				System.out.printf("Informe %2dº valor de %d: ", (i + 1), n);
				v[i] = ler.nextInt();
			}
			// usando Thread
			ThreadEx1 threadA = new ThreadEx1("Thread de Ordenação", v);
			threadA.start();

			System.out.println("**** Uma thread foi iniciada!");
			boolean a = true;
			while (a) {
				System.out.println(" Se deseja parar a operação digite 'S' ");
				String input = ler.next();
				if ("S".equals(input)) {
					a = false;
					repetir = false;
				} else {
					a = false;
				}

			}

		}
		ler.close();
		System.out.println("************FIM main");
	}

	public class ThreadEx1 extends Thread {
		private String nome;
		private int original[];

		public ThreadEx1(String nome, int v[]) {
			System.out.println();
			this.nome = nome;
			int tam = v.length;
			this.original = new int[tam];
			int ordenado[] = new int[tam];
			int i, j, menor, aux;
			for (i = 0; i < tam; i++) {
				original[i] = v[i];
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
				System.out.printf("v[%d] = %8d, %8d %n", i, original[i], ordenado[i]);
			}
		}

		@Override
		public void run() {
			System.out.printf("FIM da %s%n", this.nome);
		}
	}

}

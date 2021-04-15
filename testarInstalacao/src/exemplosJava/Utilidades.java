package exemplosJava;

import java.util.Calendar;
import java.util.Random;

public class Utilidades {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Aleatoriar: " + aleatoriar(3, 7));
		System.out.println("AleatoriarPeriodo: " + intervaloAleatorio(3, 7)*1000);

		System.out.printf("Tempo em milissegundos ==> %f \n", tempoAleatorio());
		System.out.println("Tempo em milissegundos ==> " + (Math.random()) * 10);
		System.out.println("Tempo em milissegundos ==> " + (Math.random()) * 10 + 1);
		System.out.println("Tempo em milissegundos ==> " + (Math.random()) * 10 + 3);
		System.out.println("Tempo em milissegundos ==> " + (Math.random()) * 10);

	}

	public static int aleatoriar(int minimo, int maximo) {
		Calendar lCDateTime = Calendar.getInstance();
		return (int) (lCDateTime.getTimeInMillis() % (maximo - minimo + 1) + minimo);
	}

	public static double tempoAleatorio() {
//		System.out.println(Thread.currentThread().getName() + " ... dormindo ...");

		return ((Math.random()) * 10);

	}

	public static int intervaloAleatorio(int min, int max) {
		Random aleatorio = new Random();
		int valor = aleatorio.nextInt((max - min) + 1) + min;
		return valor;
	}
}
// Logger.getLogger(Escritores.class.getName()).log(Level.SEVERE, null, ex);

/*
 * //Sem·foro mutex, e initial (1, 1) Sem·foro mutex, e
 * 
 * initial (1, 1) Integer nl initial 0 Integer ne initial 0 Sem·foro mx, l
 * initial (1,1) P(l) P(mx) P(l) P(mutex)nl := nl+1 if nl = 1 then{ P(e)
 * V(mutex) P(mx) ne := ne+1 } if ne = 1 then { P(l) V(mx) V(mutex)
 * V(l)LÍP(e)Escreve EscritorP(mutex)nl := nl-1if nl = 0
 * 
 * then V(e)V(mutex)V(e)P(mx)ne := ne-1LeitorV(mutex)ne := ne-1if ne = 0
 * 
 * then V(l)V(mx) }
 */

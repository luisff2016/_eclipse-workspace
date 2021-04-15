package atv_Thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Exercicio2 {
	
	public static void main(String[] args) throws InterruptedException {
		new Exercicio2();
	}

	public Exercicio2() throws InterruptedException {

		ThreadEx2 threadA = new ThreadEx2("Thread-A", atualizaHoras());

		ThreadEx2 threadB = new ThreadEx2("Thread-B", atualizaHoras());

		ThreadEx2 threadC = new ThreadEx2("Thread-C", atualizaHoras());

		threadA.start();

		threadB.start();

		threadC.start();

		System.out.println(" **** Alguma thread foi executada! **** ");
		System.out.printf(" %s ", threadA.getName());

		Thread.sleep(100);
		threadA.join();
		threadB.join();
		threadC.join();

		System.out.println(" ************ FIM main ************ ");
	}

	private String atualizaHoras() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");
		return hora.format(calendar.getTime());
	}

	

	public class ThreadEx2 extends Thread {
		private String nome;
		private String horario;

		public ThreadEx2(String nome, String horario) {
			this.nome = nome;
			this.setHorario(horario);
		}

		public String getHorario() {
			return horario;
		}

		public void setHorario(String horario) {
			this.horario = horario;
		}

		@Override
		public void run() {
			System.out.printf("Olá, sou a %s! Iniciando tarefa às %s %n", this.nome, atualizaHoras());
			for (int i = 0; i < 10; i++) {

				System.out.printf("%s: Contador da %s = %d %n", getHorario(), this.nome, i);
				setHorario(atualizaHoras());
				System.out.println();

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println();
			System.out.printf("%s : FIM da %s %n", atualizaHoras(), this.nome);
			System.out.println();
		}

		private String atualizaHoras() {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");
			return hora.format(calendar.getTime());
		}
	}

}

package exemplosJava;

public class UnivespSOaula07_Thread {

	public static void main(String[] args) {
		System.out.println("Inicio da thread principal: " + Thread.currentThread().getName());
		Thread thread = new ThreadSimples();
		thread.start();
		try {
			Thread.sleep(1000);
//			tempo em milisegundos
			thread.join();
			
		} catch (InterruptedException e) {
//			Precisa capturar InterruptedException			
			e.printStackTrace();
		}
		
		System.out.println("Fim da thread principal: " + Thread.currentThread().getName());
	}

}

class ThreadSimples extends Thread {
	@Override
	public void run() {
		System.out.println("Ola de sua nova thread: " + Thread.currentThread().getName());
	}
}

package exemplosJava;

public class ExemploThreadRunnable {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Inside : " + Thread.currentThread().getName());

		System.out.println("Creating Runnable...");
		Runnable runnable = new MyRunnable();

		// criando uma classe anonima
		Runnable runnableAnonymous = new Runnable() {
			@Override
			public void run() {
				System.out.println("Inside : " + Thread.currentThread().getName());
			}
		};

		System.out.println("Creating Thread...");
		Thread thread = new Thread(runnable);
		Thread thread2 = new Thread(runnableAnonymous);

		System.out.println("Starting Thread...");
		thread.start();
		thread2.start();

		thread.join();
		thread2.join();

		System.out.println(" *** F I M ***");

	}

	public static class MyRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("Inside : " + Thread.currentThread().getName());
		}
	}

}

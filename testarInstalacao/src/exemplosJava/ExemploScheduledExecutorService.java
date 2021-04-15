package exemplosJava;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExemploScheduledExecutorService {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			System.out.println("Executing Task At " + System.nanoTime());
		};
		
//        Executa com um atraso de 5 segundos
		System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
		scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);

//		  Executa com um atraso de 10 segundos e repete a cada 2 segundos ate ser parada
		System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
		scheduledExecutorService.scheduleAtFixedRate(task, 10, 2, TimeUnit.SECONDS);

//		  Para executar com repeticao nao pode usar o shutdown()
		scheduledExecutorService.shutdown();

		System.out.println(" *** F I M ***");
	}

}

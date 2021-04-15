package exemplosJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExemploExecutorService {

	public static void main(String[] args) {
		System.out.println("Inside : " + Thread.currentThread().getName());

		System.out.println("Creating Executor Service...");
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		System.out.println("Creating a Runnable...");
		Runnable runnable = () -> {
			System.out.println("Inside : " + Thread.currentThread().getName());
		};

		System.out.println("Submit the task specified by the runnable to the executor service.");
		executorService.submit(runnable);

//        ExecutorService fornece dois métodos para desligar um executor -

//        shutdown () - quando o shutdown()método é chamado em um serviço do executor, ele para de aceitar novas tarefas, espera a execução de tarefas enviadas anteriormente e, em seguida, encerra o executor.

//        shutdownNow () - este método interrompe a tarefa em execução e desliga o executor imediatamente.

		System.out.println("Shutting down the executor");
		executorService.shutdown();

		System.out.println(" *** F I M ***");
	}

}

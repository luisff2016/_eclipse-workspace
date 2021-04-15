package exemplosJava;

public class ExemploThread {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating thread...");
        Thread thread = new MyThread();
//        criar um thread com um nome personalizado usando o Thread(String name)

        System.out.println("Starting thread...");
        thread.start();
    }
    
    public static class MyThread extends Thread{
		// TODO Auto-generated constructor stub
		// run() method contains the code that is executed by the thread.
	    @Override
	    public void run() {
	        System.out.println("Inside : " + Thread.currentThread().getName());
	    }
	}

    
    

}



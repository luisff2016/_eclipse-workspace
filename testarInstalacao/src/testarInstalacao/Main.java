package testarInstalacao;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Testar instalacao: Oi Eclipse");

		int i = (int) (Math.random() * 10 / Math.nextDown(1.0));
		double f = (Math.random() * 10 / Math.nextDown(1.0));
		double x1 = 1;
		double x2 = 2;
		double x = x1 * (1.0 - f) + x2 * f;

		System.out.println("i= " + i + "\nf= " + f + "\nx1= " + x1 + "\nx2= " + x2 + "\nx= " + x);
		System.out.println("Math.random() = " + Math.random());
		System.out.println("Math.random() * 10 = " + Math.random() * 10);

		System.out.println("Math.random() * 100 = " + Math.random() * 100);
		System.out.println("(int) (Math.random() * 100) = " + (int) (Math.random() * 100));

		System.out.println("Math.nextDown(1.0) = " + Math.nextDown(1.0));
		System.out.println("Math.nextDown(1.0) * 10 = " + Math.nextDown(1.0) * 10);
		System.out.println("Math.nextDown(1.0) * 100 = " + Math.nextDown(1.0) * 100);

	}

}

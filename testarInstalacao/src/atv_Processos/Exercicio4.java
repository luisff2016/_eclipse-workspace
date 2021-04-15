package atv_Processos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Exercicio4 {
	static int tam = 10;

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("*** Comunicação entre processos: Exercicio3 ***");
		
		// pasta de teste => C:\Users\ret2i\Desktop\ConverterColor
		new Exercicio4();
	}

	public Exercicio4() throws InterruptedException, IOException {
		System.out.println(" **** Alguma thread foi executada! **** ");
		
		validarDiretorio();
		
		System.out.println(" ************ FIM main ************ ");
	}

	public static void validarDiretorio() throws IOException {
		// cria um Scanner para obter entrada a partir da janela de comando
		Scanner input = new Scanner(System.in);
		
		boolean valid = true;
		
		System.out.println(" **** TESTE: antes de while! **** ");
		
		while (valid) {
			System.out.println("Digite o diretorio para a pasta com as imagens para conversão: ");
			// cria o objeto Path com base na entrada de usuário
		
			File file = new File(input.nextLine());
			
			System.out.println("# Diretorio digitado ==> " + file.getAbsolutePath() + "\n" + file.getName());
			
			if (file.exists()) // se o caminho existe, gera uma saída das informações sobre ele
			{
				valid = false;
			
				if (file.isDirectory()) {
					// objeto para iteração pelo conteúdo de um diretório
				
					File farqs[] = file.listFiles();
					
					for (File f : farqs) {
					
						if (f.isFile()) {
						
							BufferedImage img = ImageIO.read(new File(f.toString()));
							
							ThreadEx3 foto1 = new ThreadEx3(img, 0, img.getHeight() / 2);
							
							ThreadEx3 foto2 = new ThreadEx3(img, img.getWidth() / 2, 0);
							
							ThreadEx3 foto3 = new ThreadEx3(img, img.getWidth() / 2, img.getHeight() / 2);
							
							ThreadEx3 foto4 = new ThreadEx3(img, img.getWidth(), img.getHeight());
							
							Thread ft1 = new Thread(foto1);
							
							Thread ft2 = new Thread(foto2);
							
							Thread ft3 = new Thread(foto3);
							
							Thread ft4 = new Thread(foto4);
							
							ft1.start();
							
							ft2.start();
							
							ft3.start();
							
							ft4.start();
							
							System.out.println(f.getName());
							
							if (f.getName().endsWith(".jpg")) {
								System.out.println("teste");
							}
						}
					}
				}
			} else // se não for arquivo ou diretório, gera saída da mensagem de erro
			{
				System.out.printf("%n Não é um arquivo valido! %n");
			}
		}
		input.close();
	}

	public static BufferedImage converterImagem(BufferedImage originalImage, int targetWidht, int targetHeight) {
		Image resultingImage = originalImage.getScaledInstance(targetWidht, targetHeight, Image.SCALE_DEFAULT);
		
		BufferedImage outputImage = new BufferedImage(targetWidht, targetHeight, BufferedImage.TYPE_INT_RGB);
		
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		
		return outputImage;
	}

	public static class ThreadEx3 extends Thread {
		BufferedImage imagem;
		
		int altura;
		
		int largura;

		public ThreadEx3(BufferedImage img, int alt, int larg) throws IOException {
			imagem = img;
		
			altura = alt;
			
			largura = larg;
		}

		@Override
		public void run() {
			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		
			
			converterImagem(imagem, altura, largura);
			
			System.out.printf("Thread: " + Thread.currentThread().getName());
			
		
		}
	}
}

/*
 * BufferedImage img = null;
 * 
 * img = ImageIO.read(new File("D:\\work\\files\\logo.jpg"))
 */
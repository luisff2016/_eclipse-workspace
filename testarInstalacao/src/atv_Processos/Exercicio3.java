package atv_Processos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

public class Exercicio3 {
	static ExecutorService executorService = Executors.newFixedThreadPool(4);

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("*** Comunicação entre processos: Exercicio3 ***");

		// pasta de teste => E:\ConverterColor
		new Exercicio3();
	}

	public Exercicio3() throws InterruptedException, IOException {
		validarDiretorio();
		System.out.println(" *** FIM da thread ==> " + Thread.currentThread().getName());
	}

	public static void validarDiretorio() throws IOException {
		// cria um Scanner para obter entrada a partir da janela de comando
		Scanner input = new Scanner(System.in);
		boolean valid = true;
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
							String novoNome = f.getAbsolutePath();
							System.out.println(novoNome);
							// reduzir em 35% e mudar a extencao para gif
							novoNome = novoNome.substring(0, novoNome.length() - 4) + "_reduzidaEm35%.gif";
							File newFile = new File(novoNome);
							System.out.println(newFile.getName());
							BufferedImage img = ImageIO.read(f);
							Runnable runnable = new MyRunnable(img, img.getWidth(), img.getHeight(), newFile);
							executorService.submit(runnable);
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

	public static class MyRunnable implements Runnable {
		BufferedImage imagem;
		int altura;
		int largura;
		File arquivo;

		public MyRunnable(BufferedImage img, int alt, int larg, File arq) throws IOException {
			imagem = img;
			altura = alt;
			largura = larg;
			arquivo = arq;
		}

		@Override
		public void run() {
			imagem = converterImagem(imagem, (int) (altura * 0.35), (int) (largura * 0.35));
			try {
				ImageIO.write(imagem, "jpg", arquivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			Para verificar a execucao das threads no pool
//			System.out.println("Inside : " + Thread.currentThread().getName());
		}
	}
}
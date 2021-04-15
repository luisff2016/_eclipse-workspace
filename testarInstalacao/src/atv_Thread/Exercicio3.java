package atv_Thread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Exercicio3 {

	public static void main(String[] args) throws InterruptedException, IOException {
		// pasta de teste => C:\Users\ret2i\Desktop\ConverterColor
		new Exercicio3();

	}

	public Exercicio3() throws InterruptedException, IOException {
		validarDiretorio();
		Path novoDir = Paths.get("C:\\Users\\ret2i\\Desktop\\ConverterPB");
		System.out.println("novoDir");
		System.out.println(novoDir);

		System.out.println(" **** Alguma thread foi executada! **** ");

		System.out.println(" ************ FIM main ************ ");
	}

	public static void validarDiretorio() throws IOException {

		/*
		 * String teste =
		 * "E:\\OneDrive\\Documentos\\_UFS_FERNANDO\\2020_1\\SistemasOperacionais\\_AulaThreads\\Atividade_Thread_Ex3";
		 * File file = new File(teste); converterImagem(file);
		 */
		// cria um Scanner para obter entrada a partir da janela de comando
		Scanner input = new Scanner(System.in);
		boolean valid = true;
		while (valid) {
			System.out.println("Digite o diretorio para a pasta com as imagens para conversão: ");
			// cria o objeto Path com base na entrada de usuário
			File file = new File(input.nextLine());
			System.out.println("# Diretorio digitado ==> " + file.getAbsolutePath());
			if (file.exists()) // se o caminho existe, gera uma saída das informações sobre ele
			{
				valid = false;
				if (file.isDirectory()) {
					// objeto para iteração pelo conteúdo de um diretório
					File farqs[] = file.listFiles();

					for (File f : farqs) {
						System.out.println(f);
						if (f.getName().endsWith(".jpg"))
							converterImagem(f);

					}
				}
			} else // se não for arquivo ou diretório, gera saída da mensagem de erro
			{
				System.out.printf("%n Não é um arquivo valido! %n");
			}
		}
		input.close();
	}

	public static void converterImagem(File f) {
		System.out.println("converterImagem: " + f.getName());
		BufferedImage bufferedImage;
		String newFile = f.toString().toUpperCase();
		File file = new File(newFile);
		try {
			bufferedImage = ImageIO.read(file);
			for (int i = 0; i < bufferedImage.getWidth(); i++) {
				for (int j = 0; j < bufferedImage.getHeight(); j++) {
					Color pixelColor = new Color(bufferedImage.getRGB(i, j));
					int blackAndWhiteNumber = (int) (pixelColor.getRed() * 0.3 + pixelColor.getGreen() * 0.59
							+ pixelColor.getBlue() * 0.11);
					Color grayScalePixel = new Color(blackAndWhiteNumber, blackAndWhiteNumber, blackAndWhiteNumber);
					bufferedImage.setRGB(i, j, grayScalePixel.getRGB());
				}

			}
			System.out.println(file.getName());

			ImageIO.write(bufferedImage, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public class ThreadEx3 extends Thread {
		private String nome;
		private String horario;

		public ThreadEx3(String nome, String horario) {
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
			for (int i = 0; i < 100; i++) {

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

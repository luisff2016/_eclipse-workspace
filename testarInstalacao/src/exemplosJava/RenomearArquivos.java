package exemplosJava;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RenomearArquivos {
	public static void main(String[] args) throws IOException {
		System.out.println("*** inicio ***");

		File diretorio = new File("E:\\ConverterColor");
		File[] arquivos = diretorio.listFiles();

		for (File arquivo : arquivos) {
			BufferedImage bi = null;
			bi = ImageIO.read((File) arquivo);
			System.out.println("arquivo=" + arquivo.getName());
			String nome;
			String extencao = ".jpg";
			nome = arquivo.getName();
			System.out.println("nome=" + nome);
			nome = nome.toLowerCase();
//			System.out.println("minusculo=" + nome);
			nome = nome.substring(0, nome.indexOf(extencao));
			nome = "E:\\teste\\" + nome + ".gif";
			System.out.println("caminho = " + nome);
			// nome = cortarTrecho(nome, "#jpg");
			// System.out.println("cortar="+nome);
//			String primeira = nome.substring(0, 1).toUpperCase();
//			System.out.println("Primeira=" + primeira);
//			String restante = nome.substring(1);
			String restante = nome.substring(0, nome.length() - 4);
			System.out.println("Restante=" + restante);
//			nome = primeira + restante + ".gif";
//			System.out.println("Primeira + Restante = " + nome);

//			while (nome.contains(" ")) {
//
//				String nomeFinal = "";
//				String partes[] = nome.split("\\s+");
//
//				for (int i = 0; i < partes.length; i++) {
//					nomeFinal += "*" + partes[i].substring(0, 1).toUpperCase() + partes[i].substring(1).toLowerCase();
//				}
//				nomeFinal = nomeFinal.substring(1) + extencao;
//				nome = nomeFinal;
//				System.out.println("While ==> " + nome);
//
//			}
//			nome = nome.replace("_", "*");

			System.out.println("Nome Final==> " + nome);
			File newFile = new File(nome);
			// boolean ok = arquivo.renameTo(newFile);
			System.out.println("NewFile = " + newFile.getName());

			ImageIO.write(bi, "gif", newFile);

			System.out.println("Arquivo Final==> " + arquivo.getName());

		}
		System.out.println("*** FIM ***");
	}

	public static String cortarTrecho(String nome, String trecho) {

		int index = nome.indexOf(trecho);
		nome = nome.substring(0, index);
		return nome;

	}

}

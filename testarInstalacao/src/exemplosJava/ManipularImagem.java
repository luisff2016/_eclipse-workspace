package exemplosJava;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ManipularImagem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static BufferedImage setImagemDimensao(String caminhoImg, Integer imgLargura, Integer imgAltura) {
		Double novaImgLargura = null;
		Double novaImgAltura = null;
		Double imgProporcao = null;
		Graphics2D g2d = null;
		BufferedImage imagem = null, novaImagem = null;
		try {
			imagem = ImageIO.read(new File(caminhoImg));

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
//			Logger.getLogger(ManipulaImagem.class.getName()).log(Level.);

		}
		novaImgLargura = (double) imagem.getWidth();
		novaImgAltura = (double) imagem.getHeight();

		if (novaImgLargura >= imgLargura) {
			imgProporcao = (novaImgAltura / novaImgLargura);
			novaImgLargura = (double) imgLargura;
			novaImgAltura = (novaImgLargura * imgProporcao);

			while (novaImgAltura > imgAltura) {
				novaImgLargura = (double) (--imgLargura);
				novaImgAltura = (novaImgLargura * imgProporcao);
			}
		} else if (novaImgAltura >= imgAltura) {
			imgProporcao = (novaImgLargura / novaImgAltura);
			novaImgAltura = (double) imgAltura;
			novaImgAltura = (novaImgLargura * imgProporcao);

			while (novaImgLargura > imgLargura) {
				novaImgAltura = (double) (--imgAltura);
				novaImgLargura = (novaImgAltura * imgProporcao);
			}
		}

		novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
		g2d = novaImagem.createGraphics();
		g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

		return novaImagem;
	}

	public static byte[] getImgBytes(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "JPEG", baos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return baos.toByteArray();
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidht, int targetHeight) {
		Image resultingImage = originalImage.getScaledInstance(targetWidht, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidht, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

		return outputImage;
	}

}

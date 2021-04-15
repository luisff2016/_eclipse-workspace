package exemplosJava;

//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExBufferedImage {

	private static String decodeImage(File image) throws IOException {
		BufferedImage bufferedImage = null;
		bufferedImage = ImageIO.read(image);
		ImageIO.write(bufferedImage, "jpg", new File("e:\\teste\\teste.jpg"));
		ImageIO.write(bufferedImage, "gif", new File("e:\\teste\\teste.gif"));
		ImageIO.write(bufferedImage, "png", new File("e:\\teste\\teste.png"));
		ImageIO.write(bufferedImage, "png", new File(image.getAbsolutePath()));
		
		return bufferedImage.toString();
	}

	public static void main(String[] args) {
		try {
			File file = new File("E:\\ConverterColor\\teste.jpg");
			String decodedText = decodeImage(file);
			if (decodedText == null) {
				System.out.println("Imagem invalida!");
			} else {
				System.out.println("bufferedImage.getPropertyNames() = " + decodedText);
			}
		} catch (IOException e) {
			System.out.println("IOException :: " + e.getMessage());
		}
		System.out.println("*** FIM ***");
	}
}
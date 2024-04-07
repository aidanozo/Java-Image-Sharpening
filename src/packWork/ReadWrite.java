package packWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Aceasta interfata va fi implementata de catre doua clase, astfel incat sa includ conceptul de polimorfism.

public interface ReadWrite {
	public BufferedImage readImage(String ImageName);
	public BufferedImage writeImage(BufferedImage img, String Name);
}

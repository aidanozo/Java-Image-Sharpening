package packWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IOImageMirrored extends Sharpening implements ReadWrite {
	
	{
		  System.out.println("Poza a fost citita si inversata cu succes!");
	}
	
	public IOImageMirrored() {
	      image = null;
	  }

	public IOImageMirrored(BufferedImage img) {
	      this.image = img;
	  }
	
	// Aceasta este o alternativa de implementare pentru metoda readImage:
	// (Nu doar ca citeste imaginea, dar o si inverseaza, returnand astfel imaginea oglindita.)
	
	public BufferedImage readImage(String ImageName) {
		
	    File imageFile = new File(ImageName);
	    BufferedImage originalImage = null;

	    try {
	        originalImage = ImageIO.read(imageFile);

	        if (originalImage != null) {
	            int width = originalImage.getWidth();
	            int height = originalImage.getHeight();

	            BufferedImage mirroredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	            // Se parcurge fiecare pixel de la dreapta la stanga si de sus in jos:
	            for (int y = 0; y < height; y++) {
	                for (int x = width - 1; x >= 0; x--) {
	                	
	                    // Obtinem valoarea pixelului din imaginea originala:
	                    int pixel = originalImage.getRGB(x, y);
	                    
	                    // Setam valoarea pixelului in imaginea oglindita:
	                    mirroredImage.setRGB(width - 1 - x, y, pixel);
	                }
	            }

	            return mirroredImage;
	        }
	    } catch (IOException error) {
	        error.printStackTrace();
	    }

	    return null;
	}
	
	  public BufferedImage writeImage(BufferedImage img,String Name) {
	      try {
	          File outputfile = new File(Name);
	          ImageIO.write(img, "bmp", outputfile);
	      }
	      catch(IOException error) {
	          error.printStackTrace();

	      }
	      return img;
	  }
}

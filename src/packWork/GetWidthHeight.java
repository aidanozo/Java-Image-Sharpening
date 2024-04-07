package packWork;
import java.awt.image.BufferedImage;

public class GetWidthHeight extends GetWidthHeightAbstract {
	
    private int width;
    private int height;
    
    // Constructor implicit
    public void GetWidthHeight() {
        this.width = 0;
        this.height = 0;
    }
    
    // Constructor cu parametri
    public void GetWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Metoda de acces (getter) pentru width
    public int getWidth() {
        return this.width;
    }

    // Metoda de acces (getter) pentru height
    public int getHeight() {
        return this.height;
    }

    // Metoda pentru calculul latimii
    public int calculateWidth(BufferedImage img) {
        if (img != null) {
            int[] rgb = new int[3];

            // Parcurgem coloanele imaginii.
            for (int x = 0; x < img.getWidth(); x++) {
            	// Obtinem valorile de culoare ale pixelului (x,0) si le stocam in array-ul `rgb`.
                img.getRaster().getPixel(x, 0, rgb);

                // Ne asiguram ca avem un pixel valid verificand daca avem 3 valori nenule.
                if (rgb.length >= 3 && rgb[0] >= 0 && rgb[1] >= 0 && rgb[2] >= 0) {
                    this.width = x + 1;
                }
            }
        } else {
            System.out.println("Imaginea este null.");
        }

        return this.width;
    }

    // Metoda pentru calculul inaltimii (asemanatoare cu metoda anterioara)
    public int calculateHeight(BufferedImage img) {
        if (img != null) {
            int[] rgb = new int[3];

            for (int y = 0; y < img.getHeight(); y++) {
                img.getRaster().getPixel(0, y, rgb);

                if (rgb.length >= 3 && rgb[0] >= 0 && rgb[1] >= 0 && rgb[2] >= 0) {
                    this.height = y + 1;
                }
            }
        } else {
            System.out.println("Imaginea este null.");
        }

        return this.height;
    }
}

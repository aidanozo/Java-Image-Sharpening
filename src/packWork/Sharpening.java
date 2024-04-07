package packWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sharpening extends GetWidthHeight {
    BufferedImage image;
    
    // Introducem un bloc non-static de initializare.
    {
    	System.out.println("Poza se prelucreaza...");
    }
    
    public Sharpening() {
        image = null;
    }

    public Sharpening(BufferedImage img) {
        this.image = img;
    }

    // Metoda care aplica masca/mastile de convolutie:
    public BufferedImage applyConvolutionMask(BufferedImage image, int[][]... masks) // Al doilea parametru utilizeaza varargs.
    {
    	long startTime = System.currentTimeMillis();
    	
        int width = calculateWidth(image);
        int height = calculateHeight(image);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Parcurgem fiecare pixel din imagine
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0, sumAlpha = 0;

                // Parcurgem fiecare pixel din jurul pixelului curent
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixelX = x + i;
                        int pixelY = y + j;

                        // Verificam daca pixelul este in interiorul imaginii
                        if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
                            int pixel = image.getRGB(pixelX, pixelY);
                            int alpha = (pixel >> 24) & 0xff;
                            int red = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                            int blue = pixel & 0xff;

                            // Aplicam masca de convolutie pe fiecare canal de culoare
                            for (int[][] mask : masks) {
                                sumRed += red * mask[i + 1][j + 1];
                                sumGreen += green * mask[i + 1][j + 1];
                                sumBlue += blue * mask[i + 1][j + 1];
                                sumAlpha += alpha * mask[i + 1][j + 1];
                            }
                        }
                    }
                }

                // Calculam valorile finale ale canalelor de culoare
                int alpha = Math.min(255, Math.max(0, sumAlpha));
                int red = Math.min(255, Math.max(0, sumRed));
                int green = Math.min(255, Math.max(0, sumGreen));
                int blue = Math.min(255, Math.max(0, sumBlue));

                // Setam pixelul in imaginea rezultat
                int pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                result.setRGB(x, y, pixel);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Procesare: " + (endTime - startTime) + " ms");
        
        return result;
    }
}

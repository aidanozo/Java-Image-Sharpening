package packTest;

import packWork.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.Scanner;

public class MyMain {
 public static void main(String[] args) throws IOException {
     
	 // Se creeaza 3 instante ale clasei IOImage.
	 
	 IOImage inputImage1 = new IOImage();
     inputImage1.readImage("dogDuck.bmp");
     
     IOImage inputImage2 = new IOImage();
     inputImage2.readImage("dog.bmp");
     
     IOImage inputImage3 = new IOImage();
     inputImage3.readImage("chefDog.bmp");
     
     // Urmatoarele matrici reprezinta mastile de convolutie utilizate.
     
     int[][] mask1 = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}}; // Sharpen Filter
     int[][] mask2 = {{-2, -1, 0}, {-1, 1, 1}, {0, 1, 2}}; // Emboss Filter
     
     // Citesc un numar de la tastatura. Acesta va reprezenta masca aplicata primei imagini (masca 1 sau masca 2).
     
     
     Scanner scanner = new Scanner(System.in);
     System.out.print("Introduceti un numar (1 sau 2). Acest numar reprezinta masca aplicata imaginii dogDuck.bmp: ");
     while (!scanner.hasNextInt()) {
         System.out.print("Introduceti un numar valid (1 sau 2): ");
         scanner.next();
     }
     int x = scanner.nextInt();
     if (x == 1 || x == 2) {
         System.out.println("Se va aplica masca cu numarul " + x + ".");
     } else {
         System.out.println("Numarul introdus nu este 1 sau 2.");
     }
    
     
     int[][] mask = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
     
     if (x == 1 )
     {
    	 for ( int i = 0 ; i < 3 ; i++)
    		 for ( int j = 0 ; j < 3 ; j++)
    			 mask[i][j] = mask1[i][j];
     }
     else
     {
    	 for ( int i = 0 ; i < 3 ; i++)
    		 for ( int j = 0 ; j < 3 ; j++)
    			 mask[i][j] = mask2[i][j];
     }
     
     // Utilizam metodele implementate pentru a citi, prelucra si scrie urmatoarele imagini.
     
     Scanner scanner1 = new Scanner(System.in);
     
     System.out.println("Introdu numele primei imagini: ");
     String numeImagine1 = scanner1.nextLine() + ".bmp";
     System.out.println("Introdu numele imaginii modificate: ");
     String imagineModificata1 = scanner1.nextLine() + ".bmp";

     File file1 = new File(numeImagine1);
     
     if (file1.exists()) {
         IOImage image1 = new IOImage();
         image1.writeImage(image1.applyConvolutionMask(image1.readImage(numeImagine1), mask),imagineModificata1);
     } else {
         System.out.println("Imaginea nu exista. Te rog să introduci un nume de fișier valid.");
     }
     
     //inputImage1.writeImage(inputImage1.applyConvolutionMask(inputImage1.readImage("dogDuck.bmp"), mask),"dogDuck_transformed.bmp");
     
     
     System.out.println("Introdu numele celei de-a doua imagini: ");
     String numeImagine2 = scanner1.nextLine() + ".bmp";
     System.out.println("Introdu numele imaginii modificate: ");
     String imagineModificata2 = scanner1.nextLine() + ".bmp";

     File file2 = new File(numeImagine2);
     
     if (file2.exists()) {
         IOImage image2 = new IOImage();
         image2.writeImage(image2.applyConvolutionMask(image2.readImage(numeImagine2), mask1, mask2),imagineModificata2);
     } else {
         System.out.println("Imaginea nu exista. Te rog să introduci un nume de fișier valid.");
     }
     
     
     //inputImage2.writeImage(inputImage2.applyConvolutionMask(inputImage2.readImage("dog.bmp"), mask1, mask2),"dog_sharpened_embossed.bmp");
     
     
     System.out.println("Introdu numele celei de-a treia imaginii: ");
     String numeImagine3 = scanner1.nextLine() + ".bmp";
     System.out.println("Introdu numele imaginii modificate: ");
     String imagineModificata3 = scanner1.nextLine() + ".bmp";

     File file3 = new File(numeImagine3);
     
     if (file3.exists()) {
         IOImage image3 = new IOImage();
         image3.writeImage(image3.applyConvolutionMask(image3.readImage(numeImagine3), mask1),imagineModificata3);
     } else {
         System.out.println("Imaginea nu exista. Te rog să introduci un nume de fișier valid.");
     }

     
     //inputImage3.writeImage(inputImage3.applyConvolutionMask(inputImage3.readImage("chefDog.bmp"), mask1),"dog_sharpened.bmp");
     
     scanner1.close();
     
     
     // Utilizam metoda alternativa pentru readImage din cadrul clasei IOImage pentru a afisa imaginea oglindita si filtrata.
     
     
     //// Se pot face la fel cu alegerea pozei din linia de comanda.
     
     IOImageMirrored inputImage4 = new IOImageMirrored();
     inputImage4.readImage("dogDuck.bmp");
     
     inputImage4.writeImage(inputImage4.applyConvolutionMask(inputImage4.readImage("dogDuck.bmp"), mask1),"dogDuck_mirrored_sharpened.bmp");
     
     // Realizam procesarea cu ajutorul thread-urilor. 
     
     IOImage inputImage5 = new IOImage();
     inputImage5.procesareImaginiAsincron("chefDog.bmp");
 }
 
}
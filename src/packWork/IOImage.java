package packWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IOImage extends Sharpening implements ReadWrite{
	
  BufferedImage image;
  int[][] mask1 = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}}; // Sharpen Filter
  
  // Introducem un bloc static de initializare.
  static{
	  System.out.println("Pozele se citesc...");
  }
  
  public IOImage() {
      image = null;
  }

  public IOImage(BufferedImage img) {
      this.image = img;
  }
  
  // Metoda - citire:
  
  public BufferedImage readImage(String ImageName) {
	  
	  long startTime = System.currentTimeMillis(); // Se da start cronometrarii timpului de executie.
	  
      File image = new File(ImageName);
      BufferedImage img = null;
      try{
          img = ImageIO.read(image);
      }
      catch(IOException error) {
          error.printStackTrace();
      }
      
      long endTime = System.currentTimeMillis();
      System.out.println("Citire: " + (endTime - startTime) + " ms"); // Opreste si afiseaza cronometrarea.
      
      return img;
  }

  // Metoda - scriere:
  
  public BufferedImage writeImage(BufferedImage img,String Name) {
	  
	  long startTime = System.currentTimeMillis();
	  
      try {
          File outputfile = new File(Name);
          ImageIO.write(img, "bmp", outputfile);
      }
      catch(IOException error) {
          error.printStackTrace();

      }
      
      long endTime = System.currentTimeMillis();
      System.out.println("Scriere: " + (endTime - startTime) + " ms");
      
      return img;
  }
  
  // Alocarea noilor thread-uri:
  
  public void procesareImaginiAsincron(String numeImagine) {
	  
	  // Obiect de sincronizare:
      final Object lock = new Object();
      
      // Producer Thread:
      Thread producerThread = new Thread(() -> {
    	  synchronized (lock) {
              BufferedImage img = readImage(numeImagine);
              image = img;
              System.out.println("Imaginea a fost citita.");
              
              // Consumer thread este anuntat ca imaginea a fost citita:
              lock.notify(); 
              
              System.out.println("Notificarea a fost trimisa.");
              
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      });

      // Consumer Thread:
      Thread consumerThread = new Thread(() -> {
    	  synchronized (lock) {
    		  try {
                  
                  System.out.println("Se asteapta notificarea.");
                  // Adaug o intarziere de 1000 milisecunde:
                  Thread.sleep(1000);

                  // Asteapta ca Producer Thread sa citeasca imaginea:
                  lock.wait();
                  
                  System.out.println("Consumatorul a primit notificarea.");
                  
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              // Se verifica daca imaginea a fost citita:
              if (image != null) {
            	  
                  System.out.println("Imaginea a fost preluata de catre consumator.");

                  // Se aplica masca de convolutie:
                  image = applyConvolutionMask(image, mask1);

              } else {
                  System.out.println("Imaginea nu a fost citita inca.");
              }
          }
      });

      consumerThread.start();
      producerThread.start();

      try {
          producerThread.join();
          consumerThread.join();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      // Se incepe procesarea:
      System.out.println("Incepe procesarea imaginii.");

      // Se salveaza imaginea procesata:
      writeImage(image, "ImagineModificataThread.bmp");
  }
}
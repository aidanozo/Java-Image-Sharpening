package packWork;

import java.awt.image.BufferedImage;

// Aceasta clasa abstracta cu metode abstracte este extinsa de catre clasa GetWidthHeight.

public abstract class GetWidthHeightAbstract  {
	public abstract void GetWidthHeight();
	public abstract void GetWidthHeight(int width, int height);
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int calculateWidth(BufferedImage img);
	public abstract int calculateHeight(BufferedImage img);
}

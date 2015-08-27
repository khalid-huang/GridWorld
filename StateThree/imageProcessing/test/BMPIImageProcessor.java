import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class BMPIImageProcessor implements IImageProcessor
{
	public Image showChanelR(Image image) {
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new BMPImageFilter(BMPImageFilter.RED_SYMBOL)));
	}

	public Image showChanelG(Image image) 
	{
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new BMPImageFilter(BMPImageFilter.GREEN_SYMBOL)));
	}

	public Image showChanelB(Image image)
	{
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new BMPImageFilter(BMPImageFilter.BLUE_SYMBOL)));
	}

	public Image showGray(Image image) 
	{
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new GrayImageFilter()));
	}

}

class GrayImageFilter extends RGBImageFilter
{
	private static final int RED_ONLY = 0x00ff0000;
	private static final int GREEN_ONLY = 0x0000ff00;
	private static final int BLUE_ONLY = 0x000000ff;
	private static final double RED_FACTOR = 0.299;
	private static final double GREEN_FACTOR = 0.587;
	private static final double BLUE_FACTOR = 0.114;


	public GrayImageFilter() 
	{
		canFilterIndexColorModel = true;
	}

	public int filterRGB(int x, int y, int rgb)
	{
		int red = (rgb & RED_ONLY) >> 16;
		int green = rgb & GREEN_ONLY >> 8;
		int blue = (rgb & BLUE_ONLY);
		int l = (int)(red * RED_FACTOR + blue * BLUE_FACTOR + green * GREEN_FACTOR);
		int result = (rgb & 0xff000000) | (l << 16) | (l << 8) | l;
		return result;
	}
}

class BMPImageFilter extends RGBImageFilter
{
	private int symbol;
	public static final int RED_SYMBOL = 0xffff0000;
	public static final int GREEN_SYMBOL = 0xff00ff00;
	public static final int BLUE_SYMBOL = 0xff0000ff;
	public BMPImageFilter(int symbol)
	{
		this.symbol = symbol;
		canFilterIndexColorModel = true;
	}
  
	public int filterRGB(int x, int y, int rgb)
	{
		return rgb & symbol;
	}
}


import imagereader.IImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BMPIImageIO implements IImageIO
{
	//the size of the bitmap-file header
	private static final int FILE_HEADER_SIZE = 14;
	//the size of the bitmap-information header
	private static final int INFORMATION_HEADER_SIZE = 40;
	//two byte
	private static final int TWO_BYTE = 2;
	//four byte
	private static final int FOUR_BYTE = 4;
	//the size offset int fiel header
	private static final int SIZE_OFFSET = 2;
	//the biWidth offset
	private static final int WIDTH_OFFSET = 4;
	//the biHeight offset
	private static final int HEIGHT_OFFSET = 8;
	//the biBitCount offset
	private static final int BITCOUNT_OFFSET = 14;
	//each pixel take up byte
	private static final int RGB_COUNT = 3;
	//transparency
	private static final int ALPHA = 0xff000000;
	/** read the BMP from the filepath
	 **/
	public Image myRead(String filePath) throws IOException
	{
		int width, height, size, biBitCount, padding; //the BMP’s width and height;
		int pixels[];  //the BMP's pexels information;
		BufferedInputStream BfInStream = new BufferedInputStream(new FileInputStream(filePath));

		//get the size from the stream;
		byte[] fileHeader = new byte[FILE_HEADER_SIZE]; //the  fileHeader is 14 byte;
		BfInStream.read(fileHeader, 0, FILE_HEADER_SIZE);
		size = readBytes(fileHeader, SIZE_OFFSET, FOUR_BYTE);
		System.out.println(""+size);

		//from the next 40 bytes get the width ,height, pixels;(Bitmap information)
		byte[] informationHeader = new byte[INFORMATION_HEADER_SIZE];
		BfInStream.read(informationHeader, 0, INFORMATION_HEADER_SIZE);
		width = readBytes(informationHeader, WIDTH_OFFSET, FOUR_BYTE);
		height = readBytes(informationHeader, HEIGHT_OFFSET, FOUR_BYTE);
		biBitCount = readBytes(informationHeader, BITCOUNT_OFFSET, TWO_BYTE);
		System.out.println(""+width);
		System.out.println(""+height);
		System.out.println(""+biBitCount);
		// determine whether the image is BMP
		if(biBitCount == 24) {
			size  = size - INFORMATION_HEADER_SIZE - FILE_HEADER_SIZE;
			padding = (size / height) - width * RGB_COUNT;
			pixels = new int[height * width];
			byte[] pixelsInformation = new byte[size];
			BfInStream.read(pixelsInformation, 0, size);
			//get the pixels information
			int offset  = 0;
			for(int i = height - 1; i >= 0; i--)
			{
				for(int j = 0; j < width; j++)
				{
					//the rgb for each pixel is each 3 bytes or alpha
					pixels[i*width + j] = ALPHA | readBytes(pixelsInformation, offset, RGB_COUNT);
					offset += RGB_COUNT;
				}
				offset += padding;
			} 
			BfInStream.close();
			return Toolkit.getDefaultToolkit().createImage(
						new MemoryImageSource(width, height, pixels, 0, width));
		} else {
			BfInStream.close();
			throw new IOException("Bits per pixel is not 24");
		}
	}
	/** write to the filepath 
	 **/
	public Image myWrite(Image image, String filePath) throws IOException {
		//determine the type of the image
		if(image instanceof BufferedImage) {
			ImageIO.write((BufferedImage)image, "bmp", new File(filePath));
			return image;
		} else {
			//new a BufferedImage occording the image
			BufferedImage bufimg = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics gra = bufimg.createGraphics();
			gra.drawImage(image, 0, 0, null);
			gra.dispose();
			ImageIO.write(bufimg, "bmp", new File(filePath));
			return image;
		}
	}

	private int readBytes(byte[] array, int offset, int size) 
	{
		int result = 0;
		int temp = 0;
		for(int i = 0; i < size; i++)
		{
			temp = (array[offset+i] & 0x00ff) << i * 8; //强制转化并左移
			result |= temp;
		}
		return result;
	}
}

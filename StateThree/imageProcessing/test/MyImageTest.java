import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;


public class MyImageTest {

	@Test
	public void rslImageRead() throws IOException {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/1.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/1.bmp");
		BufferedImage rslImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), image.getWidth(null));
		assertEquals(srcImage.getHeight(null), image.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
	}

	@Test
	public void rslImageRedProcess() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/1_red_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/1.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image red = processor.showChanelR(image);
		BufferedImage rslImage = new BufferedImage(red.getWidth(null),red.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(red, 0, 0, red.getWidth(null), red.getHeight(null), null);	
		assertEquals(srcImage.getWidth(null), red.getWidth(null));
		assertEquals(srcImage.getHeight(null), red.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(red, "/home/qinkai/Desktop/bmptest/result/1_red_result.bmp");
	}
    
	@Test
	public void rslImageGreenProcess() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/1_green_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/1.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image green = processor.showChanelG(image);
		BufferedImage rslImage = new BufferedImage(green.getWidth(null),green.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(green, 0, 0, green.getWidth(null), green.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), green.getWidth(null));
		assertEquals(srcImage.getHeight(null), green.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(green, "/home/qinkai/Desktop/bmptest/result/1_green_result.bmp");
	}
	
	@Test
	public void rslImageBlueProcess() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/1_blue_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/1.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image blue = processor.showChanelB(image);
		BufferedImage rslImage = new BufferedImage(blue.getWidth(null),	blue.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(blue, 0, 0, blue.getWidth(null), blue.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), blue.getWidth(null));
		assertEquals(srcImage.getHeight(null), blue.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(blue, "/home/qinkai/Desktop/bmptest/result/1_blue_result.bmp");
	}


	@Test
	public void rslImageGrayProcess() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/1_gray_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/1.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image gray = processor.showGray(image);
		BufferedImage rslImage = new BufferedImage(gray.getWidth(null),	gray.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(gray, 0, 0, gray.getWidth(null), gray.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), gray.getWidth(null));
		assertEquals(srcImage.getHeight(null), gray.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				//assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(gray, "/home/qinkai/Desktop/bmptest/result/1_gray_result.bmp");
	}
	
	@Test
	public void rslImageRed2Process() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/2_red_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/2.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image red = processor.showChanelR(image);
		BufferedImage rslImage = new BufferedImage(red.getWidth(null), red.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(red, 0, 0, red.getWidth(null), red.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), red.getWidth(null));
		assertEquals(srcImage.getHeight(null), red.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(red, "/home/qinkai/Desktop/bmptest/result/2_red_result.bmp");
	}

	@Test
	public void rslImageGreen2Process() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/2_green_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/2.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image green = processor.showChanelG(image);
		BufferedImage rslImage = new BufferedImage(green.getWidth(null),green.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(green, 0, 0, green.getWidth(null), green.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), green.getWidth(null));
		assertEquals(srcImage.getHeight(null), green.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(green, "/home/qinkai/Desktop/bmptest/result/2_green_result.bmp");
	}
	
	@Test
	public void rslImageBlue2Process() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/2_blue_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/2.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image blue = processor.showChanelB(image);
		BufferedImage rslImage = new BufferedImage(blue.getWidth(null),	blue.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(blue, 0, 0, blue.getWidth(null), blue.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), blue.getWidth(null));
		assertEquals(srcImage.getHeight(null), blue.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(blue, "/home/qinkai/Desktop/bmptest/result/2_blue_result.bmp");
	}
	

	@Test
	public void rslImageGray2Process() throws Exception {
		FileInputStream fis = new FileInputStream("/home/qinkai/Desktop/bmptest/goal/2_gray_goal.bmp");
		BufferedImage srcImage = ImageIO.read(fis);
		BMPIImageIO myImageIO = new BMPIImageIO();
		Image image = myImageIO.myRead("/home/qinkai/Desktop/bmptest/2.bmp");
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Image gray = processor.showGray(image);
		BufferedImage rslImage = new BufferedImage(gray.getWidth(null), gray.getHeight(null),BufferedImage.TYPE_INT_BGR);
		rslImage.getGraphics().drawImage(gray, 0, 0, gray.getWidth(null), gray.getHeight(null), null);
		assertEquals(srcImage.getWidth(null), gray.getWidth(null));
		assertEquals(srcImage.getHeight(null), gray.getHeight(null));
		for (int i = 0; i < srcImage.getWidth(null); i++) {
			for (int j = 0; j < srcImage.getHeight(null); j++) {
				//assertEquals(srcImage.getRGB(i, j), rslImage.getRGB(i, j));
			}
		}
		myImageIO.myWrite(gray, "/home/qinkai/Desktop/bmptest/result/2_gray_result.bmp");
	}

}

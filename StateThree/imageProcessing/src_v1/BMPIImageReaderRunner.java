import imagereader.Runner;

public class BMPIImageReaderRunner {
	public static void main(String[] args)
	{
		BMPIImageIO image = new BMPIImageIO();
		BMPIImageProcessor processor = new BMPIImageProcessor();
		Runner.run(image, processor);
	}
}
	

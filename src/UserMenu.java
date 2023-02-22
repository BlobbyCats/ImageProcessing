/**
 * 
 */
import images.APImage;
import java.util.Scanner;
/**
 * @author ruthwika, anushka, eileen, alex, kora
 *
 */
public class UserMenu {
	
	private Scanner intReader; 
	private Scanner fileReader;
	private Scanner methodReader;
	private String fileName;
	private String method;
	private APImage image;
	private int threshold;
	private int degree;
	private ImageProcessing processor;
	private String[] images = {"arch", "butterfly1", "koala", "redMotorcycle", 
								"seagull",  "smokey", "swan" };
	private String[] methods = {"rotate 90 left", "rotate 90 right", "rotate 180",
			"black and white", "grayscale", "luminance grayscale", "sepia effect", "brighten",  
			"darken", "color filter", "posterize", "photographic negative", "sharpen",
			"blur", "shrink", "enlarge", "quit" };
	
	/**
	 * Constructor offers the image options, allows user to select the image, then allows the user to pick image processing methods
	 */
	public UserMenu() {
		fileReader =  new Scanner(System.in);
		boolean fileValid = false;
		
		while (!fileValid) {
			System.out.println("\nHere are the images that you can process: ");
			for (int i = 0; i < images.length; i++) {
				System.out.println(images[i]);
			}
			
			System.out.println("\nEnter an image file name (if you would like the default image smokey.jpg, you can simply press enter): ");
			fileName = fileReader.nextLine();
			for (String img : images) {
				if (fileName.isEmpty()) {
					fileName = "smokey";
					fileValid = true;
					break;
				}
				else if (fileName.equalsIgnoreCase(img) || fileName.equalsIgnoreCase(img + ".jpg")) {
					fileName = img;
					fileValid = true;
					break;
				}
			}
			if (!fileValid) {
				System.out.println("\nThis image name is not valid. Please re-enter the image name.\n");
			}
		}
		
		if (!fileName.contains(".jpg")) {
			fileName += ".jpg";
		}
		System.out.println("\nThe chosen image is " + fileName + ".");
		image = new APImage(fileName);
		
		methodReader = new Scanner(System.in);
		method = "";
		processor = new ImageProcessing(fileName);
		
		while(!method.equalsIgnoreCase("quit")) {
			System.out.println("\nHere are your image processing options: ");
			for (int i = 0; i < methods.length; i++) {
				System.out.println(methods[i]);
			}
			
			System.out.println("\nSelect a method to manipulate your image: ");
			method = methodReader.nextLine();
			method.toLowerCase();	
			
			processImage();
			processor.draw();
		}
	}
	
	/**
	 * processes the image based on user input
	 */
	public void processImage() {
		if (method.equalsIgnoreCase("rotate 90 left")) {
			processor.rotate90();
		}
		else if (method.equalsIgnoreCase("rotate 90 right")) {
			processor.rotate270();
		}
		else if (method.equalsIgnoreCase("rotate 180")) {
			processor.rotate180();
		}
		else if (method.equalsIgnoreCase("black and white")) {
			processor.blackAndWhite();
		}
		else if (method.equalsIgnoreCase("grayscale")) {
			processor.grayScale();
		}
		else if (method.equalsIgnoreCase("luminance grayscale")) {
			processor.luminanceGrayscale();
		}
		else if (method.equalsIgnoreCase("sepia effect")) {
			processor.oldFashionedImage();
		}
		else if (method.equalsIgnoreCase("brighten")) {
			System.out.println("\nPlease enter the factor to brighten the image by: ");
			Scanner factorReader = new Scanner(System.in);
			int factor = factorReader.nextInt();
			processor.brightenImage(factor);
		}
		else if (method.equalsIgnoreCase("darken")) {
			System.out.println("\nPlease enter the factor to darken the image by: ");
			Scanner factorReader = new Scanner(System.in);
			int factor = factorReader.nextInt();
			processor.darkenImage(factor);
		}
		else if (method.equalsIgnoreCase("color filter")) {
			processor.colorFiltering();
		}
		else if (method.equalsIgnoreCase("posterize")) {
			processor.posterize();
		}
		else if (method.equalsIgnoreCase("photographic negative")) {
			processor.photographicNegative();
		}
		else if (method.equalsIgnoreCase("sharpen")) {
			intReader =  new Scanner(System.in);
			System.out.println("\nEnter an integer threshold: ");
			threshold = intReader.nextInt();
			System.out.println("\nEnter the degree: ");
			degree = intReader.nextInt();
			processor.sharpen(degree, threshold);
		}
		else if (method.equalsIgnoreCase("blur")) {
			intReader =  new Scanner(System.in);
			System.out.println("\nEnter an integer threshold: ");
			threshold = intReader.nextInt();
			System.out.println("\nEnter the degree: ");
			degree = intReader.nextInt();
			processor.blur();
		}
		else if (method.equalsIgnoreCase("shrink")) {
			System.out.println("\nPlease enter the factor to shrink the image by: ");
			Scanner factorReader = new Scanner(System.in);
			int factor = factorReader.nextInt();
			processor.shrink(factor);
		}
		else if (method.equalsIgnoreCase("enlarge")) {
			intReader =  new Scanner(System.in);
			System.out.println("\nEnter the degree: ");
			degree = intReader.nextInt();
			processor.enlarge(degree);
		}
		else if (method.equalsIgnoreCase("quit")) {
			System.out.println("\nThanks for using our program!");
		}
		else {
			System.out.println("\nThis is not a valid image processing method. Please re-enter the method name.");
		}
	}

	/**
	 * Returns the method used to process the image
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Returns the integer threshold specified by the user
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}
	
	/**
	 * Returns the image chosen by the user
	 * @return the image
	 */
	public APImage getImage() {
		return image;
	}
	

}



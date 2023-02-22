import images.APImage;
import images.Pixel;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author anushka, ruthwika, alex, kora, eileen
 *
 */
public class ImageProcessing {
	private APImage oldImage;
	private APImage newImage;
	private Scanner reader;
	
	/**
	 * Constructor initializes the default image smokey.jpg and clones it
	 */
	public ImageProcessing() {
		oldImage = new APImage("smokey.jpg");
		newImage = oldImage.clone();
	}
	
	/**
	 * Constructor initializes an image based on the String filename provided
	 * @param fname - image filename
	 */
	public ImageProcessing(String fname) {
		if (!fname.contains(".jpg")) {
			fname += ".jpg";
		}
		oldImage = new APImage(fname);
		newImage = oldImage.clone();
	}
	
	/**
	 * Resets the cloned image back to the original
	 */
	public void resetImage() {
		newImage = oldImage.clone();
	}
	
	/**
	 * Returns the original image
	 * @return oldImage - the original image
	 */
	public APImage getOldImage() {
		return oldImage;
	}
	
	/**
	 * Returns the cloned image
	 * @return newImage - the cloned image
	 */
	public APImage getNewImage() {
		return newImage;
	}
	
	/**
	 * Draws both the original image and the cloned image
	 */
	public void draw() {
		oldImage.draw();
		newImage.draw();
	}
	
	/**
	 * Converts the image to black and white
	 */
	public void blackAndWhite() {
		resetImage();
		for (Pixel p: newImage) {
			int red = p.getRed();
			int green = p.getGreen();
			int blue = p.getBlue();
			int average = (red + green + blue) / 3;
			if (average < 128) {
				p.setRed(0);
				p.setGreen(0);
				p.setBlue(0);	
			}
			else {
				p.setRed(225);
				p.setGreen(225);
				p.setBlue(225);	
			}			
		}

	}
	
	/**
	 * Converts the image to gray-scale
	 */
	public void grayScale() {
		resetImage();
		int height = newImage.getHeight();
		int width = newImage.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Pixel p = newImage.getPixel(x,y);
				int red = p.getRed();
				int green = p.getGreen();
				int blue = p.getBlue();
				int average = (red + green + blue) / 3;
				p.setRed(average);
				p.setBlue(average);
				p.setGreen(average);
			}
		}
	} 
	
	/**
	 * Filters the RGB values in the image based on user input
	 */
	public void colorFiltering() {
		resetImage();
		reader = new Scanner(System.in);
		int filRed = 0;
		int filBlue = 0;
		int filGreen = 0;
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("\nSpecify the color filtering changes in RGB order. Each number should be on a new line. ");
				reader = new Scanner(System.in);
				filRed = reader.nextInt();
				filBlue = reader.nextInt();
				filGreen = reader.nextInt();
				valid = true;
			} catch (InputMismatchException e) {
				valid = false;
				System.out.println("\nThis is not a valid input. Please enter integer values only.");
			}
		}
		
		int r, g, b = 0;
		int height = newImage.getHeight();
		int width = newImage.getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Pixel p = newImage.getPixel(x,y);
				int red = p.getRed();
				int green = p.getGreen();
				int blue = p.getBlue();
				r = red + filRed;
				g = green + filGreen;
				b = blue + filBlue;
				//red
				if (r <= 255 && r >= 0) {
					p.setRed(r);
				}
				else {
					if (r < 0) {
						p.setRed(0);
					}
					else {
						p.setRed(255);
					}
				}
				
				//green
				if (g <= 255 && g >= 0) {
					p.setGreen(g);
				}
				else {
					if (g < 0) {
						p.setGreen(0);
					}
					else {
						p.setGreen(255);
					}
				}
				
				//blue
				if (b <= 255 && b >= 0) {
					p.setBlue(b);
				}
				else {
					if (b < 0) {
						p.setBlue(0);
					}
					else {
						p.setBlue(255);
					}
				}	
			}	
		}

	}
	
	/**
	 * Converts the image to an old-fashioned image using sepia effect
	 */
	public void oldFashionedImage() {
		resetImage();
		for (Pixel p : newImage) {
			int red = p.getRed();
			int green = p.getGreen();
			int blue = p.getBlue();
			int average = (red + green + blue) / 3;
			
			p.setRed(average);
			p.setGreen(average);
			p.setBlue(average);
			
			if (average < 63) {
				red = (int) (average * 1.1);
				blue = (int) (average * 0.9);
			}
			else if (average < 192) {
				red = (int) (average * 1.15);
				blue = (int) (average * 0.85);
			}
			else {
				red = Math.min((int) (average * 1.08), 255);
				blue = (int) (average * 0.93);
			}
			
			p.setRed(red);
			p.setBlue(blue);
		}
	}
	
	/**
	 * Darkens an image based on the given factor
	 * @param factor - factor to darken image by
	 */
	public void darkenImage(double factor) {
		resetImage();
		for (Pixel p : newImage) {
			int red = p.getRed();
			int green = p.getGreen();
			int blue = p.getBlue();
			
			red -= factor;
			green -= factor;
			blue -= factor;
			
			if (red < 0) {
				red = 0;
			}
			if (green < 0) {
				green = 0;
			}
			if (blue < 0) {
				blue = 0;
			}
			
			p.setRed(red);
			p.setGreen(green);
			p.setBlue(blue);
		}
	} 
	
	/**
	 * Brightens an image based on the given factor
	 * @param factor - factor to brighten image by
	 */
	public void brightenImage(double factor) {
		resetImage();
		for (Pixel p : newImage) {
			int red = p.getRed();
			int green = p.getGreen();
			int blue = p.getBlue();
			
			red += factor;
			green += factor;
			blue += factor;
			
			if (red > 255) {
				red = 255;
			}
			if (green > 255) {
				green = 255;
			}
			if (blue > 255) {
				blue = 255;
			}
			
			p.setRed(red);
			p.setGreen(green);
			p.setBlue(blue);
		}
	}

	/**
	 * Converts the image to luminance gray-scale
	 */
	public void luminanceGrayscale() {
		resetImage();
		for(int i = 0; i< newImage.getWidth(); i++) {
			for(int j = 0; j < newImage.getHeight(); j++) {
				Pixel p = newImage.getPixel(i,j);
				int red = (int) ((int) p.getRed() * 0.299);
				int blue = (int) ((int) p.getBlue() * 0.114);
				int green = (int) ((int) p.getGreen() * 0.587);
				int scale = red + blue + green / 3;
				p.setRed(scale);
				p.setBlue(scale);
				p.setGreen(scale);
			}
		}
	} 
	
	/**
	 * Converts the image to photographic negative
	 */
	public void photographicNegative() {
		resetImage();
		for(int i = 0; i< newImage.getWidth(); i++) {
			for(int j = 0; j < newImage.getHeight(); j++) {
				Pixel p = newImage.getPixel(i,j);
				int red = p.getRed();
				int green = p.getGreen();
				int blue = p.getBlue();
				int average = (red + green + blue) / 3;
				red = 255 - average;
				green = 255 - average;
				blue = 255 - average;
				p.setRed(red);
				p.setBlue(blue);
				p.setGreen(green);
			}
		}
	}
    
	/**
	 * Rotates an image 90 degrees left
	 */
	public void rotate90() {
		resetImage();
		int width = oldImage.getWidth();
		int height = oldImage.getHeight();
		APImage rotateImage = new APImage(height, width);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Pixel oldPixel = oldImage.getPixel(x, y);
				rotateImage.setPixel(y, width-1-x, oldPixel); 
			}
		}
		newImage = rotateImage.clone();
	}
	
	/**
	 * Rotates an image 180 degrees
	 */
	public void rotate180() {
		resetImage();
		int width = oldImage.getWidth();
		int height = oldImage.getHeight();
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Pixel oldPixel = oldImage.getPixel(x, y);
				newImage.setPixel(width-1-x, height-1-y, oldPixel); 
			}
		}
	}
	
	/**
	 * Rotates an image 90 degrees right (270 degrees left)
	 */
	public void rotate270() {
		resetImage();
		int width = newImage.getWidth();
		int height = newImage.getHeight();
		APImage rotateImage = new APImage(height, width);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Pixel oldPixel = oldImage.getPixel(x, y);
				rotateImage.setPixel(height-1-y, x, oldPixel); 
			}
		}
		newImage = rotateImage.clone();
	}
    
	/**
	 * Converts an image to a posterized version
	 */
	public void posterize() {
    	resetImage();
		Pixel ran1 = new Pixel((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
		Pixel ran2 = new Pixel((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
		for(Pixel p : newImage) {
			int red = p.getRed();
			int green = p.getGreen();
			int blue = p.getBlue();
			int average = (red + green + blue) / 3;
			if(average < 128) {
				p.setRed(ran1.getRed());
				p.setGreen(ran1.getGreen());
				p.setBlue(ran1.getBlue());
			} else {
				p.setRed(ran2.getRed());
				p.setGreen(ran2.getGreen());
				p.setBlue(ran2.getBlue());
			}
		}
	}
	
	/**
	 * Sharpens an image by a given degree based on edges detected by a certain threshold
	 * @param degree - degree to sharpen an image
	 * @param threshold - threshold to detect edges
	 */
     public void sharpen(int degree, int threshold) {
    	resetImage();
		for (int y = 0; y < newImage.getHeight() - 1; y++) {
			for (int x = 1; x < newImage.getWidth(); x++) {
				Pixel oldPixel = oldImage.getPixel(x, y);
				Pixel leftPixel = oldImage.getPixel(x-1, y);
				Pixel bottomPixel = oldImage.getPixel(x, y+1);
				int oldAve = (oldPixel.getRed() + oldPixel.getGreen() + oldPixel.getBlue()) / 3;
				int leftAve = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue()) / 3;
				int bottomAve = (bottomPixel.getRed() + bottomPixel.getGreen() + bottomPixel.getBlue()) / 3;
				
				Pixel newPixel = newImage.getPixel(x, y);
				if(Math.abs(oldAve - leftAve) > threshold || Math.abs(oldAve - bottomAve) > threshold) {
					if(oldPixel.getRed() - degree > 0) {
						newPixel.setRed(oldPixel.getRed() - degree);
					} else {
						newPixel.setRed(0);
					}
					if(oldPixel.getGreen() - degree > 0) {
						newPixel.setGreen(oldPixel.getGreen() - degree);
					} else {
						newPixel.setGreen(0);
					}
					if(oldPixel.getBlue() - degree > 0) {
						newPixel.setBlue(oldPixel.getBlue() - degree);
					} else {
						newPixel.setBlue(0);
					}
				}
			}
		}
	}
    
     /**
 	 * Shrinks an image based on the given factor
 	 * @param factor - factor to shrink image by
 	 */
    public void shrink(int factor) {
    	resetImage();
		int width = oldImage.getWidth();
		int height = oldImage.getHeight();
		newImage = new APImage(width/factor, height/factor);
		for (int i = 0; i < newImage.getHeight(); i++) {
			for (int j = 0; j < newImage.getWidth(); j++) {
				newImage.setPixel(j, i, oldImage.getPixel(j*factor, i*factor));
			}
		}
	}
    
    /**
	 * Converts an image to a blurred version
	 */
    public void blur() {
		int width = oldImage.getWidth();
		int height = oldImage.getHeight();
		newImage = oldImage.clone();
		for(int j = 1; j < oldImage.getHeight() - 1; j++) {
			for(int i = 1; i < oldImage.getWidth() - 1; i++) {
				int averageRed = (oldImage.getPixel(i-1, j).getRed()+oldImage.getPixel(i+1, j).getRed()+oldImage.getPixel(i, j-1).getRed()+oldImage.getPixel(i, j+1).getRed())/4;
				int averageGreen = (oldImage.getPixel(i-1, j).getGreen()+oldImage.getPixel(i+1, j).getGreen()+oldImage.getPixel(i, j-1).getGreen()+oldImage.getPixel(i, j+1).getGreen())/4;
				int averageBlue = (oldImage.getPixel(i-1, j).getBlue()+oldImage.getPixel(i+1, j).getBlue()+oldImage.getPixel(i, j-1).getBlue()+oldImage.getPixel(i, j+1).getBlue())/4;
				Pixel newPixel = new Pixel(averageRed, averageGreen, averageBlue);
						
				newImage.setPixel(i, j, newPixel);
			}
		}
	}
    
    /**
	 * Enlarges an image based on the given factor
	 * @param factor - factor to enlarge image by
	 */
    public void enlarge(int factor) {
		int width = oldImage.getWidth();
		int height = oldImage.getHeight();
		newImage = new APImage(width*factor, height*factor);
		for(int i = 0; i < oldImage.getHeight(); i++) {
			for(int j = 0; j < oldImage.getWidth(); j++) {
				for(int y = i*factor; y < i*factor+factor; y++) {
					for(int x = j*factor; x < j*factor+factor; x++) {
						newImage.setPixel(x, y, oldImage.getPixel(j, i));
					}
				}
			}
		}
	}


       
      
}

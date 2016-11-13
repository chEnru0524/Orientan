/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientan.image;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import orientan.image.ImagePairs;

import javax.imageio.ImageIO;
/**
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */

public class ImagePairLoader {

	/**
	 */
	public static void load(final String name, final Point center) throws IOException {
			if( ImagePairs.contains(name) ) return;

			final BufferedImage leftImage = ImageIO.read(ImagePairLoader.class.getResource(name));
			final BufferedImage rightImage = flip(leftImage);
			ImagePair ip = new ImagePair(new MascotImage(leftImage, center), 
					new MascotImage(rightImage, new Point(rightImage.getWidth() - center.x, center.y)));
			ImagePairs.load( name, ip );
	}

	/**
	 */
	private static BufferedImage flip(final BufferedImage src) {

		final BufferedImage copy = new BufferedImage(src.getWidth(), src.getHeight(),
				src.getType() == BufferedImage.TYPE_CUSTOM ? BufferedImage.TYPE_INT_ARGB : src.getType());

		for (int y = 0; y < src.getHeight(); ++y) {
			for (int x = 0; x < src.getWidth(); ++x) {
				copy.setRGB(copy.getWidth() - x - 1, y, src.getRGB(x, y));
			}
		}
		return copy;
	}

}

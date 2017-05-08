package es.ucm.fdi.is.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Utilidades {
	
	/**
	 * Reads an image from a file inside the current classpath.
	 * 
	 * @return the image; can be displayed and used in ImageIcons.
	 */
	public static Image loadImage(String path) {
		URL imgUrl = Utilidades.class.getClassLoader().getResource(path);
		return Toolkit.getDefaultToolkit().createImage(imgUrl);
	}

}

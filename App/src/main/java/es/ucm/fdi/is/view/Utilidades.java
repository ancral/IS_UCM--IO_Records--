package es.ucm.fdi.is.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.net.URL;

import javax.swing.ImageIcon;

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
	
	public static ImageIcon createImage(String path, int width, int height) {
		URL imgUrl = Utilidades.class.getClassLoader().getResource(path);
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgUrl).getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon createImage(String path) {
		URL imgUrl = Utilidades.class.getClassLoader().getResource(path);
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(imgUrl));
	}
	
    /**
     * Round to certain number of decimals
     * 
     * @param d
     * @param decimalPlace
     * @return
     */
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}

package assignment13.View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;	//Creating fields
	private BufferedImage image;
	private JPanel emptyPanel1;
	private JPanel emptyPanel2;

	/*
	 * converts url into image
	 */
	public ImagePanel(String url) {	

		try {
			File file = new File(url);
			image = ImageIO.read(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * paints image onto this panel
	 */
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		graphic.drawImage(image, 0, 0, this);
	}

	public void setBoatLayout() {
		setLayout(new GridLayout(2, 1));
		emptyPanel1 = new JPanel();
		emptyPanel1.setOpaque(false);
		add(emptyPanel1);
		emptyPanel2 = new JPanel();
		emptyPanel2.setOpaque(false);
		add(emptyPanel2);
	}

}

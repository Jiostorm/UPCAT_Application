package game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.utils.ImageLoader;

@SuppressWarnings("serial")
public class ImageQuestion extends JPanel{
	
	private int width, height;
	private BufferedImage imageQ;
	
	public ImageQuestion(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		imageQ = ImageLoader.getImage("/img/Load.jpg");
		
		setPreferredSize(new Dimension(width, height));
		
		g.drawImage(imageQ, 0, 0, width, height, null);
		
		
	}
}

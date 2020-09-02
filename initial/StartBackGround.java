package game.initial;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.utils.ImageLoader;

@SuppressWarnings("serial")
public class StartBackGround extends JPanel{
	
	private BufferedImage bg;
	//
	private int width, height;
	//
	public StartBackGround(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
	}
	//
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		bg = ImageLoader.loadImage("/image/attributes/upcatbg.png");
		g.drawImage(bg, 0, 0, width, (height-30), null);
	}
}
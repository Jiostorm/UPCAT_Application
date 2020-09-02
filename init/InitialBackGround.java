package game.init;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.utils.ImageLoader;

@SuppressWarnings("serial")
public class InitialBackGround extends JPanel{
	
	private int width, height;
	private String imgname;
	
	private BufferedImage initbgimg;
	
	public InitialBackGround(int width, int height, String imgname) {
		this.width = width;
		this.height = height;
		this.imgname = imgname;
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		initbgimg = ImageLoader.getImage("/img/bg/"+imgname);
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		
		g.drawImage(initbgimg, 0, 0, width-5, height-30, null);
	}
}

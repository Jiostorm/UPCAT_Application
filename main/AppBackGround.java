package game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.utils.ImageLoader;

@SuppressWarnings("serial")
public class AppBackGround extends JPanel{
	
	private int width, height;
	private String imgname;
	
	private BufferedImage mainbgimg;
	
	public AppBackGround(int width, int height, String imgname) {
		this.width = width;
		this.height = height;
		this.imgname = imgname;
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		mainbgimg = ImageLoader.getImage("/img/bg/"+imgname);
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));

		g.drawImage(mainbgimg, 0, 0, width, height, null);
		
	}
}

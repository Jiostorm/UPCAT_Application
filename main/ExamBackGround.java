package game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.utils.ImageLoader;

@SuppressWarnings("serial")
public class ExamBackGround extends JPanel{
	
	private BufferedImage bgimg;
	//
	private int width, height;
	//
	public ExamBackGround(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
	}
	//
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bgimg = ImageLoader.getImage("/image/bg/upcat.png");
		
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		
		g.drawImage(bgimg, 0, 0, width, height-30, null);
	}
}
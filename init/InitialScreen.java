package game.init;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import game.AppLauncher;

public class InitialScreen implements Runnable{

	private InitialBackGround initpanel;
	
	private boolean active = false;
	
	private Font bfont;
	
	private JLabel footer;
	private JButton start;
	private JProgressBar loadbar;
	
	private Thread initthread;
	
	public InitialScreen(int width, int height, String imgname) {
		initpanel = new InitialBackGround(width, height, imgname);
		bfont = new Font("Times New Roman", 5, 14);

		footer = new JLabel("",JLabel.LEFT);
		footer.setBounds(0, 255, 200, 15);
		footer.setFont(bfont);

		start = new JButton("Start App");
		start.setForeground(Color.WHITE);
		start.setBackground(Color.BLACK);
		start.setBounds(150, 190, 100, 20);		
		start.setFont(bfont);

		loadbar = new JProgressBar(0, 100);
		loadbar.setBackground(Color.BLACK);
		loadbar.setForeground(Color.GRAY);
		loadbar.setValue(0);
		loadbar.setStringPainted(true);
		loadbar.setBounds(100, 220, 200, 20);
		loadbar.setFont(bfont);
		
		initpanel.add(start);
		LoadingScreen.getframe().add(initpanel);
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadingScreen.getframe().setTitle("Launching UP College Admission Test");
				start.setEnabled(false);
				initpanel.add(footer);
				initpanel.add(loadbar);
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							while(loadbar.getValue() != 100) {
							footer.setText("Initializing.");
							LoadingScreen.getframe().setTitle("Launching UP College Admission Test.");
							Thread.sleep(200);
							footer.setText("Initializing..");
							LoadingScreen.getframe().setTitle("Launching UP College Admission Test..");
							Thread.sleep(200);
							footer.setText("Initializing...");
							LoadingScreen.getframe().setTitle("Launching UP College Admission Test...");
							Thread.sleep(200);
							footer.setText("Initializing....");
							LoadingScreen.getframe().setTitle("Launching UP College Admission Test....");
							Thread.sleep(200);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
				start();
				thread.start();
			}
		});
	}
	public void run() {
		for(int tick = 0; tick <= 100; tick++) {
			//footer.setText("Initializing....");
			final int now = tick;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					loadbar.setValue(now);
					if(loadbar.getValue() == 100) {
						footer.setText("Initialization Complete!");
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								} finally {
									LoadingScreen.getframe().dispose();
									AppLauncher.loadWindow(2);
								}
							}
						});
					}
				}		
			});
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stop();
	}
	private synchronized void start() {
		if(active) {
			return;
		}
		active = true;
		initthread = new Thread(this);
		initthread.start();
	}
	private synchronized void stop() {
		if(!active) {
			return;
		}
		try {
			initthread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
